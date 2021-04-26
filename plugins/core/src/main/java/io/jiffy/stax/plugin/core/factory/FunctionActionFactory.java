package io.jiffy.stax.plugin.core.factory;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.core.action.FunctionAction;
import org.pf4j.Extension;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashSet;
import java.util.Map;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class FunctionActionFactory extends ActionFactoryExtensionPointAdapter implements STAXJobManagementHandler {

    public static final String ELEMENT_NAME = "function";

    private STAX staxService;

    public FunctionActionFactory() {
        super(ELEMENT_NAME);
    }

    @Override
    public void initialize(STAX stax) {

    }

    @Override
    public STAXAction parseAction(STAX staxService, STAXJob job, Node root) throws STAXException {
        this.staxService = staxService;
        STAXFunctionAction function = new STAXFunctionAction();

        function.setLineNumber(root);
        function.setXmlFile(job.getXmlFile());
        function.setXmlMachine(job.getXmlMachine());

        /*String functionName = "";
        String scope = "";
        String requires = "";*/
        STAXAction functionAction = null;

        // Used to make sure all argument names are unique for a function
        HashSet<String> argNameSet = new HashSet<String>();

        // Used to make sure that for function-arg-def arguments, "required"
        // and "optional" args are specified before "other" args
        boolean functionArgDefOtherSpecified = false;

        assignAttrs(root, function);

        return handleChildNodes(job, root, function, functionAction, argNameSet, functionArgDefOtherSpecified);
    }

    private STAXAction handleChildNodes(STAXJob job, Node root, STAXFunctionAction function, STAXAction functionAction, HashSet<String> argNameSet, boolean functionArgDefOtherSpecified) throws STAXException {
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node thisChild = children.item(i);

            switch (thisChild.getNodeType()) {
                case Node.COMMENT_NODE:
                    /* Do nothing */
                    break;
                case Node.ELEMENT_NODE:
                    function.setLineNumber(thisChild);

                    functionAction = handleElementNode(job, function, functionAction, argNameSet, functionArgDefOtherSpecified, thisChild);
                    break;
                default:
                    function.setElementInfo(new STAXElementInfo(
                            root.getNodeName(),
                            STAXElementInfo.NO_ATTRIBUTE_NAME,
                            STAXElementInfo.LAST_ELEMENT_INDEX,
                            "Contains invalid node type: " + Integer.toString(thisChild.getNodeType())));

                    throw new STAXInvalidXMLNodeTypeException(STAXUtil.formatErrorMessage(function), function);
            }
        }
        return functionAction;
    }

    private STAXAction handleElementNode(STAXJob job, STAXFunctionAction function, STAXAction functionAction, HashSet<String> argNameSet, boolean functionArgDefOtherSpecified, Node thisChild) throws STAXException {
        switch (thisChild.getNodeName()) {
            case "function-description":
            case "function-prolog":
                function.setProlog(handleChild(thisChild, function));
                break;
            case "function-epilog":
                function.setEpilog(handleChild(thisChild, function));
                break;
            case "function-import":
                handleFunctionImport(function, thisChild);
                break;
            case "function-no-args":
                function.setArgDefinition(STAXFunctionAction.FUNCTION_ALLOWS_NO_ARGS);
                break;
            case "function-single-arg":
                function.setArgDefinition(STAXFunctionAction.FUNCTION_DEFINES_ONE_ARG);

                // Iterate child element to get a required/option arg
                handleArg(thisChild, function, argNameSet, functionArgDefOtherSpecified);
                break;
            case "function-list-args":
                function.setArgDefinition(STAXFunctionAction.FUNCTION_DEFINES_LIST_ARGS);

                // Iterate child elements to get required/optional args
                handleArg(thisChild, function, argNameSet, functionArgDefOtherSpecified);
                break;
            case "function-map-args":
                function.setArgDefinition(STAXFunctionAction.FUNCTION_DEFINES_MAP_ARGS);

                // Iterate child elements to get required/optional args
                handleArg(thisChild, function, argNameSet, functionArgDefOtherSpecified);
                break;
            default:
                if (functionAction != null) {
                    function.setElementInfo(
                            new STAXElementInfo(
                                    thisChild.getNodeName(),
                                    STAXElementInfo.NO_ATTRIBUTE_NAME,
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "Invalid element type \"" +
                                            thisChild.getNodeName() + "\""));

                    throw new STAXInvalidXMLElementCountException(
                            STAXUtil.formatErrorMessage(function),
                            function);
                }

                STAXActionFactory factory = staxService.getActionFactory(thisChild.getNodeName());

                if (factory == null) {
                    function.setElementInfo(new STAXElementInfo(
                            thisChild.getNodeName(),
                            STAXElementInfo.NO_ATTRIBUTE_NAME,
                            STAXElementInfo.LAST_ELEMENT_INDEX,
                            "No action factory for element type \"" +
                                    thisChild.getNodeName() + "\""));

                    throw new STAXInvalidXMLElementException(
                            STAXUtil.formatErrorMessage(function),
                            function);
                }

                functionAction = factory.parseAction(staxService, job, thisChild);

                function.setAction(functionAction);
                break;
        }
        return function;
    }

    private void handleFunctionImport(STAXFunctionAction function, Node thisChild) throws STAXException {
        // Get required file attribute and optional machine and
        // functions attributes

        String file = null;
        String directory = null;
        String machine = null;
        String functions = null;

        NamedNodeMap childAttrs = thisChild.getAttributes();

        for (int j = 0; j < childAttrs.getLength(); ++j) {
            Node thisAttr = childAttrs.item(j);

            switch (thisAttr.getNodeName()) {
                case "file":
                    file = thisAttr.getNodeValue();
                    break;
                case "directory":
                    directory = thisAttr.getNodeValue();
                    break;
                case "machine":
                    machine = thisAttr.getNodeValue();
                    break;
            }
        }

        valideFileAndDirectory(function, thisChild, file, directory);

        functions = handleChild(thisChild, function);

        if ((directory != null) && (functions.length() != 0)) {
            function.setElementInfo(new STAXElementInfo(
                    thisChild.getNodeName(),
                    STAXElementInfo.NO_ATTRIBUTE_NAME,
                    STAXElementInfo.LAST_ELEMENT_INDEX,
                    "Cannot specify functions if the \"directory\" " +
                            "attribute is specified."));

            throw new STAXInvalidXMLAttributeException(
                    STAXUtil.formatErrorMessage(function),
                    function);
        }

        function.addToImportList(file, directory, machine, functions);
    }

    private void valideFileAndDirectory(STAXFunctionAction function, Node thisChild, String file, String directory) throws STAXInvalidXMLAttributeException {
        if ((file != null) && (directory != null)) {
            function.setElementInfo(new STAXElementInfo(
                    thisChild.getNodeName(),
                    STAXElementInfo.NO_ATTRIBUTE_NAME,
                    STAXElementInfo.LAST_ELEMENT_INDEX,
                    "Only one of the following attributes " + "are allowed:  file | directory"));

            throw new STAXInvalidXMLAttributeException(
                    STAXUtil.formatErrorMessage(function),
                    function);
        } else if ((file == null) && (directory == null)) {
            function.setElementInfo(new STAXElementInfo(
                    thisChild.getNodeName(),
                    STAXElementInfo.NO_ATTRIBUTE_NAME,
                    STAXElementInfo.LAST_ELEMENT_INDEX,
                    "One of the following attributes must be " +
                            "specified: file | directory"));

            throw new STAXInvalidXMLAttributeException(
                    STAXUtil.formatErrorMessage(function),
                    function);
        }
    }

    private void assignAttrs(Node root, STAXFunctionAction function) {
        NamedNodeMap rootAttrs = root.getAttributes();

        for (int i = 0; i < rootAttrs.getLength(); ++i) {
            Node thisAttr = rootAttrs.item(i);

            switch (thisAttr.getNodeName()) {
                case "name":
                    function.setName(thisAttr.getNodeValue());
                    break;
                case "scope":
                    function.setScope(thisAttr.getNodeValue());
                    break;
                case "requires":
                    function.setRequires(thisAttr.getNodeValue());
                    break;
            }
        }
    }

    private String handleChild(Node root, STAXFunctionAction action) throws STAXException {
        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); ++i) {
            Node thisChild = children.item(i);

            // XXX: Should I be able to have a COMMENT_NODE here?

            switch (thisChild.getNodeType()) {
                case Node.COMMENT_NODE:
                    /* Do nothing */
                    break;
                case Node.CDATA_SECTION_NODE:
                    return thisChild.getNodeValue();
                case Node.TEXT_NODE:
                    String value = thisChild.getNodeValue().trim();

                    if (!value.equals("")) {
                        return value;
                    }
                    break;
                default:
                    action.setElementInfo(
                            new STAXElementInfo(
                                    root.getNodeName(),
                                    STAXElementInfo.NO_ATTRIBUTE_NAME,
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "Contains invalid node type: " +
                                            Integer.toString(thisChild.getNodeType())));

                    throw new STAXInvalidXMLNodeTypeException(
                            STAXUtil.formatErrorMessage(action), action);

            }
        }

        return "";
    }

    private void handleArg(Node root, STAXFunctionAction function, HashSet<String> argNameSet, boolean functionArgDefOtherSpecified) throws STAXException {
        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); ++i) {
            Node thisChild = children.item(i);

            switch (thisChild.getNodeType()) {
                case Node.COMMENT_NODE:
                    /* Do nothing */
                    break;
                case Node.ELEMENT_NODE:
                    function.setLineNumber(thisChild);

                    if (thisChild.getNodeName().equals("function-required-arg")) {
                        int type = STAXFunctionAction.ARG_REQUIRED;
                        String name = new String();
                        String defaultValue = new String();

                        NamedNodeMap attrs = thisChild.getAttributes();

                        for (int j = 0; j < attrs.getLength(); ++j) {
                            Node thisAttr = attrs.item(j);

                            if (thisAttr.getNodeName().equals("name")) {
                                name = thisAttr.getNodeValue();
                            }
                        }

                        if (name.equals("")) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The \"name\" attribute's value cannot be null."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        } else if (!argNameSet.add(name)) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The value \"" + name + "\" has already been " +
                                            "used.  The name for each argument defined for " +
                                            "a function must be unique."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        }

                        function.addArg(new STAXFunctionArgument(
                                name, STAXFunctionAction.ARG_REQUIRED,
                                handleChild(thisChild, function)));
                    } else if (thisChild.getNodeName().equals(
                            "function-optional-arg")) {
                        String name = new String();
                        String defaultValue = new String();

                        NamedNodeMap attrs = thisChild.getAttributes();

                        for (int j = 0; j < attrs.getLength(); ++j) {
                            Node thisAttr = attrs.item(j);

                            if (thisAttr.getNodeName().equals("name")) {
                                name = thisAttr.getNodeValue();
                            } else if (thisAttr.getNodeName().equals("default")) {
                                function.setElementInfo(new STAXElementInfo(
                                        thisChild.getNodeName(),
                                        thisAttr.getNodeName(),
                                        STAXElementInfo.LAST_ELEMENT_INDEX));

                                defaultValue = STAXUtil.parseAndCompileForPython(
                                        thisAttr.getNodeValue(), function);
                            }
                        }

                        if (name.equals("")) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The \"name\" attribute's value cannot be null."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        } else if (!argNameSet.add(name)) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The value \"" + name + "\" has already been " +
                                            "used.  The name for each argument defined for " +
                                            "a function must be unique."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        }

                        function.addArg(
                                new STAXFunctionArgument(
                                        name, STAXFunctionAction.ARG_OPTIONAL,
                                        defaultValue,
                                        handleChild(thisChild, function)));
                    } else if (thisChild.getNodeName().equals("function-other-args")) {
                        String name = new String();
                        String defaultValue = new String();

                        NamedNodeMap attrs = thisChild.getAttributes();

                        for (int j = 0; j < attrs.getLength(); ++j) {
                            Node thisAttr = attrs.item(j);

                            if (thisAttr.getNodeName().equals("name")) {
                                name = thisAttr.getNodeValue();
                            }
                        }

                        if (name.equals("")) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The \"name\" attribute's value cannot be null."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        } else if (!argNameSet.add(name)) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The value \"" + name + "\" has already been " +
                                            "used.  The name for each argument defined for " +
                                            "a function must be unique."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        }

                        function.addArg(
                                new STAXFunctionArgument(
                                        name, STAXFunctionAction.ARG_OTHER, defaultValue,
                                        handleChild(thisChild, function)));
                    } else if (thisChild.getNodeName().equals("function-arg-def")) {
                        function.setDefinedWithFunctionArg(true);

                        String name = new String();
                        String type = new String();
                        String defaultValue = new String();
                        String description = new String();
                        int functionType = 0;

                        NamedNodeMap attrs = thisChild.getAttributes();

                        for (int j = 0; j < attrs.getLength(); ++j) {
                            Node thisAttr = attrs.item(j);

                            if (thisAttr.getNodeName().equals("name")) {
                                name = thisAttr.getNodeValue();
                            } else if (thisAttr.getNodeName().equals("type")) {
                                type = thisAttr.getNodeValue();

                                if (type.equals("required")) {
                                    if (functionArgDefOtherSpecified) {
                                        function.setElementInfo(new STAXElementInfo(
                                                thisChild.getNodeName(), "type",
                                                STAXElementInfo.LAST_ELEMENT_INDEX,
                                                "Required arguments must be defined" +
                                                        " before arguments with type " +
                                                        "\"other\"."));

                                        throw new STAXInvalidFunctionArgumentException(
                                                STAXUtil.formatErrorMessage(function), function);
                                    }

                                    functionType = STAXFunctionAction.ARG_REQUIRED;
                                } else if (type.equals("optional")) {
                                    if (functionArgDefOtherSpecified) {
                                        function.setElementInfo(new STAXElementInfo(
                                                thisChild.getNodeName(), "type",
                                                STAXElementInfo.LAST_ELEMENT_INDEX,
                                                "Optional arguments must be defined" +
                                                        " before arguments with type " +
                                                        "\"other\"."));

                                        throw new STAXInvalidFunctionArgumentException(
                                                STAXUtil.formatErrorMessage(function), function);
                                    }

                                    functionType = STAXFunctionAction.ARG_OPTIONAL;
                                } else if (type.equals("other")) {
                                    functionType = STAXFunctionAction.ARG_OTHER;
                                    functionArgDefOtherSpecified = true;
                                }
                            } else if (thisAttr.getNodeName().equals("default")) {
                                function.setElementInfo(new STAXElementInfo(
                                        thisChild.getNodeName(),
                                        thisAttr.getNodeName(),
                                        STAXElementInfo.LAST_ELEMENT_INDEX));

                                defaultValue = STAXUtil.parseAndCompileForPython(
                                        thisAttr.getNodeValue(), function);
                            }
                        }

                        if (name.equals("")) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The \"name\" attribute's value cannot be null."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        } else if (!argNameSet.add(name)) {
                            function.setElementInfo(new STAXElementInfo(
                                    thisChild.getNodeName(), "name",
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "The value \"" + name + "\" has already been " +
                                            "used.  The name for each argument defined for " +
                                            "a function must be unique."));

                            throw new STAXInvalidFunctionArgumentException(
                                    STAXUtil.formatErrorMessage(function), function);
                        }

                        // Iterate child elements to get the arg-def sub-elements
                        STAXFunctionArgument arg = handleArgDef(
                                thisChild, function, argNameSet);

                        arg.setName(name);
                        arg.setType(functionType);
                        arg.setDefaultValue(defaultValue);

                        function.addArg(arg);
                    }
                    break;
                default:
                    function.setElementInfo(new STAXElementInfo(
                            root.getNodeName(),
                            STAXElementInfo.NO_ATTRIBUTE_NAME,
                            STAXElementInfo.LAST_ELEMENT_INDEX,
                            "Contains invalid node type: " +
                                    Integer.toString(thisChild.getNodeType())));

                    throw new STAXInvalidXMLNodeTypeException(
                            STAXUtil.formatErrorMessage(function), function);
            }
        }
    }

    private STAXFunctionArgument handleArgDef(Node root, STAXFunctionAction function, HashSet<String> argNameSet) throws STAXException {
        STAXFunctionArgument arg = new STAXFunctionArgument();

        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); ++i) {
            Node thisChild = children.item(i);

            switch (thisChild.getNodeType()) {
                case Node.COMMENT_NODE:
                    /* Do nothing */
                    break;
                case Node.ELEMENT_NODE:
                    if (thisChild.getNodeName().equals("function-arg-description")) {
                        arg.setDescription(handleChild(thisChild, function));
                    }

                    if (thisChild.getNodeName().equals("function-arg-private")) {
                        arg.setPrivate(true);
                    }
                    if (thisChild.getNodeName().equals("function-arg-property")) {
                        STAXFunctionArgumentProperty argProperty =
                                new STAXFunctionArgumentProperty();

                        NamedNodeMap attrs = thisChild.getAttributes();

                        for (int j = 0; j < attrs.getLength(); ++j) {
                            Node thisAttr = attrs.item(j);

                            if (thisAttr.getNodeName().equals("name")) {
                                argProperty.setName(thisAttr.getNodeValue());
                            } else if (thisAttr.getNodeName().equals("value")) {
                                argProperty.setValue(thisAttr.getNodeValue());
                            }
                        }

                        NodeList propertyChildren = thisChild.getChildNodes();

                        for (int k = 0; k < propertyChildren.getLength(); ++k) {
                            Node thisPropertyChild = propertyChildren.item(k);

                            if (thisPropertyChild.getNodeType() ==
                                    Node.COMMENT_NODE) {
                                /* Do nothing */
                            } else if (thisPropertyChild.getNodeType() ==
                                    Node.ELEMENT_NODE) {
                                if (thisPropertyChild.getNodeName().equals(
                                        "function-arg-property-description")) {
                                    argProperty.setDescription(
                                            handleChild(thisPropertyChild, function));
                                } else if (thisPropertyChild.getNodeName().equals(
                                        "function-arg-property-data")) {
                                    STAXFunctionArgumentPropertyData argPropData =
                                            handleArgPropertyData(thisPropertyChild);

                                    argProperty.addData(argPropData);
                                }
                            }
                        }

                        arg.addProperty(argProperty);
                    }
                    break;
            }
        }

        return arg;
    }

    private STAXFunctionArgumentPropertyData handleArgPropertyData(Node root) throws STAXException {
        STAXFunctionArgumentPropertyData propData = new STAXFunctionArgumentPropertyData();

        NamedNodeMap attrs = root.getAttributes();

        for (int j = 0; j < attrs.getLength(); ++j) {
            Node thisAttr = attrs.item(j);

            if (thisAttr.getNodeName().equals("type")) {
                propData.setType(thisAttr.getNodeValue());
            } else if (thisAttr.getNodeName().equals("value")) {
                propData.setValue(thisAttr.getNodeValue());
            }
        }

        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); ++i) {
            Node thisChild = children.item(i);

            switch (thisChild.getNodeType()) {
                case Node.COMMENT_NODE:
                    /* Do nothing */
                    break;
                case Node.ELEMENT_NODE:
                    if (thisChild.getNodeName().equals(
                            "function-arg-property-data")) {
                        propData.addData(handleArgPropertyData(thisChild));
                    }
                    break;
            }
        }

        return propData;
    }

    @Override
    public void initJob(STAXJob job) {

    }

    @Override
    public void terminateJob(STAXJob job) {

    }
}
