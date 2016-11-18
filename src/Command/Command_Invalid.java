package Command;

import ConstantData.Constant_Data_Manager;
import Message.Message;
import Parsing.*;
import ircbot.IRCBot;

// this is a type of command that the user probably typo'd or otherwise messed up
public class Command_Invalid implements Command {
    public Command_Invalid() { }
    
    @Override public void operate(Message msg) {
        Parser p = new StringSeperator();
        String[] text = p.parse(msg.getText());
        IRCBot Bot = IRCBot.getInstance();
        String toSend = Constant_Data_Manager.invalid_command;
        Bot.sendMessage(msg, toSend);
    }
}
