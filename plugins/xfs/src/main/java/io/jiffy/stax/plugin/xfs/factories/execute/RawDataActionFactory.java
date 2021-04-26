package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.RawDataAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class RawDataActionFactory extends XfsExecuteCommandActionFactory<RawDataAction> {

    public RawDataActionFactory() {
        super("raw-data", RawDataAction.class);
    }
}
