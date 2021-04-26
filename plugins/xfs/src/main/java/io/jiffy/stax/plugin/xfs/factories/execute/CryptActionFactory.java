package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.CashInStartAction;
import io.jiffy.stax.plugin.xfs.actions.execute.CryptAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class CryptActionFactory extends XfsExecuteCommandActionFactory<CryptAction> {

    public CryptActionFactory() {
        super("crypt", CryptAction.class);
    }

}
