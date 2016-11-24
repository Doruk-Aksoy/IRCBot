package UserType;

import java.util.ArrayList;

public abstract class GameUser {
    public enum User_Type {
        USER_NORMAL, USER_ADMIN
    }
    
    protected Integer ID;
    protected Integer score;
    protected User_Type type;
    protected ArrayList<String> keywords;
    protected UserCredentials uc;
    
    public GameUser() { }
    
    public Integer getId() {
        return this.ID;
    }
    
    public Integer getScore() {
        return this.score;
    }
    
    public User_Type getType() {
        return this.type;
    }
    
    public String getIRCName() {
        return uc.getIRCName();
    }
    
    // returns the database name of the user
    public String getUserName() {
        return uc.getUserName();
    }
    
    public UserCredentials getCredentials() {
        return uc;
    }
    
    public void setType(User_Type T) {
        this.type = T;
    }
    
    public ArrayList<String> getKeywords() {
        return keywords;
    }
    
    public boolean isKeyword(String word) {
        return keywords.contains(word);
    }
    
    public void addKeyword(String word) {
        keywords.add(word);
    }
    
    public void setIRCName(String name) {
        uc.setIRCName(name);
    }
}
