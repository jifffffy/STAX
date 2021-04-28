package io.jiffy.stax.plugin.pe.action;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.Constants;
import org.apache.commons.lang3.StringUtils;
import org.ini4j.Config;
import org.ini4j.Wini;
import org.pmw.tinylog.Logger;


import java.io.*;
import java.nio.charset.Charset;

public class IniAction extends STAXActionDefaultImpl {

    private String path;
    private String section;
    private String action;
    private String option;
    private String value;

    private Wini wini;

    enum State {
        INIT, RUNNING, COMPLETE
    }

    private State state = State.INIT;

    public IniAction() {
    }


    @Override
    public void execute(STAXThread thread) {
        switch (state) {
            case INIT:
                if (!valid()) {
                    handleError(thread, "INI初始化失败!", null);
                    return;
                }

                try {
                    setPath(thread.pyStringEval(getPath()));
                } catch (STAXPythonEvaluationException e) {
                    throwSignal(thread, new STAXElementInfo(getElement()), "path不是有效的python code", Constants.INI_ERROR, e);
                }
                try {
                    Config config = new Config();
                    config.setFileEncoding(Charset.forName("gb2312"));
                    config.setEscape(false);
                    wini = new Wini(new BufferedReader(new InputStreamReader(new FileInputStream(getPath()))));
                    wini.setConfig(config);
                } catch (Exception e) {
                    handleError(thread, "INI加载失败!", e);
                    return;
                }
                state = State.RUNNING;
                break;
            case RUNNING:
                switch (action) {
                    case "query":
                        String result = wini.get(getSection(), getOption());
                        thread.pySetVar(Constants.RESULT, result);
                        thread.pySetVar(Constants.RC, 0);
                        break;
                    case "update":
                        if (StringUtils.isEmpty(getValue())) {
                            handleError(thread, "必须提供option值!", null);
                            return;
                        }
                        String updateValue = null;
                        try {
                            updateValue = thread.pyStringEval(getValue());
                        } catch (STAXPythonEvaluationException e) {
                            handleError(thread, "无效的" + getValue(), e);
                            return;
                        }
                        Logger.info("update {} {} {}", getSection(), getOption(), updateValue);
                        wini.put(getSection(), getOption(), updateValue);
                        try {
                            wini.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.path))));
                            thread.pySetVar(Constants.RC, 0);
                        } catch (IOException e) {
                            handleError(thread, "路径错误:" + getPath(), e);
                            return;
                        }
                        break;
                    case "remove":
                        wini.remove(getSection(), getOption());
                        try {
                            wini.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.path))));
                            thread.pySetVar(Constants.RC, 0);
                        } catch (IOException e) {
                            handleError(thread, "路径错误:" + getPath(), e);
                            return;
                        }
                        break;
                    default:
                        throw new RuntimeException("不支持" + action + "操作");
                }

                state = State.COMPLETE;
                break;
            case COMPLETE:
                thread.pySetVar("RC", 0);
                thread.popAction();
                break;
        }

    }


    private void handleError(STAXThread thread, String msg, Exception e) {
        if (e != null) {
            Logger.error(e);
        }
        setElementInfo(new STAXElementInfo(getElement()));
        thread.popAction();
        thread.pySetVar(Constants.RC, -1);
        thread.pySetVar(Constants.RESULT, msg);
        thread.setSignalMsgVar(Constants.INI_ERROR, STAXUtil.formatErrorMessage(this));
        thread.raiseSignal(Constants.INI_ERROR);
    }


    @Override
    public STAXAction cloneAction() {
        IniAction clone = new IniAction();

        clone.setElement(getElement());
        clone.setLineNumberMap(getLineNumberMap());
        clone.setXmlFile(getXmlFile());
        clone.setXmlMachine(getXmlMachine());

        clone.setPath(getPath());
        clone.setAction(getAction());
        clone.setSection(getSection());
        clone.setOption(getOption());
        clone.setValue(getValue());
        return clone;
    }

    public boolean valid() {
        // Logger.info("path = {}, action={}, section={}, option={}", getPath(),getAction(),getSection(),getOption());
        return StringUtils.isNotEmpty(getPath()) &&
                StringUtils.isNotEmpty(getAction()) &&
                StringUtils.isNotEmpty(getSection()) &&
                StringUtils.isNotEmpty(getOption());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
