package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class EjectCardAction extends XfsExecuteCommandMapParamAction {


    @Override
    public STAXSTAFCommandAction createClone() {
        EjectCardAction clone = new EjectCardAction();
        clone.setParameter(getParameter());
        return clone;
    }


    @Override
    public String createCommand() {
        return "ejectCard";
    }

}
