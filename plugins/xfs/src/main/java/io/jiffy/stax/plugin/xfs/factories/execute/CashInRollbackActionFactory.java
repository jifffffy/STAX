package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.CashInAction;
import io.jiffy.stax.plugin.xfs.actions.execute.CashInRollbackAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class CashInRollbackActionFactory extends XfsCommandActionFactory<CashInRollbackAction> {

    public CashInRollbackActionFactory() {
        super("cash-in-rollback", CashInRollbackAction.class);
    }
}
