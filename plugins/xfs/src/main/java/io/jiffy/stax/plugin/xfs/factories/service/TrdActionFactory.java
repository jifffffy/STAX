package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.TrdAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class TrdActionFactory extends XfsServiceActionFactory<TrdAction> {

    public static final String XFS_PTR_EVENT = "TRD";

    public TrdActionFactory() {
        super("trd", TrdAction.class);
    }

}
