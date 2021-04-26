package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;


public class OpenAction extends XfsCommandAction {

    private String logicalName;

    @Override
    public String createRequest(STAXThread thread) throws STAXPythonEvaluationException{
        return "open logicalName " + thread.pyStringEval(getLogicalName());
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        OpenAction clone = new OpenAction();
        clone.setLogicalName(getLogicalName());
        return clone;
    }


    public String getLogicalName() {
        return logicalName;
    }

    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }
}
