package GameTask;

import Mediator.BotMediator;

// Accepts join commands of players and starts the game
public class GameTask_AcceptPlayers extends GameTask {
    public void run() {
        if(source != null) {
            BotMediator.sendMessage(source, "The " + game_name + " game is ready to begin!");
        }
        // stop the timer from repeating
        this.timer.cancel();
    }
}
