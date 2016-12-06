package GameTask;

import Games.ChatGame;

public class GameTask_ShowAnswer extends GameTask {
    public GameTask_ShowAnswer(String s, ChatGame g) {
        super(s, g);
    }
    
    @Override public String call() {
        game.setGameState(ChatGame.State.STAT_ONGOING);
        return "Answer shown.";
    }
}
