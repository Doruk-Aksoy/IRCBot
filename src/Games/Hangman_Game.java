package Games;

import ConstantData.Game_Data;
import UserType.GameUser;

public class Hangman_Game extends ChatGame {
    public Hangman_Game(String c) {
        super(c);
    }
    
    @Override public String getName() {
        return "Hangman";
    }
    
    @Override public int getAnswerCount() {
        return Game_Data.scramble_answer_count;
    }
    
    @Override public boolean checkAnswer(String[] answer) {
        return true;
    }
    
    @Override public void awardUser(GameUser U) {
        
    }
    
    @Override public void initialize() {
        
    }
    
    @Override public void play() {
        
    }
    
    @Override public void finish() {
        
    }
}
