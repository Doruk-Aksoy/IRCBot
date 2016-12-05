package TaskScheduler;

// Schedules timed tasks using Java ScheduledExecutorService

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface SchedulerInterface {
    public ScheduledFuture scheduleWithDelay(Callable c, long delay, TimeUnit t);
}
