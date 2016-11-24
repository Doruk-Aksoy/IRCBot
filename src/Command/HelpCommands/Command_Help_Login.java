package Command.HelpCommands;

import Command.Command;
import ConstantData.Constant_Data_Manager;
import Message.Message;
import ircbot.IRCBot;

public class Command_Help_Login implements Command {
    @Override public Command_Validity validate(Message msg) {
        if(msg.getText().equals(Constant_Data_Manager.help_command))
            return Command_Validity.CMD_VALID;
        return Command_Validity.CMD_BADFORMAT;
    }
    
    @Override public void operate(Message msg) {
        IRCBot Bot = IRCBot.getInstance();
        String toSend = Constant_Data_Manager.login_help_message;
        Bot.sendMessage(msg.getChannel(), toSend);
    }
}
