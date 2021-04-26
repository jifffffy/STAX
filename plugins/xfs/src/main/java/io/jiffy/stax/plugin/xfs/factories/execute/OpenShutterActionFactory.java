package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.OpenShutterAction;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class OpenShutterActionFactory extends XfsExecuteCommandActionFactory<OpenShutterAction> {

    public OpenShutterActionFactory() {
        super("open-shutter", OpenShutterAction.class);
    }

}
