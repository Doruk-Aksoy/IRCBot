package Command;

import ConstantData.*;
import Games.ChatGame;
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
        return isGameName(s[1]);
    }
    
    // this command requires you to be logged in in order to use
    @Override public Command.Command_Validity validate(Message msg) {
        Parser p = new StringSeperator();
        text = p.parse(msg.getText());
        if(!verifyFormat(text))
            return Command.Command_Validity.CMD_BADFORMAT;
        if(!BotMediator.isLoggedIn(msg.getSender()))
            return Command.Command_Validity.CMD_REQUIRELOGIN;
        return Command.Command_Validity.CMD_VALID;
    }
    
    private boolean isGameName(String s) {
        for(String comp : Game_Data.game_names) {
            if(comp.equals(s.toLowerCase()))
                return true;
        }
        return false;
    }
    
    @Override public void operate(Message msg) {
        // first check if this user is in any game
        GameUser u = BotMediator.getUser(msg.getSender());
        if(u.isInGame())
            BotMediator.sendMessage(msg, Message_Data.ingame_already);
        else {
            // check if a game like this was created before, if not, create
            // make a base game for comparisons
            BotMediator.sendMessage(msg, "Attempting to start Scramble game...");
            ChatGame template = GameMediator.makeGame(msg);
            ChatGame G = GameMediator.gameExists(template);
            // if game is created before
            if(G != null) {
                // check if game is not full and in countdown state, allow the player to join it if so
                if(GameMediator.isFull(G) == false && G.getGameState() == ChatGame.State.STAT_COUNTDOWN) {
                    // only allow a user to play one game at a time
                    u.setGameStatus(true);
                    GameMediator.addUser(G, u);
                    BotMediator.sendMessage(msg, u.getIRCName() + " has joined the " + G.getName() + " game!");
                    // if we're full, don't wait
                    if(GameMediator.getUserCount(G) == Game_Data.GAME_max_players)
                        G.abortCurrentTask();
                }
                else
                    BotMediator.sendMessage(msg, Message_Data.game_nojoin);
            }
            else { 
                // create the game, and join this player in it
                // complete total construction of this game
                template.makeWhole();
                u.setGameStatus(true);
                GameMediator.addUser(template, u);
                GameMediator.addGame(template);
                if(template.isRanked())
                    BotMediator.sendMessage(msg, u.getIRCName() + " has joined the " + template.getName() + " game!");
                else
                    System.out.println("Started unranked game with " + u.getIRCName() + ".");
            }
        }
    }
}
