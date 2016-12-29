package Command;

import java.util.Arrays;

import ConstantData.Game_Data;
import ConstantData.Message_Data;
import Games.ChatGame;
import Games.GameData.GameState;
import Mediator.BotMediator;
import Mediator.GameMediator;
import Message.Message;
import Parsing.Parser;
import Parsing.StringSeperator;
import UserType.GameUser;

public class Command_Answer implements Command {
    private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    private boolean verifyFormat(String[] s) {
        return s != null && s.length > 2;
    }
    
    // this command requires you to be logged in in order to use
    @Override public Command.Command_Validity validate(Message msg) {
        Parser p = new StringSeperator();
        text = p.parse(msg.getText(), Parser.spaces);
        if(!verifyFormat(text))
            return Command.Command_Validity.CMD_BADFORMAT;
        if(!BotMediator.isLoggedIn(msg.getSender()))
            return Command.Command_Validity.CMD_REQUIRELOGIN;
        return Command.Command_Validity.CMD_VALID;
    }
    
    // refactor this so bad...
    @Override public void operate(Message msg) {
        // first check if this user is in any game
        GameUser u = BotMediator.getUser(msg.getSender());
        if(u.isInGame()) {
            ChatGame G = GameMediator.gameExists(text[1]);
            if(G != null) {
                // check if this user is in this game
                if(GameMediator.userInGame(G, u)) {
                    // only check if this user can answer or hasn't answered
                    if(u.getAnswerCount() == Game_Data.CORRECTLY_ANSWERED) {
                        BotMediator.sendMessage(msg, Message_Data.already_correctly_answered);
                        return;
                    }
                    else if(u.getAnswerCount() == 0) {
                        BotMediator.sendMessage(msg, Message_Data.no_more_answers);
                        return;
                    }
                    // only accept user answers if the game is accepting answers
                    if(G.getGameState().getState() == GameState.State.STAT_WANTANSWER) {
                        GameMediator.acceptAnswer(G, u, Arrays.copyOfRange(text, 2, text.length));
                    }
                    else
                        BotMediator.sendMessage(msg, Message_Data.game_noans);
                }
                else
                    BotMediator.sendMessage(msg, Message_Data.not_in_this_game);
            }
            else
                BotMediator.sendMessage(msg, Message_Data.game_doesnotexist);
        }
        else
            BotMediator.sendMessage(msg, Message_Data.not_in_any_game);
    }
}
