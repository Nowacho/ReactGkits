package dev.hardling.us.Utils.command;

import dev.hardling.us.Commands.GkitCommand;
import dev.hardling.us.Commands.KitCommand;
import dev.hardling.us.Commands.ReactGkitCommand;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandManager {

    private ReactGkitCommand reactGkitCommand;
    private GkitCommand gkitCommand;
    private KitCommand kitCommand;

    @Register
    public void loadCommands() {
        for (Field field : getClass().getDeclaredFields()) {
            if (BaseCommand.class.isAssignableFrom(field.getType()) && field.getType().getSuperclass() == BaseCommand.class) {
                field.setAccessible(true);
                try {
                    Constructor<?> constructor = field.getType().getDeclaredConstructor(new Class[0]);
                    constructor.newInstance(new Object[0]);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}