package Command;

import java.sql.SQLException;
import java.sql.ResultSet;

import ConstantData.Message_Data;
import Database.*;
import Mediator.BotMediator;
import Message.Message;
import Parsing.*;
import UserType.*;

public class Command_Login implements Command {
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
        String sender = msg.getSender();
        Database_Connection DB = Database_Connection.getInstance();
        BotMediator.sendMessage(msg, Message_Data.login_begin_message);
        try {
            Query_Handler QH = new Query_Handler();
            DB.connect();
            // 1 is the first parameter, login
            if(!QH.userExists(DB.getConnection(), text[1]))
                BotMediator.sendMessage(msg, Message_Data.login_nouser);
            else if(BotMediator.isLoggedIn(msg.getSender()))
                BotMediator.sendMessage(msg, Message_Data.login_already);
            else {
                // get the md5 hash of password
                Parser hasher = new MD5Hasher();
                String pass = hasher.parseSingle(text[2]);
                if(QH.checkPassword(DB.getConnection(), text[1], pass)) {
                    ResultSet rs = QH.getMostRecentResult();
                    // we know this has to be a unique result
                    UserCredentials uc = new UserCredentials(sender, text[1], msg.getLogin(), msg.getHostname());
                    GameUser user = new NormalUser(uc, rs.getInt("ID"), 0);
                    BotMediator.addUser(user);
                    BotMediator.sendMessage(msg, Message_Data.login_success);
                }
                else
                    BotMediator.sendMessage(msg, Message_Data.login_invalid_password);
            }
        }
        catch (SQLException e) {
            BotMediator.sendMessage(sender, Message_Data.register_dbconnection_fail);
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
}
