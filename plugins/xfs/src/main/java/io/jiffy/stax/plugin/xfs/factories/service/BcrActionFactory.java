package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.BcrAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class BcrActionFactory extends XfsServiceActionFactory<BcrAction> {

    public static final String XFS_PTR_EVENT = "BCR";

    public BcrActionFactory() {
        super("bcr", BcrAction.class);
    }

}
