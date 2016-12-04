package Message;

/*

**********************
PRIVATE MESSAGE FORMAT
**********************

String sender, String login, String hostname, String message

*/

public class Message_PM extends Message {
    public Message_PM(String... A) {
        super(A);
        this.type = Message_Type.MSG_PM;
    }
    
    // PMs don't have channels
    @Override public String getChannel() {
        return null;
    }
    
    @Override public String getSender() {
        return content.get(0);
    }
    
    @Override public String getText() {
        return content.get(3);
    }
    
    @Override public String getLogin() {
        return content.get(1);
    }
    
    @Override public String getHostname() {
        return content.get(2);
    }
    
    @Override public boolean isValid() {
        return content.size() == 4;
    }
}
