package Games;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import ConstantData.Game_Data;
import ConstantData.Message_Data;
import GameTask.*;
import Parsing.StringShuffle;
import Mediator.BotMediator;
import Mediator.GameMediator;
import UserType.GameUser;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Scramble_Game extends ChatGame {
    private ArrayList<String> words;
    private ArrayList<String> scrambled_words;
    private boolean first_correct_answer;
    
    public Scramble_Game(String c) {
        super(c);
        words = new ArrayList<>();
        scrambled_words = new ArrayList<>();
        first_correct_answer = false;
    }
    
    @Override public String getName() {
        return "Scramble";
    }
    
    @Override public int getAnswerCount() {
        return Game_Data.scramble_answer_count;
    }
    
    @Override public boolean checkAnswer(String[] answer) {
        return answer.length == 1 && answer[0].equals(words.get(0));
    }
    
    @Override public void awardUser(GameUser U) {
        long pts = Game_Data.scramble_point;
        if(!first_correct_answer) {
            U.addScore(pts);
            first_correct_answer = true;
            BotMediator.sendMessage(source, U.getIRCName() + " is the first player to answer correctly and claims " + pts + " points!");
            BotMediator.sendMessage(U.getIRCName(), "You have answered correctly and received " + pts + " points!");
        }
        else {
            long delay = pending_future.getDelay(TimeUnit.SECONDS);
            pts *= delay;
            pts /= (Game_Data.scramble_time_per_question);
            U.addScore(pts);
            BotMediator.sendMessage(source, U.getIRCName() + " has answered correctly after " + Game_Data.scramble_time_per_question + " seconds and receives " + pts + " points!");
            BotMediator.sendMessage(U.getIRCName(), "You have answered correctly after " + Game_Data.scramble_time_per_question + " seconds and received " + pts + " points!");
        }
        U.setAnswerCount(Game_Data.CORRECTLY_ANSWERED);
        answers_received++;
        if(answers_received == um.getUserCount())
            abortCurrentTask();
        
    }
    
    private void accept_players() {
        // send accept message if ranked
        if(isRanked()) {
            BotMediator.sendMessage(source, Message_Data.player_join_begin_scramble);
            try {
                synchronized(pending_future) {
                    pending_future.get();
                }
            } catch (Exception ex) {
                System.out.println(ex + " happened!");
            }
        }
        else if(pending_future != null) {
            pending_future.cancel(true);
        }
    }
    
    // will show answer after 10 seconds
    private void show_answer() {
        pending_task = new GameTask_ShowAnswer(source, this);
        pending_future = GameMediator.scheduleTask(pending_task, Game_Data.scramble_time_per_question, TimeUnit.SECONDS);
        try {
            synchronized(pending_future) {
                pending_future.get();
            }
        } catch (Exception ex) {
            System.out.println(ex + " happened!");
        }
        BotMediator.sendMessage(source, "The answer was " + words.get(0));
        words.remove(0);
    }
    
    private void countdown() {
        timer = Game_Data.scramble_time_between_rounds;
        game_state = ChatGame.State.STAT_ONGOING;
        pending_task = new GameTask_Dummy(source, this);
        pending_future = GameMediator.scheduleTask(pending_task, timer, TimeUnit.SECONDS);
        BotMediator.sendMessage(source, "The next round will begin in " + timer + " seconds!");
        try {
            synchronized(pending_future) {
                pending_future.get();
            }
        } catch (Exception ex) {
            System.out.println(ex + " happened!");
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
        for(String w : scrambled_words) {
            // initiate round
            answers_received = 0;
            first_correct_answer = false;
            game_state = State.STAT_WANTANSWER;
            GameMediator.initAnswerCounts(this);
            BotMediator.sendMessage(source, Message_Data.scramble_question_format + w + ". You have " + Game_Data.scramble_time_per_question + " seconds.");
            show_answer();
            if(!words.isEmpty())
                countdown();
        }
    }
    
    @Override public void finish() {
        BotMediator.sendMessage(source, "Game Finished!");
        game_state = State.STAT_DONE;
    }
}
