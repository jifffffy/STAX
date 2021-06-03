package io.jiffy.stax.plugin.pe.action;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.Constants;
import io.jiffy.stax.plugin.pe.model.Dialog;
import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;

public class DialogAction extends STAXActionDefaultImpl {

    public State state = State.INIT;

    private String description;
    private String image;

    private final STAXHoldThreadCondition holdThreadCondition = new STAXHoldThreadCondition();
    private STAXThread ownThread;

    public enum State {
        INIT, YES, NO
    }

    public synchronized void changeState(DialogAction.State state, Dialog dialog) {
        this.state = state;
        dialog.setVisible(false);
        dialog.dispose();
        ownThread.removeCondition(holdThreadCondition);
        ownThread.schedule();
    }

    @Override
    public void execute(STAXThread thread) {
        switch (state) {
            case INIT:
                this.ownThread = thread;
                String label = "";
                String desc = "";
                try {
                    label = thread.pyStringEval("STAXCurrentTestcase");
                    desc = thread.pyStringEval(getDescription());
                } catch (STAXPythonEvaluationException e) {
                    Logger.error(e);
                }

                new Dialog(label, desc,this, image).setVisible(true);
                thread.addCondition(holdThreadCondition);
                break;
            case YES:
                thread.pySetVar(Constants.RC, 0);
                thread.popAction();
                break;
            case NO:
                thread.pySetVar(Constants.RC, -1);
                thread.popAction();
                break;
        }
    }

    @Override
    public STAXAction cloneAction() {
        DialogAction clone = new DialogAction();

        clone.setElement(getElement());
        clone.setLineNumberMap(getLineNumberMap());
        clone.setXmlFile(getXmlFile());
        clone.setXmlMachine(getXmlMachine());

        clone.setDescription(getDescription());
        clone.setImage(getImage());
        return clone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
