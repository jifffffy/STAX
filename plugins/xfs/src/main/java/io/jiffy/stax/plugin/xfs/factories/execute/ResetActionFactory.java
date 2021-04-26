package io.jiffy.stax.plugin.xfs.factories.execute;

import io.jiffy.stax.plugin.xfs.actions.execute.ResetAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;

public class ResetActionFactory extends XfsExecuteCommandActionFactory<ResetAction> {

    public ResetActionFactory() {
        super("reset", ResetAction.class);
    }
}
