package io.jiffy.stax.plugin.xfs.actions.info;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class SlotsInfoAction extends XfsCommandAction {

    public SlotsInfoAction() {
    }

    @Override
    public String createRequest(STAXThread thread) {
        return "slotsInfo";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new SlotsInfoAction();
    }

}
