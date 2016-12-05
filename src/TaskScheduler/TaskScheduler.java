package TaskScheduler;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ConstantData.Game_Data;

public class TaskScheduler implements SchedulerInterface {
    private final ScheduledExecutorService scheduler;
    private ArrayList<ScheduledFuture> futures;
    
    public TaskScheduler() {
        scheduler = Executors.newScheduledThreadPool(Game_Data.MAX_THREADS * Game_Data.MAX_GAME_TYPES);
        futures = new ArrayList<>();
    }
    
    @Override public ScheduledFuture scheduleWithDelay(Callable C, long delay, TimeUnit t) {
        ScheduledFuture F = scheduler.schedule(C, delay, t);
        futures.add(F);
        return F;
    }
    
    public ArrayList<ScheduledFuture> getFutures() {
        return futures;
    }
}
