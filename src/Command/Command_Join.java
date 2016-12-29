package Command;

import ConstantData.*;
import Games.ChatGame;
import Games.GameData.GameState;
import Mediator.*;
import Message.Message;
import Parsing.Parser;
import Parsing.StringSeperator;
import UserType.GameUser;

public class Command_Join implements Command {
    private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    private boolean verifyFormat(String[] s) {
        if(s == null || s.length != 2)
            return false;
        return isInteger(s[1], 10);
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
    
    private boolean isInteger(String s, int base) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i), base) < 0) return false;
        }
        return true;
    }
    
    @Override public void operate(Message msg) {
        // first check if this user is in any game
        GameUser u = BotMediator.getUser(msg.getSender());
        if(u.isInGame())
            BotMediator.sendMessage(msg, Message_Data.ingame_already);
        else {
            // check if this game exists
            ChatGame G = GameMediator.gameExists(text[1]);
            if(G != null) {
                // check if game is not full and in countdown state, allow the player to join it if so
                if(GameMediator.isFull(G) == false && G.getGameState().getState() == GameState.State.STAT_COUNTDOWN) {
                    // only allow a user to play one game at a time
                    u.setGameStatus(G.getGameInfo().getGameID());
                    GameMediator.addUser(G, u);
                    BotMediator.sendMessage(msg, u.getIRCName() + " has joined the " + G.getGameInfo().getName() + " game!");
                    // if we're full, don't wait
                    if(GameMediator.getUserCount(G) == Game_Data.GAME_max_players)
                        G.abortCurrentTask();
                }
                else
                    BotMediator.sendMessage(msg, Message_Data.game_nojoin);
            }
            else
                BotMediator.sendMessage(msg, Message_Data.game_doesnotexist);
        }
    }
}
