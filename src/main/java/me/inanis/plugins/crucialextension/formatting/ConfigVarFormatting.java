package me.inanis.plugins.crucialextension.formatting;

public class ConfigVarFormatting {

    public static String parsePlayer(String s, String playername) {
        if (s == null) {
            return "";
        }
        s = s.replaceAll("\\{PLAYER}", playername);
        return s;
    }
}
