package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandListParamAction;

public class ActionKeysAction extends XfsExecuteCommandListParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        ActionKeysAction clone = new ActionKeysAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    protected String prefix() {
        return "keys";
    }

    @Override
    public String createCommand() {
        return "actionKeys";
    }
}
