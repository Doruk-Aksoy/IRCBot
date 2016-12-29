package Database;

import java.sql.*;
import java.util.ArrayList;

import Trigger.*;

public class Database_Connection implements Notifier {
    private static Database_Connection instance = null;
    private Connection connection = null;
    private ArrayList<Triggerable> triggers;
    
    private Database_Connection() {
        triggers = new ArrayList<>();
        try {
            // register once only
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
        }
    }
    
    public synchronized static Database_Connection getInstance() {
        if(instance == null)
            instance = new Database_Connection();
        return instance;
    }
    
    public Connection connect() {
        try {
            connection = DriverManager.getConnection(Database_Info.DB_URL, Database_Info.DB_LOGIN, Database_Info.DB_PASSWORD);
        } catch(SQLException e) {
            System.out.println(e.toString() + " happened!");
            connection = null;
        }
        notifyTriggers();
        return connection;
    }
    
    public void disconnect() throws SQLException {
        connection.close();
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    @Override public void addTrigger(Triggerable T) {
        triggers.add(T);
    }
    
    @Override public void notifyTriggers() {
        for(Triggerable T : triggers)
            T.trigger(this);
    }
}
