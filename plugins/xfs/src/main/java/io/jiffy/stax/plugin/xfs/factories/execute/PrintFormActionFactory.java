package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.execute.PrintFormAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class PrintFormActionFactory extends XfsExecuteCommandActionFactory<PrintFormAction> {

    public PrintFormActionFactory() {
        super("print-form", PrintFormAction.class);
    }

}
