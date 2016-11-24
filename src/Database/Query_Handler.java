package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query_Handler {
    private ResultSet rs = null;
    
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
        rs = query(connection, "SELECT high_score, games_won FROM Users WHERE login='" + username + "'");
        return rs.next();
    }
    
    public void addUser(Connection connection, String name, String password) throws SQLException {
        rs = query(connection, "INSERT INTO Users(login, password, high_score, games_won) VALUES('" + name + "', '" + password + "', 0, 0)");
    }
}
