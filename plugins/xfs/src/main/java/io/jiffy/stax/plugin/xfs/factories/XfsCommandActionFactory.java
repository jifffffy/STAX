package io.jiffy.stax.plugin.xfs.factories;

import com.ibm.staf.service.stax.ActionFactorySupport;
import com.ibm.staf.service.stax.STAX;
import io.jiffy.stax.plugin.xfs.actions.XfsCommandAction;

public abstract class XfsCommandActionFactory<T extends XfsCommandAction> extends ActionFactorySupport<T> {

    public XfsCommandActionFactory(String name, Class<T> clazz) {
        super(name, clazz);
    }

    @Override
    public void initialize(STAX stax) {

    }
}
