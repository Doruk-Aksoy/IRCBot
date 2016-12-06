package ircbot;

// Update here later with generic stuff and GUI!
// This is the part that has the main execution
public class IRCBotMain {
    public static void main(String[] args) throws Exception {
        IRCBot Bot = IRCBot.getInstance();
        Bot.makeName("Test_Bot");
        // Connect to IRC -- TODO: Take input from user via GUI interaction
        Bot.connect("irc.esper.net");
        Bot.joinChannel("#test123", true);
    }
}
