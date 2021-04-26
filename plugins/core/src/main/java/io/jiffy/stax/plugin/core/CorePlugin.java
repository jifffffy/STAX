package io.jiffy.stax.plugin.core;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class CorePlugin extends Plugin {
    /**
     * Constructor to be used by plugin manager for plugin instantiation.
     * Your plugins have to provide constructor with this exact signature to
     * be successfully loaded by manager.
     *
     * @param wrapper
     */
    public CorePlugin(PluginWrapper wrapper) {
        super(wrapper);
    }
}
