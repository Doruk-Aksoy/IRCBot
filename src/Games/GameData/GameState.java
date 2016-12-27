package Games.GameData;

public class GameState {
    public enum State {
        STAT_COUNTDOWN,
        STAT_ONGOING,
        STAT_WANTANSWER,
        STAT_FINISHED,
        STAT_DATABASE,
        STAT_DONE,
        STAT_CANCEL // if the game was not initialized properly (not enough players for example)
    }
    
    private boolean game_over;
    private State game_state;
    
    public GameState() {
        game_over = false;
        game_state = State.STAT_COUNTDOWN;
    }
    
    public boolean isOver() {
        return game_over == true;
    }
    
    public State getState() {
        return game_state;
    }
    
    public void gameOver() {
        game_over = true;
    }
    
    public void setState(State s) {
        game_state = s;
    }
}
