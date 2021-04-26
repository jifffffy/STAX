package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class DispenseAction extends XfsExecuteCommandMapParamAction {


    @Override
    public STAXSTAFCommandAction createClone() {
        DispenseAction clone = new DispenseAction();
        clone.setParameter(getParameter());
        return clone;
    }


    @Override
    public String createCommand() {
        return "dispense";
    }

}
