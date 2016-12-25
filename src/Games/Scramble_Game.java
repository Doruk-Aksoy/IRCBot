package Games;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import ConstantData.Game_Data;
import ConstantData.Message_Data;
import GameAward.ScrambleAward;
import Games.GameData.GameState;
import Games.GameData.Scramble_GameInfo;
import Parsing.StringShuffle;
import Mediator.BotMediator;
import Mediator.GameMediator;
import UserType.GameUser;

public class Scramble_Game extends ChatGame {
    public Scramble_Game(String c) {
        super();
        g_info = new Scramble_GameInfo(getInstanceCount(), c);
        awarder = new ScrambleAward();
    }
    
    private void accept_players() {
        // send accept message if ranked
        if(isRanked()) {
            BotMediator.sendMessage(g_info.getSource(), Message_Data.player_join_begin_scramble);
            g_delay.beginDelay();
            BotMediator.sendMessage(g_info.getSource(), "The " + g_info.getName() + " game is ready to begin!");
            g_state.setState(GameState.State.STAT_ONGOING);
        }
        else if(g_delay.onDelay()) {
            g_delay.cancelDelay();
        }
    }
    
    // will show answer after 10 seconds
    private void show_answer() {
        g_delay.makeDelay(Game_Data.scramble_time_per_question, TimeUnit.SECONDS);
        g_delay.beginDelay();
        g_state.setState(GameState.State.STAT_ONGOING);
        BotMediator.sendMessage(g_info.getSource(), "The answer was " + g_info.getWordList().get(0));
        g_info.getWordList().remove(0);
    }
    
    private void countdown() {
        g_state.setState(GameState.State.STAT_ONGOING);
        g_delay.makeDelay(Game_Data.scramble_time_between_rounds, TimeUnit.SECONDS);
        BotMediator.sendMessage(g_info.getSource(), "The next round will begin in " + Game_Data.scramble_time_between_rounds + " seconds!");
        g_delay.beginDelay();
    }
    
    private void buildWords() {
        for(int i = 0; i < Game_Data.scramble_rounds; ++i) {
            String toAdd;
            // add words until we have something that doesn't have this
            do {
                // get a random int using rng local to this thread
                // 0 inclusive, size exclusive
                toAdd = Game_Data.scramble_words.get(ThreadLocalRandom.current().nextInt(0, Game_Data.scramble_words.size()));
            } while(g_info.getWordList().contains(toAdd));
            g_info.getWordList().add(toAdd);
            g_info.getScrambledWordList().add(new StringShuffle().parseSingle(toAdd));
        }
    }
    
    @Override public void initialize() {
        // pick the list of words
        buildWords();
        if(!g_delay.onDelay())
            g_delay.makeDelay(Game_Data.TIME_wait_before_gamestart, TimeUnit.SECONDS);
        accept_players();
    }
    
    @Override public void play() {
        for(String w : g_info.getScrambledWordList()) {
            // initiate round
            g_info.resetAnswers();
            g_info.setFirstCorrectAnswer(false);
            g_state.setState(GameState.State.STAT_WANTANSWER);
            GameMediator.initAnswerCounts(this);
            BotMediator.sendMessage(g_info.getSource(), Message_Data.scramble_question_format + w + ". You have " + Game_Data.scramble_time_per_question + " seconds.");
            show_answer();
            if(!g_info.getWordList().isEmpty())
                countdown();
        }
    }
    
    @Override public void finish() {
        BotMediator.sendMessage(g_info.getSource(), "Game Finished!");
        g_state.setState(GameState.State.STAT_DONE);
        if(isRanked())
            save_stats();
        announce_winner(find_winner());
        GameMediator.terminate(this);
    }
}
