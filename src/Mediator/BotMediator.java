package Mediator;

import Message.Message;
import UserType.GameUser;
import ircbot.Features.IRCBot_Feature;
import ircbot.IRCBot;
import ircbot.IRCBot_UserManager;

// hides request of bot instance everytime when a bot operation is required
public class BotMediator {
    public static void sendMessage(String source, String text) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.sendMessage(source, text);
    }
    
    public static void sendMessage(Message msg, String text) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.sendMessage(msg, text);
    }
    
    public static GameUser getUser(String name) {
        IRCBot Bot = IRCBot.getInstance();
        IRCBot_UserManager UM = Bot.getUserManager();
        return UM.getUser(name);
    }
    
    public static GameUser getUser(String IRCName, String name) {
        IRCBot Bot = IRCBot.getInstance();
        IRCBot_UserManager UM = Bot.getUserManager();
        return UM.getUser(IRCName, name);
    }
    
    public static void addUser(GameUser u) {
        IRCBot Bot = IRCBot.getInstance();
        IRCBot_UserManager UM = Bot.getUserManager();
        UM.addUser(u);
    }
    
    public static IRCBot_Feature getFeature(String s) {
       IRCBot Bot = IRCBot.getInstance();
       return Bot.getFeature(s);
    }
}
