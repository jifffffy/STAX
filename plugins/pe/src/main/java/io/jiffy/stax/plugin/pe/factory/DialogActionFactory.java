package io.jiffy.stax.plugin.pe.factory;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.action.DialogAction;
import org.pf4j.Extension;
import org.pmw.tinylog.Logger;
import org.w3c.dom.Node;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class DialogActionFactory extends ActionFactorySupport<DialogAction> {

    public DialogActionFactory() {
        super("dialog", DialogAction.class);
    }

    @Override
    public void initialize(STAX stax) {
    }

    @Override
    protected void handleTextNode(STAX staxService, STAXJob job, DialogAction action, Node root, Node child) throws STAXException {
        action.setElementInfo(new STAXElementInfo(root.getNodeName()));
        action.setDescription(root.getTextContent());
    }
}
