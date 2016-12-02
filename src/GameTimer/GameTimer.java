package GameTimer;

import java.util.Timer;

import GameTask.GameTask;

public class GameTimer {
    private Timer timer;
    
    // with initial delays
    public GameTimer(int seconds, GameTask G) {
        timer = new Timer();
        G.setTimer(timer);
        timer.schedule(G, seconds * 1000);
    }
    
    // with initial delays and subsequent repetition delays
    public GameTimer(int seconds, int rep, GameTask G) {
        timer = new Timer();
        G.setTimer(timer);
        timer.schedule(G, seconds * 1000, rep * 1000);
    }
}
