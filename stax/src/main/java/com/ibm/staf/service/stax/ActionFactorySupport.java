package com.ibm.staf.service.stax;

import org.pmw.tinylog.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class ActionFactorySupport<T extends STAXActionDefaultImpl> extends ActionFactoryExtensionPointAdapter {

    private Class<T> clazz;

    public ActionFactorySupport(String name, Class<T> clazz) {
        super(name);
        this.clazz = clazz;
    }

    @Override
    public STAXAction parseAction(STAX staxService, STAXJob job, Node root) throws STAXException {
        T staxAction = createAction();

        staxAction.setLineNumber(root);
        staxAction.setXmlFile(job.getXmlFile());
        staxAction.setXmlMachine(job.getXmlMachine());

        handleActionAttributes(staxService, job, staxAction, root.getAttributes());
        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);

            switch (child.getNodeType()) {
                case Node.COMMENT_NODE:
                    handleCommentNode(staxService, job, staxAction, root, child);
                    break;
                case Node.ELEMENT_NODE:
                    handleChildNode(staxService, job, staxAction, root, child);
                    break;
                case Node.TEXT_NODE:
                    handleTextNode(staxService, job, staxAction, root, child);
                    break;
                default:
                    staxAction.setElementInfo(
                            new STAXElementInfo(
                                    root.getNodeName(),
                                    STAXElementInfo.NO_ATTRIBUTE_NAME,
                                    STAXElementInfo.LAST_ELEMENT_INDEX,
                                    "Contains invalid node type: " + Integer.toString(child.getNodeType())));

                    throw new STAXInvalidXMLNodeTypeException(STAXUtil.formatErrorMessage(staxAction), staxAction);
            }
        }

        return staxAction.cloneAction();
    }

    /**
     * 所有xfs action都使用默认构造方法构造
     * @return
     * @throws STAXException
     */
    public T createAction() throws STAXException{
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            Logger.error(e);
            throw new STAXException("无法实例化" + clazz.getName());
        }
    }

    protected void handleActionAttributes(STAX staxService, STAXJob job, T action, NamedNodeMap attrs) {
    }

    protected void handleCommentNode(STAX staxService, STAXJob job, T action, Node root, Node child) {
    }

    protected void handleTextNode(STAX staxService, STAXJob job, T action, Node root, Node child) throws STAXException {
    }

    protected void handleChildNode(STAX staxService, STAXJob job, T action, Node root, Node child) throws STAXException {
    }
}
