package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.service.RptAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class RptActionFactory extends XfsServiceActionFactory<RptAction> {

    public static final String XFS_PTR_EVENT = "RPT";

    public RptActionFactory() {
        super("rpt", RptAction.class);
    }

}
