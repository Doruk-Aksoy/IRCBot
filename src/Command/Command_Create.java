package Command;

import ConstantData.Game_Data;
import ConstantData.Message_Data;
import Games.ChatGame;
import Mediator.BotMediator;
import Mediator.GameMediator;
import Message.Message;
import Parsing.Parser;
import Parsing.StringSeperator;
import UserType.GameUser;

public class Command_Create implements Command {
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
        GameUser u = BotMediator.getUser(msg.getSender());
        if(u.isInGame())
            BotMediator.sendMessage(msg, Message_Data.ingame_cantmakegame);
        else {
            // create the game, and join this player in it
            // complete total construction of this game
            ChatGame G = GameMediator.makeGame(msg);
            BotMediator.sendMessage(msg, "Attempting to start " + G.getGameInfo().getName() + " game...");
            u.setGameStatus(G.getGameInfo().getGameID());
            GameMediator.addUser(G, u);
            GameMediator.addGame(G);
            if(G.isRanked())
                BotMediator.sendMessage(msg, u.getIRCName() + " has created the " + G.getGameInfo().getName() + " game! Game reference ID: " + G.getGameInfo().getGameID() + ". Use this to join the game!");
            else {
                BotMediator.sendMessage(msg, "Unranked Scramble game with ID " + G.getGameInfo().getGameID() + " has been created! Use this ID to answer using .ans command.");
                System.out.println("Started unranked game with " + u.getIRCName() + ".");
            }
        }
    }
}
