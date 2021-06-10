package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.CurrencyCimAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class CurrencyCimActionFactory extends XfsServiceActionFactory<CurrencyCimAction> {

    public static final String XFS_PTR_EVENT = "CURRENCY_CIM";

    public CurrencyCimActionFactory() {
        super("currency-cim", CurrencyCimAction.class);
    }

}
