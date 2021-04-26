package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class ReadInputAction extends XfsExecuteCommandMapParamAction {
    @Override
    public STAXSTAFCommandAction createClone() {
        ReadInputAction clone = new ReadInputAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    public String createCommand() {
        return "read readInput";
    }
}
