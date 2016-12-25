package GameTask;

import Games.ChatGame;

public class GameTask_Dummy extends GameTask {
    public GameTask_Dummy() {
        super();
    }
    
    public GameTask_Dummy(String s, ChatGame g) {
        super(s, g);
    }
    
    @Override public String call() {
        return "Dummy Task Complete.";
    }
}
