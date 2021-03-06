package Command;

import Message.Message;
import Parsing.Parser;
import Parsing.StringSeperator;
import ConstantData.Feature_CD;

public class Command_Keyword implements Command {
    private String[] text; // has a string array associated with parsing to avoid splitting twice
    
    @Override public Command_Validity validate(Message msg) {
        if(msg.getType() != Message.Message_Type.MSG_PM)
            return Command_Validity.CMD_POSSIBLYBAD;
        Parser p = new StringSeperator();
        text = p.parse(msg.getText(), Parser.spaces);
        if(!verifyFormat(text))
            return Command_Validity.CMD_BADFORMAT;
        return Command_Validity.CMD_VALID;
    }
    
    private boolean verifyFormat(String[] s) {
        return s != null && s.length >= 3 && s.length <= Feature_CD.keyword_maxcount;
    }
    
    @Override public void operate(Message msg) {
        
    }
}
