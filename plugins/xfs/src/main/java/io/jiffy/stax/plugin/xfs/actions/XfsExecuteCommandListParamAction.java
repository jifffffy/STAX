package io.jiffy.stax.plugin.xfs.actions;

import com.ibm.staf.STAFUtil;
import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXThread;

import java.util.List;
import java.util.stream.Collectors;

public abstract class XfsExecuteCommandListParamAction extends XfsExecuteCommandAction {

    private String paramList;

    @Override
    public String createRequest(STAXThread thread) throws STAXPythonEvaluationException {
        String request = ((List<String>)thread.pyListEval(getParameter()))
                .stream()
                .map(param -> prefix() + " " + param + " " + suffix())
                .collect(Collectors.joining(" "));
        return createCommand() + " " + request;
    }

    protected   String prefix() { return ""; };
    protected   String suffix() { return ""; };

    @Override
    public String getParameter() {
        return paramList;
    }

    @Override
    public void setParameter(String param) {
        paramList = param;
    }
}
