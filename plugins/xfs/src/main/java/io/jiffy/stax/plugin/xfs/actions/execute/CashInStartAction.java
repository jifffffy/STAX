package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandSingleParamAction;

public class CashInStartAction extends XfsExecuteCommandMapParamAction {
    @Override
    public STAXSTAFCommandAction createClone() {
        CashInStartAction clone = new CashInStartAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    public String createCommand() {
        return "cashInStart";
    }
}
