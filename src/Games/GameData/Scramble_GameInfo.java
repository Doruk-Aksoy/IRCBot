package Games.GameData;

import ConstantData.Game_Data;
import java.util.ArrayList;

public class Scramble_GameInfo extends GameInfo {
    private ArrayList<String> words;
    private ArrayList<String> scrambled_words;
    
    public Scramble_GameInfo(long id, String c) {
        super(id, c);
        words = new ArrayList<>();
        scrambled_words = new ArrayList<>();
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
    
    @Override public ArrayList<String> getWordList() {
        return words;
    }
    
    @Override public ArrayList<String> getScrambledWordList() {
        return scrambled_words;
    }
}
