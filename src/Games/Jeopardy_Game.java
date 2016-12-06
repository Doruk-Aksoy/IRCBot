package Games;

import ConstantData.Game_Data;
import UserType.GameUser;

public class Jeopardy_Game extends ChatGame {
    public Jeopardy_Game(String c) {
        super(c);
    }

    @Override public String getName() {
        return "Jeopardy";
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
