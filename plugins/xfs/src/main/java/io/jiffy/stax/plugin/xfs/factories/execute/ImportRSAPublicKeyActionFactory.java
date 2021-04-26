package io.jiffy.stax.plugin.xfs.factories.execute;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.execute.ExportRSAIssuerSignedItemAction;
import io.jiffy.stax.plugin.xfs.actions.execute.ImportRSAPublicKeyAction;
import io.jiffy.stax.plugin.xfs.factories.XfsExecuteCommandActionFactory;
import org.pf4j.Extension;


@Extension(points = {ActionFactoryExtensionPoint.class})
public class ImportRSAPublicKeyActionFactory extends XfsExecuteCommandActionFactory<ImportRSAPublicKeyAction> {

    public ImportRSAPublicKeyActionFactory() {
        super("import-rsa-public-key", ImportRSAPublicKeyAction.class);
    }

}
