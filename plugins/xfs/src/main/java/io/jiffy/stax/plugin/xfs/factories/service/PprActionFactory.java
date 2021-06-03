package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.PprAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class PprActionFactory extends XfsServiceActionFactory<PprAction> {

    public static final String XFS_PTR_EVENT = "PPR";

    public PprActionFactory() {
        super("ppr", PprAction.class);
    }

}
