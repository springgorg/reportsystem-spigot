package fr.springg.reportsystem.invs;

import fr.mrmicky.fastinv.FastInv;
import fr.springg.reportsystem.Main;
import fr.springg.reportsystem.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportLastsInv extends FastInv {
    public ReportLastsInv() {
        super(3*9, "§cLes 20 derniers reports");

        setItem(26, new ItemBuilder(Material.BARRIER).setName("§cFermer").toItemStack());

        try {
            PreparedStatement sts = Main.getInstance().sql.getConnection().prepareStatement("SELECT * FROM reports LIMIT 0, 20");
            ResultSet rs = sts.executeQuery();
            if(rs.next()){
                setItem(rs.getInt("id"), new ItemBuilder(Material.SKULL_ITEM).setName("§b"+rs.getString("reported")).setLore(
                        "§cReporté par §8: §f" + rs.getString("author")+
                        "\n" +
                        "§aDate §8: §f" + rs.getDate("date") +
                        "\n" +
                        "§eRaison §8: §f" + rs.getString("reason")).toItemStack());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(e.getInventory().getName().equalsIgnoreCase("§cLes 20 derniers reports")){

            if(it == null) return;
            if(it.getType()==Material.BARRIER) p.closeInventory();
            /* Créer un inventaire qui peut :
             * Supprimer ou pas les reports
             * Ban les gens report
             * Kick les gens report
             * Mute les gens report
             */
        }
        super.onClick(e);
    }
}
