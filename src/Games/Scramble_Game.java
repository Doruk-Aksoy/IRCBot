package Games;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import ConstantData.Game_Data;
import ConstantData.Message_Data;
import GameTask.GameTask_AcceptPlayers;
import Parsing.StringShuffle;
import Mediator.BotMediator;
import Mediator.GameMediator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Scramble_Game extends ChatGame {
    private ArrayList<String> words;
    private ArrayList<String> scrambled_words;
    
    public Scramble_Game(String c) {
        super(c);
        words = new ArrayList<>();
        scrambled_words = new ArrayList<>();
    }
    
    public Scramble_Game(String c, boolean weak) {
        super(c, weak);
    }
    
    @Override public void makeWhole() {
        super.makeWhole();
        words = new ArrayList<>();
        scrambled_words = new ArrayList<>();
    }
    
    @Override public String getName() {
        return "Scramble";
    }
    
    private void accept_players() {
        // send accept message if ranked
        if(isRanked()) {
            BotMediator.sendMessage(source, Message_Data.player_join_begin_scramble);
            try {
                synchronized(pending_future) {
                    pending_future.get();
                }
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex + " happened!");
            }
        }
        else if(pending_future != null) {
            pending_future.cancel(true);
        }
    }
    
    private void buildWords() {
        for(int i = 0; i < Game_Data.scramble_rounds; ++i) {
            String toAdd;
            // add words until we have something that doesn't have this
            do {
                // get a random int using rng local to this thread
                // 0 inclusive, size exclusive
                toAdd = Game_Data.scramble_words.get(ThreadLocalRandom.current().nextInt(0, Game_Data.scramble_words.size()));
            } while(words.contains(toAdd));
            words.add(toAdd);
            scrambled_words.add(new StringShuffle().parseSingle(toAdd));
        }
    }
    
    @Override public void initialize() {
        // pick the list of words
        buildWords();
        // create timer here
        if(pending_future == null) {
            pending_task = new GameTask_AcceptPlayers(source, this);
            pending_future = GameMediator.scheduleTask(pending_task, Game_Data.TIME_wait_before_gamestart, TimeUnit.SECONDS);
        }
        accept_players();
    }
    
    @Override public void play() {
        int word_index = 0;
        BotMediator.sendMessage(source, Message_Data.scramble_question_format + scrambled_words.get(word_index) + ". You have " + Game_Data.scramble_time_per_question + " seconds.");
    }
    
    @Override public void finish() {
        
    }
}
