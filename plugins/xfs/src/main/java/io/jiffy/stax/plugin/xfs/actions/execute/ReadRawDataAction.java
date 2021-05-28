package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandSingleParamAction;

public class ReadRawDataAction extends XfsExecuteCommandSingleParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        ReadRawDataAction clone = new ReadRawDataAction();
        clone.setParameter(getParameter());
        clone.setEvents(getEvents());
        return clone;
    }

    @Override
    public String createCommand() {
        return "readRawData dataSources";
    }
}
