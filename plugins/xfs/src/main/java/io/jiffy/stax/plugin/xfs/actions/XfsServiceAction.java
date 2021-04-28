package io.jiffy.stax.plugin.xfs.actions;

import com.ibm.staf.service.stax.*;
import org.pmw.tinylog.Logger;
import org.python.core.Py;

import java.util.HashMap;

public class XfsServiceAction extends STAXActionDefaultImpl {

    static final int INIT = 0;
    static final int ACTION_CALLED = 1;
    static final int COMPLETE = 2;

    static final String XFS_SERVICE_EVENT = "xfs";

    // Xfs service(eg. ptr, cim)下面可以包括任何STAXAction, 但是只能包括一个，
    // 一般是STAXSequenceAction
    private STAXAction action;
    private String xfsServiceName;
    private int state = INIT;


    public XfsServiceAction(String xfsServiceName) {
        this.xfsServiceName = xfsServiceName;
    }

    /**
     * 维护两个变量 XFSCurrentService XFSServiceStack
     *
     * @param thread
     */
    @Override
    public void execute(STAXThread thread) {
        switch (state) {
            case INIT:

                if (action == null) {
                    handleException(thread, "XFS Service element must has a action ", null);
                    return;
                }

                // set STAXCurrentBlock, STAXBlockStack
                try {
                    thread.pySetVar("XFSCurrentService", xfsServiceName);
                    thread.pyExec("XFSServiceStack.append(XFSCurrentService)");
                } catch (STAXPythonEvaluationException e) {
                    handleException(thread, "XFSServiceAction: Enter  " + xfsServiceName + " failed with " + e.toString(), e);
                    return;
                }

                HashMap<String, String> xfsServiceBeginMap = new HashMap<>();
                xfsServiceBeginMap.put("type", "XfsService");
                xfsServiceBeginMap.put("status", "begin");
                xfsServiceBeginMap.put("service", xfsServiceName);

                thread.getJob().generateEvent(XFS_SERVICE_EVENT, xfsServiceBeginMap);
                // push the clone action and execute the clone action since the top of actionStack is this clone action
                thread.pushAction(action.cloneAction());
                state = ACTION_CALLED;
                break;
            case ACTION_CALLED:
                HashMap<String, String> xfsServiceEndMap = new HashMap<>();
                xfsServiceEndMap.put("type", "XfsService");
                xfsServiceEndMap.put("status", "end");
                xfsServiceEndMap.put("service", xfsServiceName);

                thread.getJob().generateEvent(XFS_SERVICE_EVENT, xfsServiceEndMap);

                state = COMPLETE;
                thread.pySetVar("XFSServiceRC", STAXJob.NORMAL_STATUS);

                // Exit Xfs Service
                exitXfsService(xfsServiceName, thread);
                thread.popAction();
                break;
        }

    }

    @Override
    public void handleCondition(STAXThread thread, STAXCondition condition) {
        thread.popAction();
    }

    private void handleException(STAXThread thread, String message, STAXPythonEvaluationException e) {
        state = COMPLETE;
        thread.popAction();
        // Set RC and STAFResult variables when done
        thread.pySetVar("XFSServiceRC", STAXJob.ABNORMAL_STATUS);
        Logger.error(message);
        if (e != null) {
            Logger.error(e);
        }
        thread.getJob().log(STAXJob.JOB_LOG, "error", message);
    }

    private void exitXfsService(String xfsServiceName, STAXThread thread) {
        try {
            if (thread.pyBoolEval("XFSServiceStack[len(XFSServiceStack)-1] != " + "XFSCurrentService")) {
                Logger.error("XFSServiceAction: Exit Service " + xfsServiceName + " failed.  This service is not on the XFSServiceStack");
            } else {
                thread.pyExec("XFSServiceStack.pop()");
                if (thread.pyBoolEval("len(XFSServiceStack) > 0")) {
                    thread.pyExec("XFSCurrentService = " + "XFSServiceStack[len(XFSServiceStack)-1]");
                } else {
                    thread.pySetVar("XFSCurrentService", Py.None);
                }
            }
        } catch (STAXPythonEvaluationException e) {
            Logger.error(e);
        }
    }


    @Override
    public STAXAction cloneAction() {
        XfsServiceAction clone = null;
        try {
            clone = new XfsServiceAction(xfsServiceName);
            clone.setElement(getElement());
            clone.setLineNumberMap(getLineNumberMap());
            clone.setXmlFile(getXmlFile());
            clone.setXmlMachine(getXmlMachine());
            clone.setAction(action);
        } catch (Exception e) {
            Logger.error(e);
        }
        return clone;
    }

    public STAXAction getAction() {
        return action;
    }

    public void setAction(STAXAction action) {
        this.action = action;
    }
}
