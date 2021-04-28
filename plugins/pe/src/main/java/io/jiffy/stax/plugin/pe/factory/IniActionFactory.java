package io.jiffy.stax.plugin.pe.factory;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.action.IniAction;
import org.pf4j.Extension;
import org.pmw.tinylog.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.function.Consumer;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class IniActionFactory extends ActionFactorySupport<IniAction> {

    public IniActionFactory() {
        super("ini", IniAction.class);
    }

    @Override
    public void initialize(STAX stax) {
    }

    @Override
    protected void handleRootAttributes(STAX staxService, STAXJob job, IniAction action, Node root, NamedNodeMap attrs) throws STAXException {
        for (int i = 0; i < attrs.getLength(); ++i) {
            Node thisAttr = attrs.item(i);

            String attrName = thisAttr.getNodeName();
            action.setElementInfo(new STAXElementInfo(root.getNodeName(), attrName));

            if (thisAttr.getNodeName().equals("path")) {
                action.setPath(thisAttr.getNodeValue());
            }
        }
    }

    @Override
    protected void handleChildNode(STAX staxService, STAXJob job, IniAction action, Node root, Node child) throws STAXException {
        action.setAction(child.getNodeName());
        NodeList sectionNodes = child.getChildNodes();
        for (int j = 0; j < sectionNodes.getLength(); ++j) {
            Node sectionTag = sectionNodes.item(j);
            setTagNameAttr(action, child, sectionTag, "section", (section) -> action.setSection(section.getNodeValue()));
            NodeList optionNodes = sectionTag.getChildNodes();
            for (int k = 0; k < optionNodes.getLength(); ++k) {
                Node optionTag = optionNodes.item(k);
                setTagNameAttr(action, sectionTag, optionTag, "option", (option) -> {
                    action.setOption(option.getNodeValue());
                    if(child.getNodeName().equalsIgnoreCase("update")) {
                        action.setValue(optionTag.getTextContent());
                    }
                });
            }
        }
    }

    private void setTagNameAttr(IniAction action, Node root, Node child, String tag, Consumer<Node> consumer) throws STAXInvalidXMLNodeTypeException, STAXPythonCompileException {
        if (!child.getNodeName().equalsIgnoreCase(tag)) {
            action.setElementInfo(new STAXElementInfo(root.getNodeName(), STAXElementInfo.NO_ATTRIBUTE_NAME, STAXElementInfo.LAST_ELEMENT_INDEX, child.getNodeName() + "必须提供" +  tag));
            throw new STAXInvalidXMLNodeTypeException(STAXUtil.formatErrorMessage(action), action);
        }
        Node nameNode = child.getAttributes().getNamedItem("name");
        if (nameNode == null) {
            action.setElementInfo(new STAXElementInfo(root.getNodeName(), STAXElementInfo.NO_ATTRIBUTE_NAME, STAXElementInfo.LAST_ELEMENT_INDEX, "必须提供name属性"));
            throw new STAXInvalidXMLNodeTypeException(STAXUtil.formatErrorMessage(action), action);
        }
        consumer.accept(nameNode);
    }
}
