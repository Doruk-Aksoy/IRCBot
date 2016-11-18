package UserType;

public abstract class GameUser {
    public enum User_Type {
        USER_NORMAL, USER_ADMIN
    }
    
    protected Integer id;
    protected Integer score;
    protected User_Type type;
    protected UserCredentials uc;
    
    public GameUser() { }
    
    public Integer getId() {
        return this.id;
    }
    
    public Integer getScore() {
        return this.id;
    }
    
    public User_Type getType() {
        return this.type;
    }
    
    public String getName() {
        return uc.getName();
    }
    
    public void setType(User_Type T) {
        this.type = T;
    }
}
