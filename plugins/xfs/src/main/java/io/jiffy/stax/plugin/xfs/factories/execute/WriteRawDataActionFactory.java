package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.actions.execute.WriteRawDataAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class WriteRawDataActionFactory extends XfsExecuteCommandActionFactory<WriteRawDataAction> {

    public WriteRawDataActionFactory() {
        super("write-raw-data", WriteRawDataAction.class);
    }

}
