package Handlers;

import Command.Command;
import Command.Command_List;
import Message.Message;

public class PM_Handler implements Event_Handler {
    public PM_Handler() { }
    
    @Override public boolean handle_event(Message msg) {
        Command_List CL = Command_List.getInstance();
        String text = msg.getText();
        Command cmd = CL.getCommand(text);
        if(cmd != null) {
            cmd.operate(msg);
            return true;
        }
        return false;
    }
}
