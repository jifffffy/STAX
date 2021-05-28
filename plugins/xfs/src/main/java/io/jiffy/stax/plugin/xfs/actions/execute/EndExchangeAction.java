package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class EndExchangeAction extends XfsExecuteCommandMapParamAction {


    @Override
    public STAXSTAFCommandAction createClone() {
        EndExchangeAction clone = new EndExchangeAction();
        clone.setParameter(getParameter());
        clone.setEvents(getEvents());
        return clone;
    }


    @Override
    public String createCommand() {
        return "endExchange cashUnitInfo";
    }

}
