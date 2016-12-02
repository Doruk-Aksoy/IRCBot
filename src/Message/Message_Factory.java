package Message;

import Parsing.*;
import Command.Command;
import Command.Command_List;
import ConstantData.Game_Data;

public class Message_Factory {
    
    private boolean isGameMessage(String s) {
        return Game_Data.game_commands.contains(s);
    }
    
    private Message make_default(Message.Message_Type default_type, String... A) {
        // only two default types are allowed
        if(default_type == Message.Message_Type.MSG_PM)
            return new Message_PM(A);
        return new Message_Chat(A);
    }
    
    public Message build(Message.Message_Type default_type, String... A) {
        String message = A[A.length - 1]; // guaranteed to be the last string
        Parser P = new StringSeperator();
        String begin = P.parse(message)[0];
        Command_List CL = Command_List.getInstance();
        if(CL.getCommand(begin) != null) {
            if(isGameMessage(begin))
                return new Message_Game(A);
        }
        return make_default(default_type, A);
    }
}
