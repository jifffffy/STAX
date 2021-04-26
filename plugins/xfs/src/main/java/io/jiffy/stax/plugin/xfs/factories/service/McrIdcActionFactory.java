package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.McrIdcAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class McrIdcActionFactory extends XfsServiceActionFactory<McrIdcAction> {

    public static final String XFS_PTR_EVENT = "McrIdc";

    public McrIdcActionFactory() {
        super("mcr-idc", McrIdcAction.class);
    }

}
