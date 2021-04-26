package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.CashInAction;
import io.jiffy.stax.plugin.xfs.actions.execute.InitializationAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class InitializationActionFactory extends XfsCommandActionFactory<InitializationAction> {

    public InitializationActionFactory() {
        super("initialization", InitializationAction.class);
    }
}
