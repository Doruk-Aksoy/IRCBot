package Command;

import java.sql.SQLException;
import java.sql.ResultSet;

import ConstantData.Constant_Data_Manager;
import ConstantData.Feature_CD;
import Database.*;
import Message.Message;
import Parsing.*;
import UserType.*;
import ircbot.IRCBot;
import ircbot.IRCBot_UserManager;


public class Command_Stats implements Command {
    private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    private boolean verifyFormat(String[] s) {
        if(s != null && s.length == 2)
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
    
    // checks user score
    @Override public void operate(Message msg) {
        IRCBot Bot = IRCBot.getInstance();
        Parser p = new StringSeperator();
        String[] text = p.parse(msg.getText());
        String sender = msg.getSender();
        Database_Connection DB = Database_Connection.getInstance();
        Bot.sendMessage(msg, Constant_Data_Manager.stat_begin_message);
        try {
            Query_Handler QH = new Query_Handler();
            DB.connect();
            // 1 is the first parameter which can refer to a user name on the channel or database
            // checking channel is easier so we will start with that
            // if the user in the channel has actually logged in, search our list
            IRCBot_UserManager UM = Bot.getUserManager();
            GameUser u = UM.getUser(sender, text[1]);
            String search_name = sender;
            // now this means that the user actually entered a login that's different than the IRC name
            // and also logged in previously in current bot session
            if(u != null)
                search_name = u.getUserName();
            // now query from the db
            if(!QH.getUserScores(DB.getConnection(), search_name))
                Bot.sendMessage(msg, Constant_Data_Manager.stat_nouser);
            else {
                Bot.getFeature(Feature_CD.stat_evaluation).execute(msg, QH.getMostRecentResult());
            }
        }
        catch (SQLException e) {
            Bot.sendMessage(sender, Constant_Data_Manager.register_dbconnection_fail);
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
