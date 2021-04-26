package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class RetainCardAction extends XfsExecuteCommandMapParamAction {


    @Override
    public STAXSTAFCommandAction createClone() {
        RetainCardAction clone = new RetainCardAction();
        clone.setParameter(getParameter());
        return clone;
    }


    @Override
    public String createCommand() {
        return "retainCard";
    }

}
