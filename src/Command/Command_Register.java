package Command;

import java.sql.*;

import ConstantData.Constant_Data_Manager;
import Database.Database_Connection;
import Message.Message;
import Parsing.*;
import ircbot.IRCBot;

public class Command_Register implements Command {
    public Command_Register() { }
    
    private boolean verifyFormat(String[] s) {
        if(s != null && s.length == 3)
            return true;
        return false;
    }
    
    @Override public void operate(Message msg) {
        Parser p = new StringSeperator();
        String[] text = p.parse(msg.getText());
        IRCBot Bot = IRCBot.getInstance();
        Database_Connection DB = Database_Connection.getInstance();
        String toSend = null;
        String sender = msg.getSender();
        if(verifyFormat(text) == true) {
            toSend = Constant_Data_Manager.register_begin_message;
            Bot.sendMessage(sender, toSend);
            try {
                DB.connect();
                // 1 is the first parameter
                if(DB.userExists(text[1]))
                    toSend = Constant_Data_Manager.register_user_exists;
                else {
                    DB.addUser(text[1], text[2]);
                    toSend = Constant_Data_Manager.register_success;
                }
            }
            catch (SQLException e) {
                toSend = Constant_Data_Manager.register_dbconnection_fail;
                Bot.sendMessage(sender, toSend);
                System.out.println(e);
            }
            // close database after registering
            finally {
                try {
                    DB.disconnect();
                }
                catch(SQLException e) {
                    System.out.println(e);
                }
            }
        }
        else {
            // data sent to bot was invalid
            toSend = Constant_Data_Manager.register_bad_format;
        }
        Bot.sendMessage(sender, toSend);
    }
}
