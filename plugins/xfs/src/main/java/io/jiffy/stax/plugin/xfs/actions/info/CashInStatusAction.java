package io.jiffy.stax.plugin.xfs.actions.info;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class CashInStatusAction extends XfsCommandAction {

    public CashInStatusAction() {
    }

    @Override
    public String createRequest(STAXThread thread) {
        return "cashInStatus";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new CashInStatusAction();
    }

}
