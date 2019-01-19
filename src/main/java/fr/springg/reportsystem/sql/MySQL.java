package fr.springg.reportsystem.sql;

import fr.springg.reportsystem.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    private Connection conn;

    public MySQL(){}

    public void connect(String host, String database, int port, String user, String password){
        if(!isConnected()){
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                System.out.println("Connexion etablie avec la base de donnees");
            } catch (SQLException e){
                e.printStackTrace();
                System.out.println("Connexion refuse avec la base de donnees");
            }
        }
    }

    public void disconnect(){
        if(isConnected()){
            try {
                conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected(){
        try {
            if((conn == null) || (conn.isClosed()) || (conn.isValid(5))){
                return false;
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void query(String qry){
        final String host = Main.getInstance().dbconfig.getString("database.host");
        final String dbname = Main.getInstance().dbconfig.getString("database.dbname");
        final String user = Main.getInstance().dbconfig.getString("database.user");
        final String pass = Main.getInstance().dbconfig.getString("database.pass");
        final int port = Main.getInstance().dbconfig.getInt("database.port");
        try  (Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname, user, pass); Statement state = conn.createStatement()){
            state.execute(qry);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return conn;
    }

}
