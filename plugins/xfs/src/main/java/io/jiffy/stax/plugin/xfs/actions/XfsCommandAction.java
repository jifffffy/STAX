package io.jiffy.stax.plugin.xfs.actions;

import com.ibm.staf.service.stax.*;
import org.pmw.tinylog.Logger;

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
