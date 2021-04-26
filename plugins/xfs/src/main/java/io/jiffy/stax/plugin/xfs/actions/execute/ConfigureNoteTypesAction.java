package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandMapParamAction;

public class ConfigureNoteTypesAction extends XfsExecuteCommandMapParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        ConfigureNoteTypesAction clone = new ConfigureNoteTypesAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    public String createCommand() {
        return "configureNoteTypes";
    }
}
