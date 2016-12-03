package Command;

import java.sql.*;

import ConstantData.Message_Data;
import Database.Database_Connection;
import Database.Query_Handler;
import Mediator.BotMediator;
import Message.Message;
import Parsing.*;

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
        String toSend = null;
        String sender = msg.getSender();
        Parser p = new StringSeperator();
        if(verifyFormat(text) == true) {
            Database_Connection DB = Database_Connection.getInstance();
            toSend = Message_Data.register_begin_message;
            BotMediator.sendMessage(sender, toSend);
            try {
                Query_Handler QH = new Query_Handler();
                DB.connect();
                // 1 is the first parameter
                if(QH.userExists(DB.getConnection(), text[1]))
                    toSend = Message_Data.register_user_exists;
                else {
                    // save the md5 hash of password
                    Parser hasher = new MD5Hasher();
                    // get 1st element of returned array
                    QH.addUser(DB.getConnection(), text[1], hasher.parse(text[2])[0]);
                    toSend = Message_Data.register_success;
                }
            }
            catch (SQLException e) {
                toSend = Message_Data.register_dbconnection_fail;
                BotMediator.sendMessage(sender, toSend);
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
            toSend = Message_Data.bad_format;
        }
        BotMediator.sendMessage(msg, toSend);
    }
}
