package fr.springg.reportsystem.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        for(Player pls : Bukkit.getOnlinePlayers()){
            if(pls.hasPermission("report.receive")){
                // Reports.
                TextComponent reports = new TextComponent("§aVoulez-vous voir les 20 derniers reports?");
                reports.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cOui je veux !").create()));
                reports.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "report lasts"));

                p.spigot().sendMessage(reports);
            }
        }
    }

}
