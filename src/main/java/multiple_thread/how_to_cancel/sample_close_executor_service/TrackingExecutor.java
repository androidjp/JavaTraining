package multiple_thread.how_to_cancel.sample_close_executor_service;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 这个Executor的作用，就多了一个：记录哪些在cancel 的时候 没有被执行的任务
 * 这就保证了 调用 ExecutorService.shutdownNow() 时 ，有些task 会被忽略 的 情况。
 */
public class TrackingExecutor extends AbstractExecutorService{
    private final ExecutorService exec;
    //synchronized Set [cancelled tasks set]
    private final Set<Runnable> tasksCancelledShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    public void shutdown() {
        exec.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    public boolean isShutdown() {
        return exec.isShutdown();
    }

    public boolean isTerminated() {
        return exec.isTerminated();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout,unit);
    }

    public List<Runnable> getCancelledTasks(){
        if(!exec.isTerminated())
            throw new IllegalStateException("The ExecutorService is still available");
        return new ArrayList<Runnable>(tasksCancelledShutdown);
    }

    ///执行过程中增加一个interrupt的情况的处理
    public void execute(final Runnable command) {
        exec.execute(new Runnable() {
            public void run() {
                try{
                    command.run();
                }finally {
                    if(isShutdown() && Thread.currentThread().isInterrupted())
                        tasksCancelledShutdown.add(command);
                }
            }
        });
    }
}
