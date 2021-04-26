package io.jiffy.stax.plugin.xfs.actions.info;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class CurrencyExpAction extends XfsCommandAction {
    public CurrencyExpAction() {
    }

    @Override
    public String createRequest(STAXThread thread) {
        return "currencyExp";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new CurrencyExpAction();
    }


}
