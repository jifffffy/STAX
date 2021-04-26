package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.UKeyIsrAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class UKeyIsrActionFactory extends XfsServiceActionFactory<UKeyIsrAction> {

    public static final String XFS_PTR_EVENT = "UKeyIsr";

    public UKeyIsrActionFactory() {
        super("ukey-isr", UKeyIsrAction.class);
    }

}
