package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.UKeyIsrCrdAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class UKeyIsrCrdActionFactory extends XfsServiceActionFactory<UKeyIsrCrdAction> {

    public static final String XFS_PTR_EVENT = "UKeyIsrCrd";

    public UKeyIsrCrdActionFactory() {
        super("ukey-isr-crd", UKeyIsrCrdAction.class);
    }

}
