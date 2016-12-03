package ircbot.datamanager;

import java.util.ArrayList;

public class IRCBot_ChannelManager {
    private ArrayList<String> channels;
    
    public IRCBot_ChannelManager() {
        channels = new ArrayList<>();
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
    
    public String getChannel(String c) {
        for(String s : channels)
            if(s.equals(c))
                return s;
        return null;
    }
    
    public int getChannelCount() {
        return channels.size();
    }
}
