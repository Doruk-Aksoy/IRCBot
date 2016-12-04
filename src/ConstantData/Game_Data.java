package ConstantData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game_Data {
    // Game constants
    public static final int TIME_wait_before_gamestart = 10;
    public static final int GAME_max_players = 10;
    public static final int MAX_GAME_TYPES = 3;
    public static final int MAX_THREADS = 12;
    
    // Jeopardy Data
    public static final int jeopardy_rounds = 9;
    
    // Scramble Data
    public static final int scramble_rounds = 10;
    public static final int scramble_time_per_question = 10;
    
    public static final List<String> game_names = Collections.unmodifiableList(Arrays.asList("scramble", "hangman", "jeopardy"));
    
    public static final List<String> scramble_words = buildScramble();
    
    // the commands that are of category "Game"
    public static final List<String> game_commands = Collections.unmodifiableList(Arrays.asList(Message_Data.join_command, Message_Data.answer_command));

    private static List<String> buildScramble() {
        return Collections.unmodifiableList(Arrays.asList(
                    "involuntary", "regurgitation", "redemption", "marvelous", "electronical",
                    "ridiculous", "masterful", "situation", "autodrome", "revelation",
                    "biological", "shiver", "ravishing", "rebellious", "vindictive"
               ));
    }
}
