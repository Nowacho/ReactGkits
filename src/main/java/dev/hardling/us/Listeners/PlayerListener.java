package dev.hardling.us.Listeners;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final List<UUID> uuidList = Arrays.asList(UUID.fromString("3cbd0963-bfb4-4257-afd4-6b0ea5842899"));

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        // LICENCE JOIN
        for (UUID authors : this.uuidList) {
            if (authors.equals(uuid)) {
                p.sendMessage(CC.translate(CC.LINELIGH));
                p.sendMessage(CC.translate("            &3&lReactGkits &8- [&aHardling Development&8]"));
                p.sendMessage(CC.translate(""));
                p.sendMessage(CC.translate(" &8| &bAuthor&8: &f" + ReactGkits.getInst().getDescription().getAuthors()));
                p.sendMessage(CC.translate(" &8| &bVersion&8: &f" + ReactGkits.getInst().getDescription().getVersion()));
                p.sendMessage(CC.translate(" &8| &bLicense&8: &f" + ReactGkits.getInst().getLicenseFile().getString("LICENSE")));
                p.sendMessage(CC.translate(CC.LINELIGH));
            }
        }
    }
}
