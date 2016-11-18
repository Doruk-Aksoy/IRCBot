package Command;

import ConstantData.Constant_Data_Manager;
import Message.Message;
import ircbot.IRCBot;

public class Command_Record implements Command {
    public Command_Record() { }
    
    // checks user score
    @Override public void operate(Message msg) {
        IRCBot Bot = IRCBot.getInstance();
        String text = msg.getText(); // make some sort of parser to extract the part after the command (whitespace)
        String toSend = Constant_Data_Manager.no_score_message;
        Bot.sendMessage(msg.getChannel(), toSend);
    }
}
