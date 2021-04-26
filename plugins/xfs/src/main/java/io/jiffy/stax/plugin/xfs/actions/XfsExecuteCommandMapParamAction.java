package io.jiffy.stax.plugin.xfs.actions;

import com.ibm.staf.STAFUtil;
import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXThread;

public abstract class XfsExecuteCommandMapParamAction extends XfsExecuteCommandAction {

    private String paramMap;

    public XfsExecuteCommandMapParamAction() {
    }


    /**
     * 可以处理Map类型的参数
     *
     * @param thread
     * @return
     * @throws STAXPythonEvaluationException
     */
    @Override
    public String createRequest(STAXThread thread) throws STAXPythonEvaluationException {
        String temp = getParameter();
        if (temp == null) {
            return createCommand();
        }
        thread.pySetVar("xfsExecuteCommandParamMap", temp);
        thread.pyExec("xfsExecuteCommandParamMapRequest = json.dumps(xfsExecuteCommandParamMap)");
        return createCommand() + " " + STAFUtil.wrapData(normalizeRequest(thread.pyStringEval("xfsExecuteCommandParamMapRequest")));
    }


    @Override
    public String getParameter() {
        return paramMap;
    }

    @Override
    public void setParameter(String param) {
        this.paramMap = param;
    }
}
