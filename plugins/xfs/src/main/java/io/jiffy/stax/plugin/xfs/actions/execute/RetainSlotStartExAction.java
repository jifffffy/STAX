package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class RetainSlotStartExAction extends XfsExecuteCommandMapParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        RetainSlotStartExAction clone = new RetainSlotStartExAction();
        clone.setParameter(getParameter());
        clone.setEvents(getEvents());
        return clone;
    }

    @Override
    public String createCommand() {
        return "retainSlotStartEx retainSlotStartIn";
    }

}
