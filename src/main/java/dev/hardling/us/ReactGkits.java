package dev.hardling.us;

import dev.hardling.us.Listeners.Editor.EditorListener;
import dev.hardling.us.Listeners.Editor.KitListener;
import dev.hardling.us.Listeners.Editor.SlotListener;
import dev.hardling.us.Listeners.GkitListener;
import dev.hardling.us.Listeners.PlayerListener;
import dev.hardling.us.Utils.CC;
import dev.hardling.us.Utils.command.CommandFramework;
import dev.hardling.us.Utils.command.CommandManager;
import dev.hardling.us.Utils.provider.files.ConfigCreator;
import dev.hardling.us.Utils.provider.files.FileUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


@Getter
public class ReactGkits extends JavaPlugin {

    @Getter
    private static ReactGkits plugin;
    @Getter
    private static ReactGkits inst;
    @Setter
    private ConfigCreator licenseFile;
    @Setter
    private ConfigCreator langFile;
    @Setter
    private ConfigCreator gkitFile;
    @Setter
    private ConfigCreator dataFile;
    private String prefix = CC.translate("&8[&3&lReactGkits&8] &f");
    private CommandFramework commandFramework;
    private CommandManager commandManager;


    @Override
    public void onEnable() {
        inst = this;
        plugin = this;
        try {
            this.licenseFile = new ConfigCreator("license.yml");
            this.langFile = new ConfigCreator("lang.yml");
            this.gkitFile = new ConfigCreator("gkit.yml");
            this.dataFile = new ConfigCreator("data.yml");
        } catch (RuntimeException e) {
            CC.log(CC.LINERROR);
            CC.log(this.prefix + "&cYMLs did not load, please restart the server or contact support. ");
            CC.log(CC.LINERROR);
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.getScheduler().cancelTasks(this);
        }
        /*try {
            String a1 = this.licenseFile.getString("LICENSE");
            String a2 = "http://108.175.15.151:8080/api/client";
            String a3 = "7eb937c5b91cbeb7210340180a1955fbcf991196";
            while (!new FileUtil.getConfig(this, a1, a2, a3).a5()) {
                while (!new FileUtil.getFile(this, a1, a2, a3).a4()) {
                    Bukkit.getPluginManager().disablePlugin(this);
                    Bukkit.getScheduler().cancelTasks(this);
                    return;
                }
            }
            CC.log(this.prefix + "&aSuccessfully loaded plugin");
        } catch (Exception e) {
            CC.log(CC.LINERROR);
            CC.log(this.prefix + e.getMessage());
            CC.log(CC.LINERROR);
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.getScheduler().cancelTasks(this);
        }*/
    }

    @Override
    public void onDisable() {
        CC.log(this.prefix + "&cDisabled ReactGkits");
    }

    private void loadCommands() {
        commandFramework = new CommandFramework(this);
        commandManager = new CommandManager();
        commandManager.loadCommands();
        commandFramework.loadCommandsInFile();
    }

    private void loadDepend() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            CC.log(this.prefix + "&aPlaceholderAPI Expansion Successfully");
        } else {
            CC.log("&d=====&5============&8[&bPlaceholderAPI&8]&d============&5=====");
            CC.log("&cYou have no support");
            CC.log("&cPlease put the PlaceholderAPI");
            CC.log("&d=====&5============&8[&bPlaceholderAPI&8]&d============&5=====");
            Bukkit.getPluginManager().disablePlugin(ReactGkits.getPlugin());
            Bukkit.getScheduler().cancelTasks(ReactGkits.getPlugin());
        }
    }

    private void loadListeners() {
        PluginManager manager = ReactGkits.getInst().getServer().getPluginManager();
        manager.registerEvents(new GkitListener(), ReactGkits.getInst());
        manager.registerEvents(new PlayerListener(), ReactGkits.getInst());
        manager.registerEvents(new EditorListener(), ReactGkits.getInst());
        manager.registerEvents(new KitListener(), ReactGkits.getInst());
        manager.registerEvents(new SlotListener(), ReactGkits.getInst());
        //manager.registerEvents(new ViewInventoryListener(), ReactGkits.getInst());
        //manager.registerEvents(new PermissionListener(), ReactGkits.getInst());
    }

    public void loadAll() {
        loadDepend();
        loadListeners();
        loadCommands();
    }

    public static ReactGkits get() {
        return ReactGkits.getPlugin(ReactGkits.class);
    }
}