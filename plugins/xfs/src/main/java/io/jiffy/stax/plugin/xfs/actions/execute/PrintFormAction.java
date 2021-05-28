package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class PrintFormAction extends XfsExecuteCommandMapParamAction {


    @Override
    public STAXSTAFCommandAction createClone() {
        PrintFormAction clone = new PrintFormAction();
        clone.setParameter(getParameter());
        clone.setEvents(getEvents());
        return clone;
    }


    @Override
    public String createCommand() {
        return "printForm";
    }

}
