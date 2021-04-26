package io.jiffy.stax.plugin.xfs.factories;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandAction;
import org.w3c.dom.Node;

public abstract class XfsExecuteCommandActionFactory<T extends XfsExecuteCommandAction> extends XfsCommandActionFactory<T>{

    public XfsExecuteCommandActionFactory(String name, Class<T> clazz) {
        super(name, clazz);
    }

    @Override
    protected void handleTextNode(STAX staxService, STAXJob job, T action, Node root, Node child) throws STAXException {
        action.setElementInfo(new STAXElementInfo(root.getNodeName()));
        action.setParameter(STAXUtil.parseAndCompileForPython(child.getNodeValue(), action));
    }
}
