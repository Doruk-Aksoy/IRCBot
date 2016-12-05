package GameTask;

import Games.ChatGame;
import Mediator.BotMediator;

// Accepts join commands of players and starts the game
public class GameTask_AcceptPlayers extends GameTask {
    public GameTask_AcceptPlayers(String s, ChatGame g) {
        super(s, g);
    }
    
    @Override public String call() {
        if(source != null) {
            BotMediator.sendMessage(source, "The " + game.getName() + " game is ready to begin!");
        }
        game.setGameState(ChatGame.State.STAT_ONGOING);
        return "Timer stopped.";
    }
}
