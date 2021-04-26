package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class ImportKeyAction extends XfsExecuteCommandMapParamAction {


    @Override
    public STAXSTAFCommandAction createClone() {
        ImportKeyAction clone = new ImportKeyAction();
        clone.setParameter(getParameter());
        return clone;
    }


    @Override
    public String createCommand() {
        return "importKey";
    }

}
