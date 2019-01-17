package fr.springg.reportsystem.mods;

import fr.springg.reportsystem.Main;

public class ReportManager {

    public void add(Report report){
        Main.getInstance().sql.query("INSERT INTO reports(reported, date, author, reason) VALUES(" + report.getName() + ", CURDATE(), " + report.getAuthor() + ", " + report.getReason() + ")");
    }

}
