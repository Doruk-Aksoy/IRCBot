package Message;

/*
**************************
NICK CHANGE MESSAGE FORMAT
**************************

String oldNick, String login, String hostname, String newNick

*/

public class Message_NickChange extends Message {
   public Message_NickChange(String... A) {
        super(A);
        this.type = Message.Message_Type.MSG_NICKCHANGE;
    }
    
    @Override public String getChannel() {
        return content.get(0);
    }
    
    @Override public String getSender() {
        return content.get(3);
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
    
    // a simple alias to make it more obvious
    public String getOldName() {
        return getChannel();
    }
    
    // same as above
    public String getNewName() {
        return getText();
    }
}
