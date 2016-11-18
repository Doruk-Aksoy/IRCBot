package ircbot;

import org.jibble.pircbot.*;

import java.util.ArrayList;
import java.util.HashMap;

import Handlers.Event_Handler;
import Handlers.Event_Handler_Builder;
import Message.*;

public class IRCBot extends PircBot {
    
    // members
    private static IRCBot instance = null;
    private ArrayList<String> channels;
    private IRCBot_UserManager user_manager;
    // messages get mapped to events depending on type
    private HashMap<Message.Message_Type, Event_Handler> event_handlers;
    
    private IRCBot() {
        this.channels = new ArrayList<>();
        this.user_manager = new IRCBot_UserManager();
        Event_Handler_Builder EB = new Event_Handler_Builder();
        this.event_handlers = EB.build();
    }
    
    public synchronized static IRCBot getInstance() {
        if(instance == null)
            instance = new IRCBot();
        return instance;
    }
    
    public void makeName(String S) {
        this.setName(S);
    }
    
    public IRCBot(String s) {
        this.setName(s);
    }
    
    public IRCBot_UserManager getUserManager() {
        return user_manager;
    }
    
    public void addChannel(String s) {
        channels.add(s);
    }
    
    public void deleteChannel(int i) {
        try {
            channels.remove(i);
        } catch (IndexOutOfBoundsException I) {
            System.err.println("IndexOutOfBoundsException: " + I.getMessage());
        }
    }
    
    // exception free version
    public void deleteChannel(String s) {
        channels.remove(s);
    }
    
    public String getChannel(int i) {
        try {
            return channels.get(i);
        } catch(IndexOutOfBoundsException I) {
            System.err.println("IndexOutOfBoundsException: " + I.getMessage());
            return null;
        }
    }
    
    // because message formats are different, we have to have an overloaded version here
    public void sendMessage(Message msg, String toSend) {
        if(msg.getType() == Message.Message_Type.MSG_CHAT)
            sendMessage(msg.getChannel(), toSend);
        else if(msg.getType() == Message.Message_Type.MSG_PM)
            sendMessage(msg.getSender(), toSend);
    }
    
    // This comes with the framework, these are "event functions" that actually trigger when an event happens
    @Override public void onMessage(String channel, String sender, String login, String hostname, String message) {
        Message msg = new Message_Chat(channel, sender, login, hostname, message);
        if(msg.isValid())
            event_handlers.get(msg.getType()).handle_event(msg);
    }
    
    @Override public void onPrivateMessage(String sender, String login, String hostname, String message) {
        Message msg = new Message_PM(sender, login, hostname, message);
        if(msg.isValid())
            event_handlers.get(msg.getType()).handle_event(msg);
    }
}
