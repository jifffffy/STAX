package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandListParamAction;

public class FdkKeysAction extends XfsExecuteCommandListParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        FdkKeysAction clone = new FdkKeysAction();
        clone.setParameter(getParameter());
        clone.setEvents(getEvents());
        return clone;
    }

    @Override
    protected String prefix() {
        return "keys";
    }

    @Override
    public String createCommand() {
        return "fdkKeys";
    }
}
