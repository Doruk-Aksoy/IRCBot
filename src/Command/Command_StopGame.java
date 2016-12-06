package Command;

import java.util.HashMap;
import java.util.concurrent.Future;

import Games.ChatGame;
import Mediator.BotMediator;
import Mediator.GameMediator;
import Message.Message;
import Parsing.Parser;
import Parsing.StringSeperator;

public class Command_StopGame implements Command {
   private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    private boolean verifyFormat(String[] s) {
        return s != null && s.length == 2;
    }
    
    // this command requires you to be logged in in order to use
    @Override public Command.Command_Validity validate(Message msg) {
        if(msg.getType() != Message.Message_Type.MSG_GAMEPM)
            return Command_Validity.CMD_POSSIBLYBAD;
        Parser p = new StringSeperator();
        text = p.parse(msg.getText());
        if(!verifyFormat(text))
            return Command.Command_Validity.CMD_BADFORMAT;
        if(!BotMediator.isLoggedIn(msg.getSender()))
            return Command.Command_Validity.CMD_REQUIRELOGIN;
        return Command.Command_Validity.CMD_VALID;
    }
    
    // refactor this so bad...
    @Override public void operate(Message msg) {
        HashMap.Entry<ChatGame, Future<String>> entry = GameMediator.getGameFuturePair(text[1]);
        ChatGame G = entry.getKey();
        Future<String> F = entry.getValue();
        if(G != null && msg.getSender().equals("IvanDobrovski")) {
            GameMediator.terminate(G, F);
        }
    }
}
