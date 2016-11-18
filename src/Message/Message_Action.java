package Message;

import java.util.ArrayList;

/*
*********************
ACTION MESSAGE FORMAT
*********************

String sender, String login, String hostname, String target, String action

*/

public class Message_Action extends Message {
    public Message_Action(String... A) {
        super(A);
        this.type = Message_Type.MSG_ACTION;
    }
    
    public String getChannel() {
        return content.get(3);
    }
    
    public String getSender() {
        return content.get(0);
    }
    
    public String getText() {
        return content.get(4);
    }
    
    public boolean isValid() {
        return content.size() == 5;
    }
}
