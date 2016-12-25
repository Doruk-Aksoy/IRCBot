package ircbot.datamanager;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ConstantData.Game_Data;
import Games.ChatGame;

// Handles creation of game threads and responsibility of finishing them when done
// Create a HashMap of pair of String, ChatGame vs GameTask. This GameTask is usually a GameLoop task.
public class IRCBot_GameManager {
    // this is the service that we will use for handling game processes, which will contain subtasks in them
    private final ExecutorService service;
    private final HashMap<ChatGame, Future<String>> active_games;

    public IRCBot_GameManager() {
        service = Executors.newFixedThreadPool(Game_Data.MAX_GAME_TYPES * Game_Data.MAX_THREADS);
        active_games = new HashMap<>();
    }
    
    public void addGame(ChatGame G) {
        // uses lambda expression
        Future<String> F = service.submit( () -> G.run() );
        active_games.put(G, F);
    }
    
    public ChatGame gameExists(String sID) {
        // put this into a parser
        int game_id = 0;
        try {
            game_id = Integer.parseInt(sID);
        } 
        catch (NumberFormatException e) {
            return null;
        }
        for (HashMap.Entry<ChatGame, Future<String>> elem : active_games.entrySet()) {
            if(elem.getKey().getGameInfo().getGameID() == game_id) {
                return elem.getKey();
            }
        }
        System.out.println("Game not found!");
        return null;
    }
    
    public HashMap.Entry<ChatGame, Future<String>> getGameFuturePair(String sID) {
        // put this into a parser
        int game_id = 0;
        try {
            game_id = Integer.parseInt(sID);
        } 
        catch (NumberFormatException e) {
            return null;
        }
        for (HashMap.Entry<ChatGame, Future<String>> elem : active_games.entrySet()) {
            if(elem.getKey().getGameInfo().getGameID() == game_id) {
                return elem;
            }
        }
        return null;
    }
    
    // when games are finished, they will signal they are done to gamemediator and it will delete them from this array
    public void terminate(ChatGame G) {
        Future<String> F = null;
        // find the future associated with this game
        for (HashMap.Entry<ChatGame, Future<String>> elem : active_games.entrySet()) {
            if(elem.getKey().getGameInfo().getGameID() == G.getGameInfo().getGameID()) {
                F = elem.getValue();
            }
        }
        if(F != null)
            F.cancel(true);
        active_games.remove(G);
    }
}
