package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query_Handler {
    private ResultSet rs = null;
    private final String users_table_format = "Users(login, password, score, highest_score, games_won, games_played)";
    
    public Query_Handler() { }
    
    public ResultSet query(Connection connection, String s) throws SQLException {
        PreparedStatement st = connection.prepareStatement(s);
        rs = st.executeQuery();
        return rs;
    }
    
    public boolean userExists(Connection connection, String username) throws SQLException {
        rs = query(connection, "SELECT login FROM Users WHERE login='" + username + "'");
        return rs.next();
    }
    
    public boolean checkPassword(Connection connection, String username, String password) throws SQLException {
        rs = query(connection, "SELECT password, login, ID FROM Users WHERE login='" + username + "' AND password='" + password + "'");
        return rs.next();
    }
    
    public ResultSet getMostRecentResult() {
        return rs;
    }
    
    public boolean getUserScores(Connection connection, String username) throws SQLException {
        rs = query(connection, "SELECT score, highest_score, games_won, games_played FROM Users WHERE login='" + username + "'");
        return rs.next();
    }
    
    public void addUser(Connection connection, String name, String password) throws SQLException {
        rs = query(connection, "INSERT INTO " + users_table_format + " VALUES('" + name + "', '" + password + "', 0, 0, 0, 0)");
    }
}
