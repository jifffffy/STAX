package io.jiffy.stax.plugin.xfs;

import org.pmw.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringJoinTest {

    public static void main(String[] args) {
        List<String> events = new ArrayList<>();
        events.add("111");
        events.add("222");
        events.add("333");
        events.add("444");
        Logger.info("events  = {}", events.stream().map(e -> " events " + e).collect(Collectors.joining()));
    }
}
