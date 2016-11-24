package Database;

import java.io.IOException;
import java.sql.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Database_Connection {
    private static Database_Connection instance = null;
    
    private final String DB_USER = "SYSTEM";
    
    private String DB_URL = null;
    private String DB_LOGIN = null;
    private String DB_PASSWORD = null;
    
    private Connection connection = null;
    
    private Database_Connection() {
        try {
            // register once only
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
        // load database credentials from file
        List<String> readLines = null;
        try {
            readLines = Files.readAllLines(Paths.get("/Users/User/Documents/Programming/Java/Bot/database.txt"));
        } catch(IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        for(String line : readLines) {
            if(DB_URL == null)
                DB_URL = line;
            else if(DB_LOGIN == null)
                DB_LOGIN = line;
            else if(DB_PASSWORD == null)
                DB_PASSWORD = line;
        }
    }
    
    public synchronized static Database_Connection getInstance() {
        if(instance == null)
            instance = new Database_Connection();
        return instance;
    }
    
    public Connection connect() throws SQLException {
        System.out.println("Trying to connect...");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected!");
        return connection;
    }
    
    public void disconnect() throws SQLException {
        connection.close();
    }
    
    public Connection getConnection() {
        return connection;
    }
}
