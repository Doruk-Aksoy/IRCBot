package GUI.Event;

import ircbot.IRCBot;
import java.awt.event.ActionEvent;

class GUI_DisconnectButtonHandler implements GUI_EventHandler {
    @Override public void handle(ActionEvent e) {
        IRCBot Bot = IRCBot.getInstance();
        if(Bot.isConnected())
            Bot.disconnect();
    }
}
