package Command;

import ConstantData.Game_Data;
import Message.Message;
import Parsing.Parser;
import Parsing.StringSeperator;

public class Command_Join implements Command {
    private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    public Command.Command_Validity validate(Message msg) {
        if(msg.getType() != Message.Message_Type.MSG_PM)
            return Command.Command_Validity.CMD_POSSIBLYBAD;
        Parser p = new StringSeperator();
        text = p.parse(msg.getText());
        if(!verifyFormat(text))
            return Command.Command_Validity.CMD_BADFORMAT;
        return Command.Command_Validity.CMD_VALID;
    }
    
    private boolean isGameName(String s) {
        for(String comp : Game_Data.game_names) {
            if(comp.equals(s.toLowerCase()))
                return true;
        }
        return false;
    }
    
    private boolean verifyFormat(String[] s) {
        return s != null && s.length == 2 && isGameName(s[1]);
    }
    
    @Override public void operate(Message msg) {
        
    }
}
