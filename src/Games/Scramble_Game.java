package Games;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import ConstantData.Game_Data;
import ConstantData.Message_Data;
import Parsing.StringShuffle;
import Mediator.BotMediator;

public class Scramble_Game extends ChatGame {
    private ArrayList<String> words;
    private ArrayList<String> scrambled_words;
    
    public Scramble_Game(String c) {
        super(c);
        words = new ArrayList<>();
        scrambled_words = new ArrayList<>();
    }
    
    private void accept_players() {
        // send accept message if ranked
        if(isRanked()) {
            BotMediator.sendMessage(source, Message_Data.player_join_begin_scramble);
            // create timer, wait, and after that finish (while waiting add players)
        }
        else {
            // simply start the game with 1 player, unranked
            
        }
    }
    
    @Override public void initialize() {
        // pick the list of words
        for(int i = 0; i < Game_Data.scramble_rounds; ++i) {
            String toAdd;
            // add words until we have something that doesn't have this
            do {
                // get a random int using rng local to this thread
                // 0 inclusive, size exclusive
                toAdd = Game_Data.scramble_words.get(ThreadLocalRandom.current().nextInt(0, Game_Data.scramble_words.size()));
            } while(!words.contains(toAdd));
            words.add(toAdd);
            scrambled_words.add(new StringShuffle().parseSingle(toAdd));
        }
        // create timer here
        accept_players();
    }
    
    @Override public void play() {
        
    }
    
    @Override public void finish() {
        
    }
}
