package Message;

/*
**************************
NICK CHANGE MESSAGE FORMAT
**************************

String sourceNick, String sourceLogin, String sourceHostname, String reason

*/

public class Message_Quit extends Message {
    public Message_Quit(String... A) {
        super(A);
        this.type = Message.Message_Type.MSG_QUIT;
    }
    
    @Override public String getChannel() {
        return content.get(3);
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
    
    // alias
    public String getReason() {
        return getText();
    }
    
    public boolean isValid() {
        return content.size() == 4;
    }
}
