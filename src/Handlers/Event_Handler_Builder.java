package Handlers;

import java.util.HashMap;

import Message.*;

public class Event_Handler_Builder {
    public HashMap<Message.Message_Type, Event_Handler> build() {
        HashMap<Message.Message_Type, Event_Handler> hnd = new HashMap<>();
        Event_Handler eh_chat = new Chat_Handler();
        Event_Handler eh_pm = new PM_Handler();
        Event_Handler eh_nick = new NickChange_Handler();
        Event_Handler eh_quit = new Quit_Handler();
        Event_Handler eh_game = new Game_Handler();
        hnd.put(Message.Message_Type.MSG_CHAT, eh_chat); // to handle chat messages, this handler is built
        hnd.put(Message.Message_Type.MSG_PM, eh_pm); // to handle private messages
        hnd.put(Message.Message_Type.MSG_NICKCHANGE, eh_nick); // to handle nick changes
        hnd.put(Message.Message_Type.MSG_QUIT, eh_quit); // to log off users when they quit
        hnd.put(Message.Message_Type.MSG_GAME, eh_game); // Game related message handler
        hnd.put(Message.Message_Type.MSG_GAMEPM, eh_game);
        
        return hnd;
    }
}
