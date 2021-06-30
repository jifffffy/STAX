package io.jiffy.stax.plugin.pe.factory;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.action.ComAction;
import org.pf4j.Extension;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class ComActionFactory extends ActionFactorySupport<ComAction> {

    public ComActionFactory() {
        super("com", ComAction.class);
    }

    @Override
    public void initialize(STAX stax) {

    }

    @Override
    protected void handleTextNode(STAX staxService, STAXJob job, ComAction action, Node root, Node child) throws STAXException {
        action.setElementInfo(new STAXElementInfo(root.getNodeName()));
        action.setValue(root.getTextContent());
    }

    @Override
    protected void handleRootAttributes(STAX staxService, STAXJob job, ComAction action, Node root, NamedNodeMap attrs) throws STAXException {
        Node portNode = attrs.getNamedItem("port");
        Node readTimeoutNode = attrs.getNamedItem("readTimeout");
        Node writeTimeoutNode = attrs.getNamedItem("writeTimeout");

        if(portNode != null) {
            action.setPort(Integer.parseInt(portNode.getNodeValue()));
        }

        if(readTimeoutNode != null) {
            action.setPort(Integer.parseInt(readTimeoutNode.getNodeValue()));
        }

        if(writeTimeoutNode != null) {
            action.setPort(Integer.parseInt(writeTimeoutNode.getNodeValue()));
        }
    }
}
