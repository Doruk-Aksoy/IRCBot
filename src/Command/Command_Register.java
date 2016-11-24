package Command;

import java.sql.*;

import ConstantData.Constant_Data_Manager;
import Database.Database_Connection;
import Database.Query_Handler;
import Message.Message;
import Parsing.*;
import ircbot.IRCBot;

public class Command_Register implements Command {
    private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    private boolean verifyFormat(String[] s) {
        if(s != null && s.length == 3)
            return true;
        return false;
    }
    
    @Override public Command_Validity validate(Message msg) {
        if(msg.getType() != Message.Message_Type.MSG_PM)
            return Command_Validity.CMD_POSSIBLYBAD;
        Parser p = new StringSeperator();
        text = p.parse(msg.getText());
        if(!verifyFormat(text))
            return Command_Validity.CMD_BADFORMAT;
        return Command_Validity.CMD_VALID;
    }
    
    @Override public void operate(Message msg) {
        IRCBot Bot = IRCBot.getInstance();
        String toSend = null;
        String sender = msg.getSender();
        Parser p = new StringSeperator();
        String[] text = p.parse(msg.getText());
        if(verifyFormat(text) == true) {
            Database_Connection DB = Database_Connection.getInstance();
            toSend = Constant_Data_Manager.register_begin_message;
            Bot.sendMessage(sender, toSend);
            try {
                Query_Handler QH = new Query_Handler();
                DB.connect();
                // 1 is the first parameter
                if(QH.userExists(DB.getConnection(), text[1]))
                    toSend = Constant_Data_Manager.register_user_exists;
                else {
                    // save the md5 hash of password
                    Parser hasher = new MD5Hasher();
                    // get 1st element of returned array
                    QH.addUser(DB.getConnection(), text[1], hasher.parse(text[2])[0]);
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
            toSend = Constant_Data_Manager.bad_format;
        }
        Bot.sendMessage(msg, toSend);
    }
}
