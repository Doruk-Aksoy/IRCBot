package Message;

/*
*********************
GAME MESSAGE FORMAT
*********************

Same as a chat message.

*/

public class Message_Game extends Message_Chat {
    public Message_Game(String... A) {
        super(A);
        this.type = Message_Type.MSG_GAME;
    }
}
