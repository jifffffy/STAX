package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.CashInStartAction;
import io.jiffy.stax.plugin.xfs.actions.execute.ExportRSAIssuerSignedItemAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class ExportRSAIssuerSignedItemActionFactory extends XfsExecuteCommandActionFactory<ExportRSAIssuerSignedItemAction> {

    public ExportRSAIssuerSignedItemActionFactory() {
        super("export-rsa-issuer-signed-item", ExportRSAIssuerSignedItemAction.class);
    }

}
