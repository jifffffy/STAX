package com.ibm.staf.service.stax;

import org.pf4j.PluginWrapper;
import java.io.IOException;


public interface ActionFactoryExtensionPoint extends STAXActionFactory{
    void initialize(STAX stax);
    void loadResource(PluginWrapper pluginWrapper) throws IOException;
}
