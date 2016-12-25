package Games;

import Countable.Countable;
import Database.Database_Connection;
import Database.Query_Handler;
import GameAward.GameAward;
import Games.GameData.GameInfo;
import Games.GameData.GameDelay;
import Games.GameData.GameState;
import Mediator.BotMediator;
import UserType.GameUser;

// includes an object of this type, because they need to manage their own users actively playing in this
import ircbot.datamanager.IRCBot_UserManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ChatGame extends Countable {
    protected GameInfo g_info;
    protected GameAward awarder;
    // only these two are initialized here
    protected GameDelay g_delay;
    protected GameState g_state;
    
    public ChatGame() {
        g_state = new GameState();
        g_delay = new GameDelay();
    }
    
    public GameInfo getGameInfo() {
        return g_info;
    }
    
    public GameState getGameState() {
        return g_state;
    }
    
    public GameDelay getGameDelay() {
        return g_delay;
    }
    
    // checks if this game is an unranked game (sent via PM) or ranked (sent in a channel)
    public boolean isRanked() {
        String source = g_info.getSource();
        return source != null && source.charAt(0) == '#';
    }
    
    public IRCBot_UserManager getUserManager() {
        return g_info.getUserManager();
    }
    
    public boolean abortCurrentTask() {
        return g_delay.cancelDelay();
    }
    
    public void awardUser(GameUser U) {
        awarder.award(U, this);
    }
    
    public boolean allAnswersReceived() {
        return g_info.getAnswersReceived() == g_info.getUserManager().getUserCount();
    }
    
    // Use the template pattern to fill these methods later, but provide a definite skeleton for the games to follow
    public abstract void initialize();
    public abstract void play();
    public abstract void finish();
    
    public String run() {
        try {
            initialize();
            play();
            finish();
        }
        catch(Exception e) {
            System.out.println(e + " happened!!!!");
            return g_info.getName() + " Failed";
        }
        return "Success";
    }
    
    public GameUser find_winner() {
        return g_info.getUserManager().getHighestScoreUser();
    }
    
    public void announce_winner(GameUser u) {
        u.setWinner(true);
        BotMediator.sendMessage(g_info.getSource(), u.getIRCName() + " has won the " + g_info.getName() + " game with " + u.getScore() + " points! Congratulations!");
        g_state.setState(GameState.State.STAT_FINISHED);
    }
    
    public void save_stats() {
        g_state.setState(GameState.State.STAT_DATABASE);
        ArrayList<GameUser> users = g_info.getUserManager().getUserList();
        Database_Connection DB = Database_Connection.getInstance();
        
        try {
            DB.connect();
        } catch(SQLException e) {
            System.out.println(e + " happened!");
            return;
        }
        
        Query_Handler QH = new Query_Handler();
        for(GameUser u : users) {
            try {
                QH.updateStats(DB.getConnection(), u.getId(), u.getScore(), u.hasWon());
            } catch(SQLException e) {
                System.out.println(e + " happened!");
                return;
            }
        }
 
        try {
            DB.disconnect();
        } catch(SQLException e) {
            System.out.println(e + " happened!");
        }
    }
    
    public void disassociate_users() {
        ArrayList<GameUser> users = g_info.getUserManager().getUserList();
        for(GameUser u : users)
            u.setGameStatus(-1);
    }
}
