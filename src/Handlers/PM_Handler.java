package Handlers;

import Command.Command;
import Command.Command_List;
import ConstantData.Message_Data;
import Mediator.BotMediator;
import Message.Message;

public class PM_Handler implements Event_Handler {
    @Override public boolean handle_cmd(Command cmd, Message msg) {
        if(cmd == null)
            return false;
        Command.Command_Validity validity = cmd.validate(msg);
        if(validity == Command.Command_Validity.CMD_POSSIBLYBAD) {
            BotMediator.sendMessage(msg, Message_Data.possiblywrong_command);
            return false;
        }
        else if(validity == Command.Command_Validity.CMD_BADFORMAT) {
            BotMediator.sendMessage(msg, Message_Data.bad_format);
            return false;
        }
        cmd.operate(msg);
        return true;
    }
    
    @Override public boolean handle_event(Message msg) {
        Command_List CL = Command_List.getInstance();
        String text = msg.getText();
        return handle_cmd(CL.getCommand(text), msg);
    }
}
