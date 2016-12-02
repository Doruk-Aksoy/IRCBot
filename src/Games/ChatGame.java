package Games;

import UserType.GameUser;
import java.util.ArrayList;

public abstract class ChatGame {
    protected ArrayList<GameUser> players;
    
    public abstract void run();
}
