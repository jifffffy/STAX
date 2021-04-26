package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandSingleParamAction;

public class OpenShutterAction extends XfsExecuteCommandSingleParamAction {
    @Override
    public STAXSTAFCommandAction createClone() {
        OpenShutterAction clone = new OpenShutterAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    public String createCommand() {
        return "openShutter";
    }
}
