package Games.GameData;

import java.util.ArrayList;

import ircbot.datamanager.IRCBot_UserManager;

public abstract class GameInfo {
    protected IRCBot_UserManager um;
    protected String source;
    protected long answers_received;
    protected static long game_id;
    protected boolean first_correct_answer;
    
    public GameInfo(long id, String c) {
        um = new IRCBot_UserManager();
        source = c;
        answers_received = 0;
        game_id = id;
        first_correct_answer = false;
    }
    
    public IRCBot_UserManager getUserManager() {
        return um;
    }
    
    public String getSource() {
        return source;
    }
    
    public long getAnswersReceived() {
        return answers_received;
    }
    
    public long getGameID() {
        return game_id;
    }
    
    public boolean getFirstCorrectAnswer() {
        return first_correct_answer;
    }
    
    public void setFirstCorrectAnswer(boolean res) {
        first_correct_answer = res;
    }

    public void addAnswer() {
        answers_received++;
    }
    
    public void resetAnswers() {
        answers_received = 0;
    }
    
    public abstract String getName();
    public abstract int getAnswerCount();
    public abstract boolean checkAnswer(String[] answer);
    
    // conditional methods that are only usable in concrete class definitions (null otherwise)
    public abstract ArrayList<String> getWordList();
    public abstract ArrayList<String> getScrambledWordList();
}
