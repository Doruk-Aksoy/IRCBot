package Handlers;

import Command.*;
import ConstantData.Message_Data;
import Mediator.BotMediator;
import Message.Message;

public class Chat_Handler implements Event_Handler {    
    @Override public boolean handle_cmd(Command cmd, Message msg) {
        return new CommandValidation_Handler().handle_cmd(cmd, msg);
    }
    
    @Override public boolean handle_event(Message msg) {
        Command_List CL = Command_List.getInstance();
        return handle_cmd(CL.getCommand(msg.getText()), msg);
    }
}
