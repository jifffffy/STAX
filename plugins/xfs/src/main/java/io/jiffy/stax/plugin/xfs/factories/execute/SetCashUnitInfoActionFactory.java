package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.SetCashUniInfoAction;
import io.jiffy.stax.plugin.xfs.actions.execute.StartExchangeAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class SetCashUnitInfoActionFactory extends XfsExecuteCommandActionFactory<SetCashUniInfoAction> {

    public SetCashUnitInfoActionFactory() {
        super("set-cash-unit-info", SetCashUniInfoAction.class);
    }

}
