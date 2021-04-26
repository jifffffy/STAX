package io.jiffy.stax.plugin.pe.action.ini;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.ini4j.Config;
import org.ini4j.Wini;

public class Ini implements Callable<String> {

    private Section section;
    private Option option;
    private String action;
    private String path;
    private Wini wini;

    public Ini() {
    }

    public void load() throws Exception {
        Config config = new Config();
        config.setFileEncoding(Charset.forName("gb2312"));
        wini = new Wini(new BufferedReader(new InputStreamReader(new FileInputStream(this.path))));
        wini.setConfig(config);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String call() throws Exception {

        if (action == null || action.length() == 0) {
            throw new RuntimeException("请提供query|update|remove中的一种");
        }

        String result = "";
        String sectionName = getSection().getName();
        String optionName = getOption().getName();

        switch (action) {
            case "query":
                result = wini.get(sectionName, optionName);
                break;
            case "update":
                wini.put(sectionName, optionName, getOption().getValue());
                wini.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.path))));
                break;
            case "remove":
                wini.remove(sectionName, optionName);
                wini.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.path))));
                break;
            default:
                throw new RuntimeException("不支持" + action + "操作");
        }
        return result;
    }

    public boolean valid() {
        return StringUtils.isNotEmpty(getPath()) &&
                StringUtils.isNotEmpty(getAction()) &&
                getSection() != null &&
                StringUtils.isNotEmpty(getSection().getName()) &&
                getOption() != null &&
                StringUtils.isNotEmpty(getOption().getName());
    }
}
