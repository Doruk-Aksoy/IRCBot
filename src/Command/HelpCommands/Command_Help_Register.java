package Command.HelpCommands;

import Command.Command;
import ConstantData.Constant_Data_Manager;
import Message.Message;
import ircbot.IRCBot;

public class Command_Help_Register implements Command {
    public Command_Help_Register() { }
    
    @Override public void operate(Message msg) {
        IRCBot Bot = IRCBot.getInstance();
        String toSend = Constant_Data_Manager.help_register;
        Bot.sendMessage(msg.getChannel(), toSend);
    }
}