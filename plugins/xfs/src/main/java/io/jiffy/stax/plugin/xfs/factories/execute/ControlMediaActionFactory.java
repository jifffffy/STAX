package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.execute.ControlMediaAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;
import org.w3c.dom.Node;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class ControlMediaActionFactory extends XfsCommandActionFactory<ControlMediaAction> {

    public ControlMediaActionFactory() {
        super("control-media", ControlMediaAction.class);
    }

    @Override
    protected void handleTextNode(STAX staxService, STAXJob job, ControlMediaAction action, Node root, Node child) throws STAXException {
        action.setElementInfo(new STAXElementInfo(root.getNodeName()));
        action.setMedialControl(STAXUtil.parseAndCompileForPython(child.getNodeValue(), action));
    }
}
