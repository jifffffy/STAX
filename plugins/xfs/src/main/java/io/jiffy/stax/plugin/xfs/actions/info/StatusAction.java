package io.jiffy.stax.plugin.xfs.actions.info;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class StatusAction extends XfsCommandAction {
    public StatusAction() {
    }

    @Override
    public String createRequest(STAXThread thread) {
        return "status";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new StatusAction();
    }
}
