package Games;

import UserType.GameUser;
import java.util.ArrayList;

public abstract class ChatGame {
    protected ArrayList<GameUser> players;
    protected boolean game_over;
    
    public ChatGame() {
        players = new ArrayList<>();
        game_over = false;
    }
    
    // Use the template pattern to fill these methods later, but provide a definite skeleton for the games to follow
    public abstract void initialize();
    public abstract void play();
    public abstract void finish();
    
    public void run() {
        initialize();
        play();
        finish();
    }
}
