package fr.springg.reportsystem.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.reportsystem.Main;
import fr.springg.reportsystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportOptionsInv extends FastInv {

    private String name;

    public ReportOptionsInv() {
        super(9, "§cOptions");

        setItem(0, new ItemBuilder(Material.IRON_AXE).setName("§4§lBan la personne").toItemStack());
        setItem(1, new ItemBuilder(Material.IRON_HOE).setName("§c§lKick la personne").toItemStack());
        setItem(2, new ItemBuilder(Material.LAVA).setName("§eSupprimer le report").toItemStack());

        setItem(8, new ItemBuilder(Material.BARRIER).setName("§cFermer le menu").toItemStack());
    }

    public void setName(String name) { this.name = name; }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(it == null) return;
        if(it.getType()==Material.BARRIER) p.closeInventory();

        Player target = Bukkit.getPlayer(name);

        switch(it.getType()){

            case IRON_AXE:
                try {
                    PreparedStatement sts = Main.getInstance().sql.getConnection().prepareStatement("SELECT reason FROM reports WHERE reported="+target.getName());
                    ResultSet rs = sts.executeQuery();
                    if(rs.next()){
                        String reason = rs.getString("reason");
                        target.getServer().banIP("§c§l[ReportSystem]" +
                                "\n" +
                                "§cVous avez été ban pour la raison §7:§f" + reason);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1,1);
                        p.closeInventory();
                        p.sendMessage("§aLe joueur §e" + target.getName() + "§a a été ban avec succès !");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;

            case IRON_HOE:
                try {
                    PreparedStatement sts = Main.getInstance().sql.getConnection().prepareStatement("SELECT reason FROM reports WHERE reported="+target.getName());
                    ResultSet rs = sts.executeQuery();
                    if(rs.next()){
                        String reason = rs.getString("reason");
                        target.kickPlayer("§c§l[ReportSystem]" +
                                "\n" +
                                "§cVous avez été kick pour §7:§f" + reason);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1,1);
                        p.closeInventory();
                        p.sendMessage("§aLe joueur §e" + target.getName() + "§a a été ban avec succès !");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;

            case LAVA:
                try {
                    PreparedStatement sts = Main.getInstance().sql.getConnection().prepareStatement("DELETE FROM reports WHERE reported="+target.getName());
                    ResultSet rs = sts.executeQuery();
                    if(rs.next()){
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1,1);
                        p.closeInventory();
                        p.sendMessage("§aVous avez supprimé le report sur §e" + target.getName() + "§a !");
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
                break;

            default:
                break;
        }

        super.onClick(e);
    }
}
