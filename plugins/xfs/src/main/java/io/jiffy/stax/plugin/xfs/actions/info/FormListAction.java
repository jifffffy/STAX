package io.jiffy.stax.plugin.xfs.actions.info;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public class FormListAction extends XfsCommandAction {
    public FormListAction() {
    }

    @Override
    public String createRequest(STAXThread thread) {
        return "formList";
    }

    @Override
    public STAXSTAFCommandAction createClone() {
        return new FormListAction();
    }


}
