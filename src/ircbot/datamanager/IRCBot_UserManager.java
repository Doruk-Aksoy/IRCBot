package ircbot.datamanager;

import java.util.ArrayList;
import UserType.*;

public class IRCBot_UserManager {
    private ArrayList<GameUser> users;
    
    public IRCBot_UserManager() { 
        users = new ArrayList();
    }
    
    public void addUser(GameUser u) {
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
    public void deleteUser(GameUser u) {
        users.remove(u);
    }
    
    public GameUser getUser(int i) {
        try {
            return users.get(i);
        } catch(IndexOutOfBoundsException I) {
            System.err.println("IndexOutOfBoundsException: " + I.getMessage());
            return null;
        }
    }
    
    public GameUser getUser(String name) {
        for(GameUser u : users) {
            if(u.getIRCName().equals(name))
                return u;
        }
        return null;
    }
    
    public GameUser getUser(String IRCName, String username) {
        for(GameUser u : users) {
            if(u.getIRCName().equals(IRCName) || u.getUserName().equals(username))
                return u;
        }
        return null;
    }
    
    public int getUserCount() {
        return users.size();
    }
}
