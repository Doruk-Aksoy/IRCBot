package Mediator;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.HashMap;

import ConstantData.Game_Data;
import Games.ChatGame;
import Games.Factory.Game_Factory;
import Message.Message;
import TaskScheduler.SchedulerInterface;
import TaskScheduler.TaskScheduler;
import UserType.GameUser;
import ircbot.IRCBot;
import ircbot.datamanager.IRCBot_UserManager;

public class GameMediator {
    public static ChatGame gameExists(String sID) {
        IRCBot Bot = IRCBot.getInstance();
        return Bot.getDataManager().getGameManager().gameExists(sID);
    }
    
    public static ChatGame makeGame(Message msg) {
        Game_Factory F = new Game_Factory();
        return F.makeGame(msg);
    }
    
    public static void addGame(ChatGame G) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.getDataManager().getGameManager().addGame(G);
    }
    
    public static void addUser(ChatGame G, GameUser U) {
        IRCBot_UserManager UM = G.getUserManager();
        if(UM.getUser(U.getIRCName()) == null)
            UM.addUser(U);
    }
    
    public static boolean isFull(ChatGame G) {
        return G.getUserManager().getUserCount() == Game_Data.GAME_max_players;
    }
    
    public static boolean userInGame(ChatGame G, GameUser U) {
        IRCBot_UserManager UM = G.getUserManager();
        return UM.getUser(U.getIRCName()) != null;
    }
    
    public static ScheduledFuture scheduleTask(Callable T, long delay, TimeUnit t) {
        SchedulerInterface S = new TaskScheduler();
        return S.scheduleWithDelay(T, delay, t);
    }
    
    public static int getUserCount(ChatGame G) {
        return G.getUserManager().getUserCount();
    }
    
    // we handle it here because otherwise the thread might be waiting on a task and the answers would come up late
    public static void acceptAnswer(ChatGame G, GameUser U, String[] answer) {
        if(G.checkAnswer(answer) == true)
            G.awardUser(U);
        else {
            U.deduceAnswerCount();
            // do attempt for 1.
            BotMediator.sendMessage(G.getChannel(), "Wrong answer! You have " + U.getAnswerCount() + " attempts left!");
        }
    }
    
    public static void initAnswerCounts(ChatGame G) {
        IRCBot_UserManager UM = G.getUserManager();
        for(int i = 0; i < UM.getUserCount(); ++i)
            UM.getUser(i).setAnswerCount(G.getAnswerCount());
    }
    
    public static HashMap.Entry<ChatGame, Future<String>> getGameFuturePair(String sID) {
        IRCBot Bot = IRCBot.getInstance();
        return Bot.getDataManager().getGameManager().getGameFuturePair(sID);
    }
    
    public static void terminate(ChatGame G, Future<String> F) {
        IRCBot Bot = IRCBot.getInstance();
        Bot.getDataManager().getGameManager().terminate(G, F);
    }
}
