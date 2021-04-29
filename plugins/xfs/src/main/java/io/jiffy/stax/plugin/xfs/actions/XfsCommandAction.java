package io.jiffy.stax.plugin.xfs.actions;

import com.ibm.staf.STAFResult;
import com.ibm.staf.STAFUtil;
import com.ibm.staf.service.stax.*;
import org.pmw.tinylog.Logger;

import java.util.HashMap;

/**
 * 使用STAXSTAFCommandAction
 * machine = local, 目前只支持local
 * service = xfs
 */
abstract public class XfsCommandAction extends STAXSTAFCommandAction {

    private String xfsService;

    public XfsCommandAction() {
        fUnevalLocation = "'local'";
        fUnevalService = "'xfs'";
    }

    @Override
    public synchronized void execute(STAXThread thread) {
        if (xfsService == null) {
            String request = null;
            try {
                xfsService = thread.pyStringEval("XFSCurrentService");
                request = createRequest(thread);
            } catch (STAXPythonEvaluationException e) {
                Logger.error(e);
                throwSignal(thread,
                        new STAXElementInfo(
                                STAXElementInfo.NO_ELEMENT_NAME,
                                STAXElementInfo.NO_ATTRIBUTE_NAME),
                        "无法获取XFSCurrentService或创建Request失败!",
                        "STAXPythonEvaluationError",
                        e);
                return;
            }

            fUnevalRequest = "'" + xfsService + " " + request + "'";
            Logger.info("request = {}", fUnevalRequest);
        }
        // execute
        super.execute(thread);
    }

    public abstract String createRequest(STAXThread thread) throws STAXPythonEvaluationException;


    /**
     * overwrite requestComplete
     * 添加一个功能: 动态添加下面的action
     * <tcstatus result="'fail'"/>
     * <return>[RC, STAFResult]</return>
     * 这样可以使脚本的业务逻辑代码更紧凑
     */
    @Override
    public void doComplete(STAXThread thread, int RC) {
        if(RC != 0) {
            String testcaseName = null;
            try {
                testcaseName = thread.pyStringEval("STAXCurrentTestcase");
            } catch (STAXPythonEvaluationException e) {
                // ignore
                Logger.error(e);
            }
            // 只在testcase tag 下添加tcstatus tag
            if (testcaseName != null && !testcaseName.equals("None")){
                STAXTestcaseStatusAction testcaseStatusAction = new STAXTestcaseStatusAction();
                testcaseStatusAction.setStatus("'fail'");
                thread.pushAction(testcaseStatusAction);
            }

            STAXReturnAction returnAction = new STAXReturnAction();
            returnAction.setValue("[RC, STAFResult]");
            thread.pushAction(returnAction);
        }
    }

    protected String normalizeRequest(String request) {
        return request
                .replace("\"", "")
                .replace("\\n", "")
                .replace("\'", "\"");
    }

    public abstract STAXSTAFCommandAction createClone();

    @Override
    public STAXAction cloneAction() {
        STAXSTAFCommandAction clone = createClone();
        clone.setElement(getElement());
        clone.setLineNumberMap(getLineNumberMap());
        clone.setXmlFile(getXmlFile());
        clone.setXmlMachine(getXmlMachine());
        return clone;
    }

}
