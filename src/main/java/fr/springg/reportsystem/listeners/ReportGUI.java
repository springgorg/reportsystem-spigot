package fr.springg.reportsystem.listeners;

import fr.springg.reportsystem.Main;
import fr.springg.reportsystem.mods.Report;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ReportGUI implements Listener {

    private static String title = "§aReport §8>> §e§l";
    private static Inventory inv;
    private static String name;

    public static void openGui(String targetName){
        name = targetName;
        inv = Bukkit.createInventory(null, 6*9, title + name);

        // ItemStacks
        ItemStack killAura = new ItemStack(Material.IRON_SWORD);
        ItemMeta kM = killAura.getItemMeta();
        kM.setDisplayName("§cKillAura");
        kM.setLore(Arrays.asList("§aSignaler un §cKillAura"));
        killAura.setItemMeta(kM);

        ItemStack fly = new ItemStack(Material.FEATHER);
        ItemMeta fM = fly.getItemMeta();
        fM.setDisplayName("§fFly");
        fM.setLore(Arrays.asList("§aSignaler un §fFly"));
        fly.setItemMeta(fM);

        // Inventory item position.
        inv.setItem(0, killAura);
        inv.setItem(1, fly);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        Inventory i = e.getInventory();

        if(i.getName().equalsIgnoreCase(title)){

            switch(it.getType()){

                case IRON_SWORD:
                    Main.getInstance().reports.add(new Report(name, p.getName(), "KillAura"));
                    p.sendMessage("§aVotre report a bien été envoyé !");
                    e.setCancelled(true);
                    p.closeInventory();
                    break;

                case FEATHER:
                    Main.getInstance().reports.add(new Report(name, p.getName(), "Fly"));
                    p.sendMessage("§aVotre report a bien été envoyé !");
                    e.setCancelled(true);
                    p.closeInventory();
                    break;

                default:
                    break;

            }

        }
    }

}
