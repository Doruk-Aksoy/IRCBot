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
    
    public String getChannel() {
        return content.get(1);
    }
    
    public String getSender() {
        return content.get(0);
    }
    
    public String getText() {
        return content.get(3);
    }
    
    public boolean isValid() {
        return content.size() == 4;
    }
}
