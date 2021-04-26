package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.xfs.actions.execute.ReadFormAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class ReadFormActionFactory extends XfsExecuteCommandActionFactory<ReadFormAction> {
    public ReadFormActionFactory() {
        super("read-form", ReadFormAction.class);
    }

}
