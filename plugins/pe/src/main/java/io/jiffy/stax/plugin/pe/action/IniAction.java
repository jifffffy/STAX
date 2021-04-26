package io.jiffy.stax.plugin.pe.action;

import com.ibm.staf.service.stax.*;
import io.jiffy.stax.plugin.pe.action.ini.Ini;
import org.pf4j.Extension;
import org.pmw.tinylog.Logger;

@Extension(points = {ActionFactoryExtensionPoint.class})
public class IniAction extends STAXActionDefaultImpl {
    private Ini ini;


    @Override
    public void execute(STAXThread thread) {
        try {
            ini.load();
            ini.call();
            thread.popAction();
        } catch (Exception e) {
            thread.popAction();
            setElementInfo(new STAXElementInfo(getElement()));
            Logger.error(e);
        }
    }



    @Override
    public STAXAction cloneAction() {
        IniAction clone = new IniAction();
        clone.setIni(getIni());
        return clone;
    }

    public Ini getIni() {
        return ini;
    }

    public void setIni(Ini ini) {
        this.ini = ini;
    }
}
