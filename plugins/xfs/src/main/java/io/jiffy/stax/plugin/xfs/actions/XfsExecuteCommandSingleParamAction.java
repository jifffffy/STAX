package io.jiffy.stax.plugin.xfs.actions;

import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXThread;

public abstract class XfsExecuteCommandSingleParamAction extends XfsExecuteCommandAction {

    private String param = "";

    @Override
    public String getParameter() {
        return param;
    }

    @Override
    public void setParameter(String param) {
        this.param = param;
    }

    @Override
    public String createRequest(STAXThread thread) throws STAXPythonEvaluationException {
        String parameter = thread.pyStringEval(getParameter());
        if (parameter.equalsIgnoreCase("none")) {
            parameter = "";
        }
        return createCommand() + " " + parameter + handleEvents(thread);
    }
}
