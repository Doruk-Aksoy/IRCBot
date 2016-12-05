package Mediator;

import Message.Message;
import UserType.GameUser;
import ircbot.Features.IRCBot_Feature;
import ircbot.IRCBot;
import ircbot.datamanager.IRCBot_UserManager;

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
        IRCBot_UserManager UM = Bot.getDataManager().getUserManager();
        return UM.getUser(name);
    }
    
    public static GameUser getUser(String IRCName, String name) {
        IRCBot Bot = IRCBot.getInstance();
        IRCBot_UserManager UM = Bot.getDataManager().getUserManager();
        return UM.getUser(IRCName, name);
    }
    
    public static boolean isLoggedIn(String IRCName) {
        IRCBot Bot = IRCBot.getInstance();
        IRCBot_UserManager UM = Bot.getDataManager().getUserManager();
        return UM.getUser(IRCName) != null;
    }
    
    public static void addUser(GameUser u) {
        IRCBot Bot = IRCBot.getInstance();
        IRCBot_UserManager UM = Bot.getDataManager().getUserManager();
        UM.addUser(u);
    }
    
    public static void deleteUser(int i) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.getDataManager().getUserManager().deleteUser(i);
    }
    
    public static void deleteUser(GameUser u) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.getDataManager().getUserManager().deleteUser(u);
    }
    
    public static IRCBot_Feature getFeature(String s) {
       IRCBot Bot = IRCBot.getInstance();
       return Bot.getFeature(s);
    }
    
    public static void addChannel(String s) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.getDataManager().getChannelManager().addChannel(s);
    }
    
    public static void deleteChannel(int i) {
        IRCBot Bot = IRCBot.getInstance();
        try {
            Bot.getDataManager().getChannelManager().deleteChannel(i);
        } catch (IndexOutOfBoundsException I) {
            System.err.println("IndexOutOfBoundsException: " + I.getMessage());
        }
    }
    
    // exception free version
    public static void deleteChannel(String s) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.getDataManager().getChannelManager().deleteChannel(s);
    }
    
    public static String getChannel(int i) {
        IRCBot Bot = IRCBot.getInstance();
        try {
            return Bot.getDataManager().getChannelManager().getChannel(i);
        } catch(IndexOutOfBoundsException I) {
            System.err.println("IndexOutOfBoundsException: " + I.getMessage());
            return null;
        }
    }
    
    public static String getChannel(String c) {
        IRCBot Bot = IRCBot.getInstance();
        return Bot.getDataManager().getChannelManager().getChannel(c);
    }
}
