package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.actions.execute.RetainCardAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class RetainCardActionFactory extends XfsExecuteCommandActionFactory<RetainCardAction> {

    public RetainCardActionFactory() {
        super("retain-card", RetainCardAction.class);
    }

}
