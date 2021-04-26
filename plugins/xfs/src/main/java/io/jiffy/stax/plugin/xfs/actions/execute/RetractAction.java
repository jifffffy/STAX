package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class RetractAction extends XfsExecuteCommandMapParamAction {


    @Override
    public STAXSTAFCommandAction createClone() {
        RetractAction clone = new RetractAction();
        clone.setParameter(getParameter());
        return clone;
    }


    @Override
    public String createCommand() {
        return "retract";
    }

}
