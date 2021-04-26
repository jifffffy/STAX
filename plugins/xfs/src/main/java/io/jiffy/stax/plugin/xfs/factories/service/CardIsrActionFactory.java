package io.jiffy.stax.plugin.xfs.factories.service;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.service.AccAction;
import io.jiffy.stax.plugin.xfs.actions.service.CardIsrAction;
import io.jiffy.stax.plugin.xfs.factories.XfsServiceActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class CardIsrActionFactory extends XfsServiceActionFactory<CardIsrAction> {

    public static final String XFS_PTR_EVENT = "CardIsr";

    public CardIsrActionFactory() {
        super("card-isr", CardIsrAction.class);
    }

}
