package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.CashInAction;
import io.jiffy.stax.plugin.xfs.actions.execute.RetainSlotStartAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class RetainSlotStartActionFactory extends XfsCommandActionFactory<RetainSlotStartAction> {

    public RetainSlotStartActionFactory() {
        super("retain-slot-start", RetainSlotStartAction.class);
    }
}
