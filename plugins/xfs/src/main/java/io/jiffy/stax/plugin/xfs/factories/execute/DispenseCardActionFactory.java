package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.DispenseCardAction;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class DispenseCardActionFactory extends XfsExecuteCommandActionFactory<DispenseCardAction> {

    public DispenseCardActionFactory() {
        super("dispense-card", DispenseCardAction.class);
    }

}
