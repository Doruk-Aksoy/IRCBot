package Games;

import java.util.concurrent.ScheduledFuture;

import GameTask.GameTask;

// includes an object of this type, because they need to manage their own users actively playing in this
import ircbot.datamanager.IRCBot_UserManager;

public abstract class ChatGame {
    public enum State {
        STAT_COUNTDOWN,
        STAT_ONGOING,
        STAT_FINISHED,
        STAT_DATABASE,
        STAT_DONE
    }
    
    protected IRCBot_UserManager um;
    protected boolean game_over;
    protected String source;
    protected State game_state;
    protected ScheduledFuture pending_future;
    protected GameTask pending_task;
    
    public ChatGame(String c) {
        um = new IRCBot_UserManager();
        game_over = false;
        game_state = State.STAT_COUNTDOWN;
        source = c;
        pending_future = null;
        pending_task = null;
    }
    
    // weak construction on purpose, don't waste resources for a temp object
    public ChatGame(String c, boolean weak) {
        um = null;
        game_over = false;
        source = c;
        pending_future = null;
        pending_task = null;
    }
    
    // finish the construction of the half constructed object
    public void makeWhole() {
        if(um == null) {
            um = new IRCBot_UserManager();
            game_state = State.STAT_COUNTDOWN;
        }
    }
    
    public String getChannel() {
        return source;
    }
    
    // checks if this game is an unranked game (sent via PM) or ranked (sent in a channel)
    public boolean isRanked() {
        return source != null && source.charAt(0) == '#';
    }
    
    public State getGameState() {
        return game_state;
    }
    
    public void setGameState(State s) {
        game_state = s;
    }
    
    public IRCBot_UserManager getUserManager() {
        return um;
    }
    
    public boolean abortCurrentTask() {
        return pending_future.cancel(true);
    }

    // Use the template pattern to fill these methods later, but provide a definite skeleton for the games to follow
    public abstract void initialize();
    public abstract void play();
    public abstract void finish();
    public abstract String getName();
    
    public String run() {
        try {
            initialize();
            play();
            finish();
        }
        catch(Exception e) {
            System.out.println(e + " happened!!!!");
            return getName() + " Failed";
        }
        return "Success";
    }
}
