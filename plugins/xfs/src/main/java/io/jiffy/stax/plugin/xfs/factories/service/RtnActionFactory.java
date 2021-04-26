package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.RtnAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class RtnActionFactory extends XfsServiceActionFactory<RtnAction> {

    public static final String XFS_PTR_EVENT = "RTN";

    public RtnActionFactory() {
        super("rtn", RtnAction.class);
    }

}
