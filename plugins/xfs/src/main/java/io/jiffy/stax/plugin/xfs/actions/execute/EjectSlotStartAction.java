package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class EjectSlotStartAction extends XfsCommandAction {

    private String type;
    private String cmdData;

    @Override
    public String createRequest(STAXThread thread) throws STAXPythonEvaluationException {
        return "ejectSlotStart type " + getType() + " cmdData " + getCmdData();
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        EjectSlotStartAction clone = new EjectSlotStartAction();
        clone.setCmdData(getCmdData());
        clone.setType(getType());
        return clone;
    }

    public String getType() {
        return type;
    }

    public String getCmdData() {
        return cmdData;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCmdData(String cmdData) {
        this.cmdData = cmdData;
    }
}
