package fr.springg.reportsystem.commands;

import fr.springg.reportsystem.listeners.ReportGUI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("report")) {
            if(sender instanceof Player){
                final Player p = (Player) sender;
                final Player target = Bukkit.getPlayer(args[0]);

                if(args[0].equalsIgnoreCase(target.getName())){
                    if(target == null) {
                        p.sendMessage("§cCe joueur n'est pas connecté ou n'existe pas !");
                        return false;
                    } else {
                        ReportGUI.openGui(target.getName());
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 0.4f, 0.4f);
                    }
                }

            } else {
                Bukkit.getConsoleSender().sendMessage("§cSeul un joueur peut executer cette commande !");
                return false;
            }
        }

        return false;
    }
}
