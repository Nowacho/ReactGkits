package dev.hardling.us.Utils.provider;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.provider.files.ConfigCreator;


public class Lang {

    //public static String;
    private static ConfigCreator langFile = ReactGkits.getInst().getLangFile();
    public static String KIT_COMMAND_MENTION_KIT = langFile.getString("KIT_COMMAND.MENTION_KIT");
    public static String KIT_COMMAND_MENTION_KIT_NOT_EXIST = langFile.getString("KIT_COMMAND.MENTION_KIT_NOT_EXIST");
    public static String KIT_COMMAND_MENTION_NAME_KIT = langFile.getString("KIT_COMMAND.MENTION_NAME_KIT");
    public static String KIT_COMMAND_MENTION_PERM_KIT = langFile.getString("KIT_COMMAND.MENTION_PERM_KIT");
    public static String KIT_COMMAND_MENTION_PLAYER = langFile.getString("KIT_COMMAND.MENTION_PLAYER");
    public static String KIT_COMMAND_MENTION_PLAYER_NOT_EXIST = langFile.getString("KIT_COMMAND.MENTION_PLAYER_NOT_EXIST");
    public static String KIT_COMMAND_CORRETLY_PLACED = langFile.getString("KIT_COMMAND.CORRETLY_PLACED");
    public static String KIT_COMMAND_ALREADY_CREATED = langFile.getString("KIT_COMMAND.ALREADY_CREATED");
    public static String KIT_COMMAND_SUCCESSFULLY_CREATED = langFile.getString("KIT_COMMAND.SUCCESSFULLY_CREATED");
    public static String KIT_COMMAND_SUCCESSFULLY_DELETE = langFile.getString("KIT_COMMAND.SUCCESSFULLY_DELETE");
    public static String KIT_COMMAND_OPEN_EDIT_MENU = langFile.getString("KIT_COMMAND.OPEN_EDIT_MENU");
    public static String KIT_NO_ITEMS = langFile.getString("KIT_COMMAND.NO_ITEMS");
}
