package ircbot;

import java.util.ArrayList;
import org.jibble.pircbot.User;

public class IRCBot_UserManager {
    private ArrayList<User> users;
    
    public IRCBot_UserManager() { 
        users = null;
    }
    
    public void addUser(User u) {
        users.add(u);
    }
    
    public void deleteUser(int i) {
        try {
            users.remove(i);
        } catch (IndexOutOfBoundsException I) {
            System.err.println("IndexOutOfBoundsException: " + I.getMessage());
        }
    }
    
    // exception free version
    public void deleteUser(User u) {
        users.remove(u);
    }
    
    public User getUser(int i) {
        try {
            return users.get(i);
        } catch(IndexOutOfBoundsException I) {
            System.err.println("IndexOutOfBoundsException: " + I.getMessage());
            return null;
        }
    }
}
