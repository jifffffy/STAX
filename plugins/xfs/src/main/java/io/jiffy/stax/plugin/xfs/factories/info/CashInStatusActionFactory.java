package io.jiffy.stax.plugin.xfs.factories.info;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.info.CapabilitiesAction;
import io.jiffy.stax.plugin.xfs.actions.info.CashInStatusAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class CashInStatusActionFactory extends XfsCommandActionFactory<CashInStatusAction> {
    public CashInStatusActionFactory() {
        super("cash-in-status", CashInStatusAction.class);
    }
}
