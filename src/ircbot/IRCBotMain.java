package ircbot;

import Database.Database_Connection;

// This is the part that has the main execution
public class IRCBotMain {
    public static void main(String[] args) throws Exception {
        // Establish Database connection for users
        Database_Connection DB = Database_Connection.getInstance();
        // DB.connect();
        IRCBot Bot = IRCBot.getInstance();
        Bot.makeName("Test_Bot");
        // initiate database connection
        // Connect to IRC -- TODO: Take input from user via GUI interaction
        Bot.connect("irc.esper.net");
        Bot.joinChannel("#mxu");
    }
}
