package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class SetCardUnitInfoAction extends XfsExecuteCommandMapParamAction {
    @Override
    public STAXSTAFCommandAction createClone() {
        SetCardUnitInfoAction clone = new SetCardUnitInfoAction();
        clone.setParameter(getParameter());
        clone.setEvents(getEvents());
        return clone;
    }

    @Override
    public String createCommand() {
        return "setCardUnitInfo cardUnitInfo";
    }
}
