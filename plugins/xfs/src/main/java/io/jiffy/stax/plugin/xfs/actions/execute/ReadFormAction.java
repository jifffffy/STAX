package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class ReadFormAction extends XfsExecuteCommandMapParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        ReadFormAction clone = new ReadFormAction();
        clone.setParameter(getParameter());
        clone.setEvents(getEvents());
        return clone;
    }

    @Override
    public String createCommand() {
        return "readForm";
    }

}
