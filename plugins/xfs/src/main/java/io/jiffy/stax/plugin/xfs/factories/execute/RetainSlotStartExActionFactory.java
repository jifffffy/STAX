package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.actions.execute.RetainSlotStartExAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class RetainSlotStartExActionFactory extends XfsExecuteCommandActionFactory<RetainSlotStartExAction> {

    public RetainSlotStartExActionFactory() {
        super("retain-slot-start-ex", RetainSlotStartExAction.class);
    }

}
