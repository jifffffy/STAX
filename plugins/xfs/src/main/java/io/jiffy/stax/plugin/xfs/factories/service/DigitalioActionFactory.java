package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.DigitalIoAction;
import io.jiffy.stax.plugin.xfs.actions.service.DisAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class DigitalioActionFactory extends XfsServiceActionFactory<DigitalIoAction> {

    public static final String XFS_PTR_EVENT = "DIS";

    public DigitalioActionFactory() {
        super("digitalio", DigitalIoAction.class);
    }

}
