package Mediator;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

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
    public static ChatGame gameExists(ChatGame G) {
        IRCBot Bot = IRCBot.getInstance();
        return Bot.getDataManager().getGameManager().gameExists(G);
    }
    
    public static ChatGame makeGame(Message msg) {
        Game_Factory F = new Game_Factory();
        return F.makeGame(msg, true);
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
}
