package dev.hardling.us.Utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static String LINELICENSE = "&b=====&3============&8[&bLicense&8-&bSystem&8]&3============&b=====";
    public static String LINENORMAL = "&b=====&3========================================&b=====";
    public static String LINERROR = "&c=====&4============&8[&bERROR&8]&4============&c=====";
    public static String LINELIGH = "&8&m+----------------------------------------------+";
    public static String LINELIGHCUT = "&b&m-----&3&m&l---------------&b&m-----";

    public static List<String> translateFromArray(List<String> text) {
        List<String> messages = new ArrayList<>();
        for (String string : text)
            messages.add(translateFromString(string));
        return messages;
    }

    public static String translateFromString(String text) {
        return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', text));
    }

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static void log(String in) {
        Bukkit.getConsoleSender().sendMessage(translate(in));
    }

    public static List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList<>();
        for (String line : lines)
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        return toReturn;
    }

    public static List<String> translate(String[] lines) {
        List<String> toReturn = new ArrayList<>();
        for (String line : lines) {
            if (line != null)
                toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return toReturn;
    }
}
