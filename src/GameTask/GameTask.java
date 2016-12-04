package GameTask;

public abstract class GameTask implements Runnable {
    protected String source; // this is the source, can be a channel or a user
    protected String game_name; // name of the game
    
    public GameTask() { 
        source = null;
    }
    
    GameTask(String s, String g) {
        source = s;
        game_name = g;
    }   

    public void setSource(String s) {
        source = s;
    }
    
    public void setGameName(String g) {
        game_name = g;
    }
    
    @Override public abstract void run();
}
