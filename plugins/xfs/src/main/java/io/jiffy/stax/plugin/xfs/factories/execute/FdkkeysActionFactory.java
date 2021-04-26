package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.ActionKeysAction;
import io.jiffy.stax.plugin.xfs.actions.execute.FdkKeysAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class FdkkeysActionFactory extends XfsExecuteCommandActionFactory<FdkKeysAction> {

    public FdkkeysActionFactory() {
        super("fdk-keys", FdkKeysAction.class);
    }
}
