package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Database_Info {
    public static final String default_url = "/Users/User/Documents/Programming/Java/Bot/database.txt";
    public static final String db_url_tag = "Database URL = ";
    public static final String db_login_tag = "Database Login = ";
    public static final String db_password_tag = "Database Password = ";
    
    protected static String DB_URL = null;
    protected static String DB_LOGIN = null;
    protected static String DB_PASSWORD = null;
    
    public static void reset() {
        DB_URL = DB_LOGIN = DB_PASSWORD = null;
    }
    
    public static String getURL() {
        return DB_URL;
    }
    
    public static String getLogin() {
        return DB_LOGIN;
    }
    
    public static String getPassword() {
        return DB_PASSWORD;
    }
    
    public static void read(File f) throws IOException {
       BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null) {
            if(DB_URL == null)
                DB_URL = (line.substring(line.indexOf(Database_Info.db_url_tag) + Database_Info.db_url_tag.length(), line.length()));
            else if(DB_LOGIN == null)
                DB_LOGIN = (line.substring(line.indexOf(Database_Info.db_login_tag) + Database_Info.db_login_tag.length(), line.length()));
            else if(DB_PASSWORD == null)
                DB_PASSWORD = (line.substring(line.indexOf(Database_Info.db_password_tag) + Database_Info.db_password_tag.length(), line.length()));
        }
    }
}
