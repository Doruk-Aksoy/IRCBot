package UserType;

public class UserCredentials {
    private String name;
    private String login;
    private String hostname;
    private int ID;
    
    public UserCredentials() {
        name = ""; login = ""; hostname = "";
    }
    
    public UserCredentials(String name, String login, String hostname) {
        this.name = name;
        this.login = login;
        this.hostname = hostname;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
}
