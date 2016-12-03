package ircbot.datamanager;

public class IRCBot_DataManager {
    private final IRCBot_UserManager user_manager;
    private final IRCBot_ChannelManager channel_manager;
    private final IRCBot_GameManager game_manager;
    
    public IRCBot_DataManager() {
        this.channel_manager = new IRCBot_ChannelManager();
        this.user_manager = new IRCBot_UserManager();
        this.game_manager = new IRCBot_GameManager();
    }
    
    public IRCBot_UserManager getUserManager() {
        return user_manager;
    }
    
    public IRCBot_ChannelManager getChannelManager() {
        return channel_manager;
    }
    
    public IRCBot_GameManager getGameManager() {
        return game_manager;
    }
}
