package io.jiffy.stax.plugin.xfs.actions.info;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class CardUnitInfoAction extends XfsCommandAction {
    public CardUnitInfoAction() {
    }

    @Override
    public String createRequest(STAXThread thread) {
        return "getCardUnitInfo";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new CardUnitInfoAction();
    }


}
