package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.execute.OpenAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;
import org.w3c.dom.Node;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class OpenActionFactory extends XfsCommandActionFactory<OpenAction> {
    public OpenActionFactory() {
        super("open", OpenAction.class);
    }


    @Override
    protected void handleTextNode(STAX staxService, STAXJob job, OpenAction action, Node root, Node child) throws STAXException {
        action.setElementInfo(new STAXElementInfo(root.getNodeName()));
        action.setLogicalName(STAXUtil.parseAndCompileForPython(child.getNodeValue(), action));
    }
}
