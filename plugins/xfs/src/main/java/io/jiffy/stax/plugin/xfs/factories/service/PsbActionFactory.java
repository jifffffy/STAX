package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.PsbAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class PsbActionFactory extends XfsServiceActionFactory<PsbAction> {

    public static final String XFS_PTR_EVENT = "PSB";

    public PsbActionFactory() {
        super("psb", PsbAction.class);
    }

}
