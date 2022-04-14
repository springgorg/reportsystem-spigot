package fr.springg.reportsystem.mods;

import fr.springg.reportsystem.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportManager {

    public void add(Report report){
        Main.getInstance().sql.query("INSERT INTO reports(reported, date, author, reason) VALUES(" + report.getName() + ", CURDATE(), " + report.getAuthor() + ", " + report.getReason() + ")");
    }

    public int getTotal(){
        try {
            PreparedStatement sts = Main.getInstance().sql.getConnection().prepareStatement("SELECT COUNT(*) AS list FROM reports");
            ResultSet rs = sts.executeQuery();
            if(rs.next()){
                return rs.getInt("list");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void remove(Report report){
        Main.getInstance().sql.query("DELETE FROM reports WHERE reported="+report.getName());
    }

}
