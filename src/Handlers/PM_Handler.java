package Handlers;

import Command.Command;
import Command.Command_List;
import ConstantData.Constant_Data_Manager;
import Message.Message;
import ircbot.IRCBot;

public class PM_Handler implements Event_Handler {
    public boolean handle_cmd(Command cmd, Message msg) {
        if(cmd == null)
            return false;
        Command.Command_Validity validity = cmd.validate(msg);
        IRCBot Bot = IRCBot.getInstance();
        if(validity == Command.Command_Validity.CMD_POSSIBLYBAD) {
            Bot.sendMessage(msg, Constant_Data_Manager.possiblywrong_command);
            return false;
        }
        else if(validity == Command.Command_Validity.CMD_BADFORMAT) {
            Bot.sendMessage(msg, Constant_Data_Manager.bad_format);
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
