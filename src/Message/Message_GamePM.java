package Message;

public class Message_GamePM extends Message_PM {
    public Message_GamePM(String... A) {
        super(A);
        this.type = Message_Type.MSG_GAMEPM;
    }
}
