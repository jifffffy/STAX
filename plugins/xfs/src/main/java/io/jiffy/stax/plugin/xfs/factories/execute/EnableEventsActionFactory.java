package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.EnableEventsAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class EnableEventsActionFactory extends XfsCommandActionFactory<EnableEventsAction> {

    public EnableEventsActionFactory() {
        super("enable-events", EnableEventsAction.class);
    }
}
