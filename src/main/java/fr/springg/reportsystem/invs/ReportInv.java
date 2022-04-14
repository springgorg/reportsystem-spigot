package fr.springg.reportsystem.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.reportsystem.Main;
import fr.springg.reportsystem.mods.Report;
import fr.springg.reportsystem.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ReportInv extends FastInv {

    private String name;

    public ReportInv() {
        super(6*9, "§aReport §8>> §e§l");

        setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§cKillAura").setLore("§aSignaler un §cKillAura").toItemStack());
        setItem(1, new ItemBuilder(Material.FEATHER).setName("§fFly").setLore("§aSignaler un §fFly").toItemStack());
        setItem(2, new ItemBuilder(Material.DIAMOND_ORE).setName("§bXray").setLore("§aSignaler un §bXray").toItemStack());
        setItem(3, new ItemBuilder(Material.COMPASS).setName("§eAimAssist").setLore("§aSignaler un §eAimAssist").toItemStack());
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(it ==null) return;

        if(e.getInventory().getName().equalsIgnoreCase("§aReport §8>> §e§l")){
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

                case DIAMOND_ORE:
                    Main.getInstance().reports.add(new Report(name, p.getName(), "Xray"));
                    p.sendMessage("§aVotre report a bien été envoyé !");
                    e.setCancelled(true);
                    p.closeInventory();
                    break;

                case COMPASS:
                    Main.getInstance().reports.add(new Report(name, p.getName(), "AimAssist"));
                    p.sendMessage("§aVotre report a bien été envoyé !");
                    e.setCancelled(true);
                    p.closeInventory();
                    break;

                default:
                    break;

            }
        }


        super.onClick(e);
    }
}
