package fr.springg.reportsystem;

import fr.springg.reportsystem.commands.Commands;
import fr.springg.reportsystem.listeners.PlayerListener;
import fr.springg.reportsystem.mods.ReportManager;
import fr.springg.reportsystem.sql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;

    public FileConfiguration dbconfig = YamlConfiguration.loadConfiguration(getFile("database"));
    public MySQL sql = new MySQL();
    public ReportManager reports = new ReportManager();

    @Override
    public void onEnable() {
        instance = this;
        setupFile();
        setup();
        setupSQL();
    }

    private void setup(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(), this);

        getCommand("report").setExecutor(new Commands());
    }

    private void setupFile(){
        createFile("database");

        dbconfig.set("database.host", "localhost");
        dbconfig.set("database.dbname", "testdb");
        dbconfig.set("database.user", "root");
        dbconfig.set("database.pass", "");
        dbconfig.set("database.port", 3306);
    }

    private void setupSQL(){
        sql.connect(dbconfig.getString("database.host"), dbconfig.getString("database.dbname"), dbconfig.getInt("database.port"), dbconfig.getString("database.user"), dbconfig.getString("database.pass"));

        sql.query("CREATE TABLE IF NOT EXISTS reports(" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY_KEY," +
                "reported VARCHAR(255)," +
                "date TIMESTAMP," +
                "author VARCHAR(255)," +
                "reason VARCHAR(255))");
    }

    @Override
    public void onDisable() {sql.disconnect();}

    public void createFile(String fileName){
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        File file = new File(getDataFolder(), fileName + ".yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile(String fileName){
        return new File(getDataFolder(), fileName + ".yml");
    }

    public static Main getInstance(){
        return instance;
    }

}
