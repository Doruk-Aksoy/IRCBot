package ircbot.datamanager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

import ConstantData.Game_Data;
import Games.ChatGame;

// Handles creation of game threads and responsibility of finishing them when done
// Create a HashMap of pair of String, ChatGame vs GameTask. This GameTask is usually a GameLoop task.
public class IRCBot_GameManager {
    // this is the service that we will use for handling game processes, which will contain subtasks in them
    private final ExecutorService service;
    private final ConcurrentHashMap<ChatGame, Future<String>> active_games;

    public IRCBot_GameManager() {
        service = Executors.newFixedThreadPool(Game_Data.MAX_GAME_TYPES * 12);
        active_games = new ConcurrentHashMap<>();
    }
    
    public void addGame(ChatGame G) {
        Future<String> F = service.submit(new Callable<String>() { public String call() { return G.run(); } } );
        active_games.put(G, F);
    }
}
