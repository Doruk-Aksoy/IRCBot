package Command;

import ConstantData.Message_Data;
import Message.Message;
import Parsing.*;
import Mediator.BotMediator;

// this represents a type of command that the user probably typo'd or otherwise messed up
public class Command_Invalid implements Command {
    @Override public Command_Validity validate(Message msg) {
        return Command_Validity.CMD_VALID;
    }
    
    @Override public void operate(Message msg) {
        Parser p = new StringSeperator();
        String[] text = p.parse(msg.getText());
        String toSend = Message_Data.invalid_command;
        BotMediator.sendMessage(msg, toSend);
    }
}
