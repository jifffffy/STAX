package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.IdrAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class IdrActionFactory extends XfsServiceActionFactory<IdrAction> {

    public static final String XFS_PTR_EVENT = "IDR";

    public IdrActionFactory() {
        super("idr", IdrAction.class);
    }

}
