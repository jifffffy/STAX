package com.ibm.staf.service.stax;

import org.apache.commons.io.IOUtils;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 提供三个功能
 * 1、通过 resources下的*.dtd获取dtd info
 * 2、提供 ExtensionPoint
 * 3、通过init方法的STAX 提供初始化功能
 */
public abstract class ActionFactoryExtensionPointAdapter implements ActionFactoryExtensionPoint {

    protected String name;
    protected String dtdInfo = "";

    public ActionFactoryExtensionPointAdapter(String name) {
        this.name = name;
    }

    @Override
    public void loadResource(PluginWrapper pluginWrapper) throws IOException {
        dtdInfo = IOUtils.toString(Objects.requireNonNull(pluginWrapper.getPluginClassLoader().getResourceAsStream(name + ".dtd")), StandardCharsets.UTF_8.name());
    }

    @Override
    public String getDTDInfo() {
        return dtdInfo;
    }

    @Override
    public String getDTDTaskName() {
        return name;
    }
}
