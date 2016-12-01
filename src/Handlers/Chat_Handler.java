package Handlers;

import Command.*;
import ConstantData.Message_Data;
import Message.Message;
import ircbot.IRCBot;

public class Chat_Handler implements Event_Handler {    
    @Override public boolean handle_cmd(Command cmd, Message msg) {
        if(cmd == null)
            return false;
        Command.Command_Validity validity = cmd.validate(msg);
        IRCBot Bot = IRCBot.getInstance();
        if(validity == Command.Command_Validity.CMD_POSSIBLYBAD) {
            Bot.sendMessage(msg, Message_Data.possiblywrong_command);
            return false;
        }
        else if(validity == Command.Command_Validity.CMD_BADFORMAT) {
            Bot.sendMessage(msg, Message_Data.bad_format);
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
