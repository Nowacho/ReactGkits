package dev.hardling.us.Utils.command;

import dev.hardling.us.ReactGkits;

public abstract class BaseCommand {
    public ReactGkits plugin = ReactGkits.getInst();

    public BaseCommand() {
            this.plugin.getCommandFramework().registerCommands(this, null);
    }

    public abstract boolean sendMessage(CommandArgs paramCommandArgs);
}
