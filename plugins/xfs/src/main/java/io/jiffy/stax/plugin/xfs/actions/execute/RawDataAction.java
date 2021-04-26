package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class RawDataAction extends XfsExecuteCommandMapParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        RawDataAction clone = new RawDataAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    public String createCommand() {
        return "rawData";
    }

}
