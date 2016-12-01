package UserType;

public class UserCredentials {
    private String IRCname;
    private String username; // this is the database login and this is unique
    private String login; // this is the IRC login!
    private String hostname;
    
    public UserCredentials() {
        IRCname = ""; username = ""; login = ""; hostname = "";
    }
    
    public UserCredentials(String name, String username, String login, String hostname) {
        this.IRCname = name;
        this.username = username;
        this.login = login;
        this.hostname = hostname;
    }
    
    public String getIRCName() {
        return IRCname;
    }
    
    public String getUserName() {
        return username;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public void setIRCName(String name) {
        this.IRCname = name;
    }
    
    public void setUserName(String username) {
        this.username = username;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
