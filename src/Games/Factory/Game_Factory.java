package Games.Factory;

import Games.*;
import Message.Message;

public class Game_Factory {
    // strong construction
    public ChatGame makeGame(String source, String game_name) {
        ChatGame G;
        if(game_name.equals("hangman"))
            G = new Hangman_Game(source);
        else if(game_name.equals("jeopardy"))
            G = new Jeopardy_Game(source);
        else
            G = new Scramble_Game(source);
        return G;
    }
    
    public ChatGame makeGame(Message msg) {
        String source;
        if(msg.getChannel() == null) // implies this was a pm
            source = msg.getSender();
        else
            source = msg.getChannel();
        return makeGame(source, msg.getText().toLowerCase());
    }
}
