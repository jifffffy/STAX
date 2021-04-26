package io.jiffy.stax.plugin.xfs.actions.execute;

import com.ibm.staf.service.stax.STAXSTAFCommandAction;
import io.jiffy.stax.plugin.xfs.actions.XfsExecuteCommandListParamAction;

/**
 * TODO 等待事件的Action
 * <on>['SRVE_PTR_MEDIAINSERTED', 'SRVE_PTR_MEDIAINSERTED']</on>
 *  =
 *  XXX wait events SRVE_PTR_MEDIAINSERTED events SRVE_PTR_MEDIAINSERTED
 */
public class OnAction extends XfsExecuteCommandListParamAction {

    @Override
    public STAXSTAFCommandAction createClone() {
        OnAction clone = new OnAction();
        clone.setParameter(getParameter());
        return clone;
    }

    @Override
    protected String prefix() {
        return "events";
    }

    @Override
    public String createCommand() {
        return "wait";
    }
}
