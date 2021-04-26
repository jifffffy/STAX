package io.jiffy.stax.plugin.xfs.actions.info;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class CapabilitiesAction extends XfsCommandAction {

    public CapabilitiesAction() {
    }

    @Override
    public String createRequest(STAXThread thread) {
        return "capabilities";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new CapabilitiesAction();
    }

}
