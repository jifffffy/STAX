package io.jiffy.stax.plugin.xfs.factories.info;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import io.jiffy.stax.plugin.xfs.actions.info.CapabilitiesAction;
import io.jiffy.stax.plugin.xfs.factories.XfsCommandActionFactory;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class CapabilitiesActionFactory extends XfsCommandActionFactory<CapabilitiesAction> {
    public CapabilitiesActionFactory() {
        super("capabilities", CapabilitiesAction.class);
    }
}
