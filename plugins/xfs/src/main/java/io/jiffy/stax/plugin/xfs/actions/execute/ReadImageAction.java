package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class ReadImageAction extends XfsExecuteCommandMapParamAction {
    @Override
    public STAXSTAFCommandAction createClone() {
        ReadImageAction clone = new ReadImageAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    public String createCommand() {
        return "readImage";
    }
}
