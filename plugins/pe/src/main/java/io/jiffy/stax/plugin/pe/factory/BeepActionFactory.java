package io.jiffy.stax.plugin.pe.factory;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.action.BeepAction;
import org.pf4j.Extension;
import org.pmw.tinylog.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class BeepActionFactory extends ActionFactorySupport<BeepAction> {

    public BeepActionFactory() {
        super("beep", BeepAction.class);
    }

    @Override
    public void initialize(STAX stax) {
    }

    @Override
    protected void handleRootAttributes(STAX staxService, STAXJob job, BeepAction action, Node root, NamedNodeMap attrs) throws STAXException {
        Node hzNode = attrs.getNamedItem("hz");
        Node msecsNode = attrs.getNamedItem("msecs");
        Node volNode = attrs.getNamedItem("vol");

        if(hzNode == null || msecsNode == null || volNode == null) {
            action.setElementInfo(
                    new STAXElementInfo(root.getNodeName(),
                            STAXElementInfo.NO_ATTRIBUTE_NAME,
                            STAXElementInfo.LAST_ELEMENT_INDEX,
                            root.getNodeName()));
            Logger.error("{} 必须包含hz、msecs、vol属性", action.getElement());
            throw new STAXInvalidXMLElementException(STAXUtil.formatErrorMessage(action), action);
        }
        action.setHz(Integer.parseInt(hzNode.getNodeValue()));
        action.setMsecs(Integer.parseInt(msecsNode.getNodeValue()));
        action.setVol(Double.parseDouble(volNode.getNodeValue()));
    }
}
