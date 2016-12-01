package Message;

import java.util.ArrayList;
import java.util.Arrays;

// Message object for handling communication easier
public abstract class Message {
    public enum Message_Type {
        MSG_CHAT,
        MSG_PM,
        MSG_GAME,
        MSG_NICKCHANGE,
        MSG_QUIT
    }
    
    protected Message_Type type;
    protected ArrayList<String> content;
    
    public Message(String... A) {
        this.content = new ArrayList<>();
        this.content.addAll(Arrays.asList(A));
    }
    
    public ArrayList<String> getContent() {
        return content;
    }
 
    public Message_Type getType() {
        return type;
    }
    
    public abstract boolean isValid();
    public abstract String getSender();
    public abstract String getChannel();
    public abstract String getText();
    public abstract String getLogin();
    public abstract String getHostname();
}
