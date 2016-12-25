package UserType;

public abstract class GameUser {
    public enum User_Type {
        USER_NORMAL, USER_ADMIN
    }
    
    // id is unique and given in database
    protected long ID;
    protected long score;
    protected User_Type type;
    protected UserCredentials uc;
    protected long game_id;
    protected int answer_count;
    protected boolean has_won;
    
    public GameUser() { }
    
    public long getId() {
        return ID;
    }
    
    public long getScore() {
        return score;
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
    
    public int getAnswerCount() {
        return answer_count;
    }
    
    public void setAnswerCount(int a) {
        answer_count = a;
    }
    
    public void setType(User_Type T) {
        this.type = T;
    }
    
    public void setIRCName(String s) {
        uc.setIRCName(s);
    }
    
    public boolean isInGame() {
        return game_id != 0;
    }
    
    public void setGameStatus(long id) {
        game_id = id;
    }
    
    public void addScore(long s) {
        score += s;
    }
    
    public void deduceAnswerCount() {
        if(answer_count > 0)
            answer_count--;
    }
    
    public boolean hasWon() {
        return has_won;
    }
    
    public void setWinner(boolean w) {
        has_won = w;
    }
    
    public void resetStats() {
        score = 0;
        has_won = false;
        answer_count = 0;
    }
}
