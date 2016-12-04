package Games;

// includes an object of this type, because they need to manage their own users actively playing in this
import ircbot.datamanager.IRCBot_UserManager;

public abstract class ChatGame {
    protected IRCBot_UserManager um;
    protected boolean game_over;
    protected String source;
    
    public ChatGame(String c) {
        um = new IRCBot_UserManager();
        game_over = false;
        source = c;
    }
    
    public String getChannel() {
        return source;
    }
    
    // checks if this game is an unranked game (sent via PM) or ranked (sent in a channel)
    public boolean isRanked() {
        return source != null && source.charAt(0) == '#';
    }
    
    // Use the template pattern to fill these methods later, but provide a definite skeleton for the games to follow
    public abstract void initialize();
    public abstract void play();
    public abstract void finish();
    
    public String run() {
        try {
            initialize();
            play();
            finish();
        }
        catch(Exception e) {
            System.out.println(e);
            return "Fail";
        }
        return "Success";
    }
}
