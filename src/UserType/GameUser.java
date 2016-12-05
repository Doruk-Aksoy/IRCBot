package UserType;

public abstract class GameUser {
    public enum User_Type {
        USER_NORMAL, USER_ADMIN
    }
    
    // id is unique
    protected Integer ID;
    protected Integer score;
    protected User_Type type;
    protected UserCredentials uc;
    protected boolean in_game;
    
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
    
    public void setIRCName(String s) {
        uc.setIRCName(s);
    }
    
    public boolean isInGame() {
        return in_game;
    }
    
    public void setGameStatus(boolean s) {
        in_game = true;
    }
}
