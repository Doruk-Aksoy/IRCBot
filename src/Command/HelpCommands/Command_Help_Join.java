package Command.HelpCommands;

import Command.Command;
import ConstantData.Constant_Data_Manager;
import Message.Message;
import ircbot.IRCBot;

public class Command_Help_Join implements Command {
    public Command_Help_Join() { }
    
    @Override public void operate(Message msg) {
        IRCBot Bot = IRCBot.getInstance();
        String toSend = Constant_Data_Manager.join_help_message;
        Bot.sendMessage(msg.getChannel(), toSend);
    }
}
