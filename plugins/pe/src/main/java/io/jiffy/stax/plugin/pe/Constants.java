package io.jiffy.stax.plugin.pe;

public class Constants {
    public static final String RC = "RC";
    public static final String RESULT = "PEResult";
    public static final String SIGNAL = "PE";
    public static final String INI_ERROR = SIGNAL + "IniError";
    public static final String BEEP_ERROR = SIGNAL + "BeepError";
    public static final String COM_ERROR = SIGNAL + "ComError";

    public static final String HEXES = "0123456789ABCDEF";
    public static final char[] HEXES_ARRAY = HEXES.toCharArray();
    public static final int BYTE_BUFFER_SIZE = 8192;
}
