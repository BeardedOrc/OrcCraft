package me.beardedorc.orccraft.utilities;

import me.beardedorc.orccraft.OrcCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlManager {

    private OrcCraft plugin = OrcCraft.getInstance();

    private Connection connection;
    public String host, database, username, password;
    public int port;


    public void mysqlSetup(){

        host = this.plugin.getConfig().getString("mysql.host");
        port = this.plugin.getConfig().getInt("mysql.port");
        database = this.plugin.getConfig().getString("mysql.database");
        username = this.plugin.getConfig().getString("mysql.username");
        password = this.plugin.getConfig().getString("mysql.password");

        try{

            synchronized (this){
                if(getConnection() != null && !getConnection().isClosed()){
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":"
                        + this.port + "/" + this.database + "?autoReconnect=true&useSSL=false", this.username,this.password));

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}