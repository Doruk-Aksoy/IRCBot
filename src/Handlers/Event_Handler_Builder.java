package Handlers;

import java.util.HashMap;

import Message.*;

public class Event_Handler_Builder {
    public HashMap<Message.Message_Type, Event_Handler> build() {
        HashMap<Message.Message_Type, Event_Handler> hnd = new HashMap<>();
        Event_Handler eh_chat = new Chat_Handler();
        Event_Handler eh_pm = new PM_Handler();
        Event_Handler eh_nick = new NickChange_Handler();
        hnd.put(Message.Message_Type.MSG_CHAT, eh_chat); // to handle chat messages, this handler is built
        hnd.put(Message.Message_Type.MSG_PM, eh_pm); // to handle private messages
        hnd.put(Message.Message_Type.MSG_NICKCHANGE, eh_nick); // to handle nick changes
        
        return hnd;
    }
}
