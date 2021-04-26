package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandSingleParamAction;

public class CloseShutterAction extends XfsExecuteCommandSingleParamAction {
    @Override
    public STAXSTAFCommandAction createClone() {
        CloseShutterAction clone = new CloseShutterAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    public String createCommand() {
        return "closeShutter";
    }
}
