package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.EppAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class EppActionFactory extends XfsServiceActionFactory<EppAction> {

    public static final String XFS_PTR_EVENT = "EPP";

    public EppActionFactory() {
        super("epp", EppAction.class);
    }

}
