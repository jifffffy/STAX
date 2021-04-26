package io.jiffy.stax.plugin.xfs.factories.info;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.info.CardUnitInfoAction;
import io.jiffy.stax.plugin.xfs.actions.info.FormListAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class CardUnitInfoActionFactory extends XfsCommandActionFactory<CardUnitInfoAction> {

    public CardUnitInfoActionFactory() {
        super("card-unit-info", CardUnitInfoAction.class);
    }
}
