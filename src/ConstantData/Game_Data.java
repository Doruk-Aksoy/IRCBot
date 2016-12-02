package ConstantData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game_Data {
    // Game constants
    public static final int TIME_wait_before_gamestart = 5;
    public static final int GAME_max_players = 10;
    
    public static final int jeopardy_rounds = 9;
    public static final int scramble_rounds = 10;
    
    public static final List<String> scramble_words = Collections.unmodifiableList(Arrays.asList("foo", "bar"));
    
    // the commands that are of category "Game"
    public static final List<String> game_commands = Collections.unmodifiableList(Arrays.asList(Message_Data.join_command, Message_Data.answer_command));
}
