package GUI.Event;

import ConstantData.GUI_CD;
import java.awt.event.ActionEvent;

import ircbot.IRCBot;
import GUI.GUI_Components;
import GUI.GUI_PopupHandler;
import Parsing.Parser;
import Parsing.StringSeperator;

public class GUI_LaunchButtonHandler implements GUI_EventHandler {
    // zero or more spaces, then symbols to follow
    @Override public void handle(ActionEvent e) {
        Parser P = new StringSeperator();
        String[] channels = P.parse(GUI_Components.channelInputTF.getText(), Parser.spaces_andor_comma);
        String botName = GUI_Components.botnameInputTF.getText();
        String serverName = GUI_Components.serverInputTF.getText();
        
        if(!validate(channels, botName, serverName)) {
            GUI_PopupHandler.handle(GUI_CD.GUI_Popup_Type.Error, new IllegalArgumentException());
            return;
        }
        
        IRCBot Bot = IRCBot.getInstance();
        Bot.makeName(botName);
        try {
            Bot.connect(serverName);
            for(String c : channels) {
                System.out.println(c);
                if(c.startsWith("#"))
                    Bot.joinChannel(c, true);
                else
                    Bot.joinChannel("#" + c, true);
            }
            GUI_PopupHandler.handle(GUI_CD.GUI_Popup_Type.Success);
        } catch (Exception exception) {
            // show a popup
            GUI_PopupHandler.handle(GUI_CD.GUI_Popup_Type.Error, exception);
        }
    }
    
    private boolean validate(String[] channels, String botName, String serverName) {
        return channels.length != 0 && botName.trim().length() != 0 && serverName.trim().length() != 0;
    }
}
