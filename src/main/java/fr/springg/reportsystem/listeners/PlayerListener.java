package fr.springg.reportsystem.listeners;

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
            }
        }
    }

}
