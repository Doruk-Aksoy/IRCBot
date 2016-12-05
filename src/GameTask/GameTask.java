package GameTask;

import Games.ChatGame;
import java.util.concurrent.Callable;

public abstract class GameTask implements Callable {
    protected String source; // this is the source, can be a channel or a user
    protected ChatGame game; // this is the game that the task is associated with
    
    public GameTask() { 
        source = null;
        game = null;
    }
    
    public GameTask(String s, ChatGame g) {
        source = s;
        game = g;
    }

    public void setSource(String s) {
        source = s;
    }
    
    public void setGame(ChatGame g) {
        game = g;
    }
    
    public ChatGame getGame() {
        return game;
    }
    
    @Override public abstract String call();
}
