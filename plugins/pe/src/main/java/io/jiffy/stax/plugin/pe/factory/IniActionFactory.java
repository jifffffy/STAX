package io.jiffy.stax.plugin.pe.factory;

import com.ibm.staf.service.stax.ActionFactoryExtensionPoint;
import com.ibm.staf.service.stax.ActionFactorySupport;
import com.ibm.staf.service.stax.STAX;
import io.jiffy.stax.plugin.pe.action.IniAction;
import org.pf4j.Extension;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class IniActionFactory extends ActionFactorySupport<IniAction> {

    public IniActionFactory() {
        super("pe-ini", IniAction.class);
    }

    @Override
    public void initialize(STAX stax) {

    }
}
