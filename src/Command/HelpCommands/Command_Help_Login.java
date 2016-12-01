package Command.HelpCommands;

import Command.Command;
import ConstantData.Message_Data;
import Message.Message;
import ircbot.IRCBot;

public class Command_Help_Login implements Command {
    @Override public Command_Validity validate(Message msg) {
        if(msg.getText().equals(Message_Data.help_command))
            return Command_Validity.CMD_VALID;
        return Command_Validity.CMD_BADFORMAT;
    }
    
    @Override public void operate(Message msg) {
        IRCBot Bot = IRCBot.getInstance();
        String toSend = Message_Data.login_help_message;
        Bot.sendMessage(msg.getChannel(), toSend);
    }
}