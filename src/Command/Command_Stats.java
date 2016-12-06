package Command;

import java.sql.SQLException;

import ConstantData.Message_Data;
import ConstantData.Feature_CD;
import Database.*;
import Mediator.BotMediator;
import Message.Message;
import Parsing.*;
import UserType.*;


public class Command_Stats implements Command {
    private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    private boolean verifyFormat(String[] s) {
        return s != null && s.length == 2;
    }
    
    @Override public Command_Validity validate(Message msg) {
        Parser p = new StringSeperator();
        text = p.parse(msg.getText());
        if(!verifyFormat(text))
            return Command_Validity.CMD_BADFORMAT;
        return Command_Validity.CMD_VALID;
    }
    
    // checks user score
    @Override public void operate(Message msg) {
        Parser p = new StringSeperator();
        String sender = msg.getSender();
        Database_Connection DB = Database_Connection.getInstance();
        BotMediator.sendMessage(msg, Message_Data.stat_begin_message);
        try {
            Query_Handler QH = new Query_Handler();
            DB.connect();
            // 1 is the first parameter which can refer to a user name on the channel or database
            // checking channel is easier so we will start with that
            // if the user in the channel has actually logged in, search our list
            GameUser u = BotMediator.getUser(sender, text[1]);
            String search_name = sender;
            // now this means that the user actually entered a login that's different than the IRC name
            // and also logged in previously in current bot session
            if(u != null)
                search_name = u.getUserName();
            // now query from the db
            if(!QH.getUserScores(DB.getConnection(), search_name))
                BotMediator.sendMessage(msg, Message_Data.stat_nouser);
            else {
                BotMediator.getFeature(Feature_CD.stat_evaluation).execute(msg, QH.getMostRecentResult());
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
