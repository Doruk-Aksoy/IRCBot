package GUI.Event;

import GUI.GUI_Components;
import java.awt.event.ActionEvent;

import ircbot.IRCBot;
import java.io.IOException;
import org.jibble.pircbot.IrcException;

public class GUI_LaunchButtonHandler {
    // zero or more spaces, then symbols to follow
    private String REGEX = "\\s*(,|\\s)\\s*";
    
    public void handle(ActionEvent e) throws IOException, IrcException {
        String[] res = GUI_Components.channelInputTF.getText().split(REGEX);
        
        
        
        IRCBot Bot = IRCBot.getInstance();
        Bot.makeName(GUI_Components.botnameInputTF.getText());
        Bot.connect("irc.esper.net");
        Bot.joinChannel("#mxu", true);
    }
}
