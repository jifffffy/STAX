package io.jiffy.stax.plugin.xfs.actions;

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
        String pythonCode = getParameter();
        if (pythonCode == null) {
            return createCommand();
        }
        // 构造python script
        thread.pyExec("xfsExecuteCommandParamMapRequest = STAFUtil.wrapData(json.dumps(" + pythonCode + "))");
        return createCommand() + " " + thread.pyStringEval("xfsExecuteCommandParamMapRequest") + handleEvents(thread);
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
