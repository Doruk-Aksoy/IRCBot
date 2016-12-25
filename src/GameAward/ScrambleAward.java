package GameAward;

import ConstantData.Game_Data;
import Games.ChatGame;
import Games.GameData.GameInfo;
import Mediator.BotMediator;
import UserType.GameUser;

public class ScrambleAward implements GameAward {
    @Override public void award(GameUser U, ChatGame G) {
        long pts = Game_Data.scramble_point;
        GameInfo g_data = G.getGameInfo();
        if(!g_data.getFirstCorrectAnswer()) {
            U.addScore(pts);
            g_data.setFirstCorrectAnswer(true);
            BotMediator.sendMessage(g_data.getSource(), U.getIRCName() + " is the first player to answer correctly and claims " + pts + " points!");
            BotMediator.sendMessage(U.getIRCName(), "You have answered correctly and received " + pts + " points!");
        }
        else {
            long delay = G.getGameDelay().remainingDelay();
            pts *= delay;
            pts /= (Game_Data.scramble_time_per_question);
            U.addScore(pts);
            BotMediator.sendMessage(g_data.getSource(), U.getIRCName() + " has answered correctly after " + Game_Data.scramble_time_per_question + " seconds and receives " + pts + " points!");
            BotMediator.sendMessage(U.getIRCName(), "You have answered correctly after " + Game_Data.scramble_time_per_question + " seconds and received " + pts + " points!");
        }
        U.setAnswerCount(Game_Data.CORRECTLY_ANSWERED);
        g_data.addAnswer();
        if(G.allAnswersReceived())
            G.abortCurrentTask();
    }
}
