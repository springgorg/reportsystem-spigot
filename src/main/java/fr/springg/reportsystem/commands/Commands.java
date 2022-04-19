package fr.springg.reportsystem.commands;

import fr.springg.reportsystem.Main;
import fr.springg.reportsystem.invs.ReportInv;
import fr.springg.reportsystem.invs.ReportLastsInv;
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
                        ReportInv r = new ReportInv();
                        r.setName(target.getName());
                        r.open(p);
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 0.4f, 0.4f);
                    }
                } else if(args[0].equalsIgnoreCase("lasts")){
                    if(p.hasPermission("report.use")) {
                        new ReportLastsInv().open(p);
                    } else {
                        p.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                        return false;
                    }
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 0.4f,0.4f);
                } else if(args[0].equalsIgnoreCase("total")){
                    if(p.hasPermission("report.use")) {
                        p.sendMessage("§aVoici le nombre de total qu'il y a dans notre base de données §8: §f" + Main.getInstance().reports.getTotal());
                    } else {
                        p.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                        return false;
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
