package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class ResetCountAction extends XfsCommandAction {
    @Override
    public String createRequest(STAXThread thread) throws STAXPythonEvaluationException {
        return "resetCount";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new ResetCountAction();
    }
}
