package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.actions.execute.RetractAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class RetractActionFactory extends XfsExecuteCommandActionFactory<RetractAction> {

    public RetractActionFactory() {
        super("retract", RetractAction.class);
    }

}
