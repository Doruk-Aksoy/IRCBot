package GameTask;

import java.util.Timer;
import java.util.TimerTask;

public abstract class GameTask extends TimerTask {
    protected Timer timer; // timer reference to end when the timer, optional
    protected String source; // this is the source, can be a channel or a user
    protected String game_name; // name of the game
    
    public GameTask() { 
        source = null;
    }
    
    public GameTask(Timer t) {
        timer = t;
        source = null;
    }
    
    GameTask(Timer t, String s, String g) {
        timer = t;
        source = s;
        game_name = g;
    }   
    
    public void setTimer(Timer t) {
        this.timer = t;
    }
    
    @Override public abstract void run();
}
