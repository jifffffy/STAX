package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXPythonEvaluationException;
import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class ControlMediaAction extends XfsCommandAction {

    private String medialControl;

    @Override
    public String createRequest(STAXThread thread) throws STAXPythonEvaluationException {
        return "controlMedia mediaControl " + thread.pyStringEval(getMedialControl());
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        ControlMediaAction controlMediaAction = new ControlMediaAction();
        controlMediaAction.setMedialControl(getMedialControl());
        return controlMediaAction;
    }

    public String getMedialControl() {
        return medialControl;
    }

    public void setMedialControl(String medialControl) {
        this.medialControl = medialControl;
    }
}
