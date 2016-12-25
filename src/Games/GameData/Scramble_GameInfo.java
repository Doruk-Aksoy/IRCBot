package Games.GameData;

import ConstantData.Game_Data;
import Parsing.StringShuffle;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Scramble_GameInfo extends GameInfo {
    private ArrayList<String> words;
    private ArrayList<String> scrambled_words;
    
    public Scramble_GameInfo(long id, String c) {
        super(id, c);
        total_rounds = Game_Data.scramble_rounds;
        buildWords();
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
    
    private void buildWords() {
        words = new ArrayList<>();
        scrambled_words = new ArrayList<>();
        for(int i = 0; i < total_rounds; ++i) {
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
}
