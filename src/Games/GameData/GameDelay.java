package Games.GameData;

import GameTask.GameTask;
import GameTask.GameTask_Dummy;
import Mediator.GameMediator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class GameDelay {
    protected ScheduledFuture pending_future;
    protected GameTask pending_task;
    
    public GameDelay() {
        pending_future = null;
        pending_task = null;
    }
    
    public ScheduledFuture getPendingFuture() {
        return pending_future;
    }
    
    public void makeDelay(long delay, TimeUnit t) {
        pending_task = new GameTask_Dummy();
        pending_future = GameMediator.scheduleTask(pending_task, delay, t);
    }
    
    public long remainingDelay() {
        return pending_future.getDelay(TimeUnit.SECONDS);
    }
    
    public void beginDelay() {
        try {
            pending_future.get();
        } catch (Exception ex) {
            System.out.println(ex + " happened!");
        }
    }
    
    public boolean onDelay() {
        return pending_future != null;
    }
    
    public boolean cancelDelay() {
        return pending_future.cancel(true);
    }
}
