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
    
    public ChatGame gameExists(ChatGame G) {
        for (HashMap.Entry<ChatGame, Future<String>> elem : active_games.entrySet()) {
            if(elem.getKey().equals(G)) {
                return elem.getKey();
            }
        }
        System.out.println("Game not found! Creating...");
        return null;
    }
    
    // add a fixedDelay task here that sweeps over all games, deletes if they are in STAT_DONE state
}
