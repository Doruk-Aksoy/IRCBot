package Command.HelpCommands;

import Command.Command;
import ConstantData.Message_Data;
import Mediator.BotMediator;
import Message.Message;

public class Command_Help_Register implements Command {
    @Override public Command_Validity validate(Message msg) {
        if(msg.getText().equals(Message_Data.helpregister_command))
            return Command_Validity.CMD_VALID;
        return Command_Validity.CMD_BADFORMAT;
    }
    
    @Override public void operate(Message msg) {
        String toSend = Message_Data.register_help_message;
        BotMediator.sendMessage(msg.getChannel(), toSend);
    }
}