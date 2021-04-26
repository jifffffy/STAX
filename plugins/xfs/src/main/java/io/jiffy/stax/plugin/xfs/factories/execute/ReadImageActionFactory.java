package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.DispenseAction;
import io.jiffy.stax.plugin.xfs.actions.execute.ReadImageAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class ReadImageActionFactory extends XfsExecuteCommandActionFactory<ReadImageAction> {

    public ReadImageActionFactory() {
        super("read-image", ReadImageAction.class);
    }

}
