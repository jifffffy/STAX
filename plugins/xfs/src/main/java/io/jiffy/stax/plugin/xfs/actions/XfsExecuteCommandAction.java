package io.jiffy.stax.plugin.xfs.actions;

import com.ibm.staf.service.stax.STAXAction;
import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXThread;
import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;

import java.util.List;
import java.util.stream.Collectors;

public abstract class XfsExecuteCommandAction extends XfsCommandAction {

    private String events;

    public abstract String getParameter();

    public abstract void setParameter(String param);

    public abstract String createCommand();

    protected String handleEvents(STAXThread thread) throws STAXPythonEvaluationException {

        Logger.info("events = {}", events);

        if(StringUtils.isEmpty(events)) {
            return "";
        }

        List<String> eventList = thread.pyListEval(events);
        return eventList.size() > 0 ? eventList.stream().map(e -> " events " + e).collect(Collectors.joining()) : "";
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getEvents() {
        return events;
    }
}