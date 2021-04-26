package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.actions.execute.SetGuidLightAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class SetGuidLightActionFactory extends XfsExecuteCommandActionFactory<SetGuidLightAction> {

    public SetGuidLightActionFactory() {
        super("set-guid-light", SetGuidLightAction.class);
    }

}
