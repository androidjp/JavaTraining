package multiple_thread.how_to_cancel;

import multiple_thread.CommonCalculateMethodUtil;
import multiple_thread.how_to_cancel.runnable.RethowableTask;
import multiple_thread.how_to_cancel.throwable.LaunderThrowable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 关于如何关闭定时任务
 */
public class WayInterruptScheduleTask {


    public static void main(String[] args) throws InterruptedException {

        timedRun(new Runnable() {
            public void run() {
                while(!Thread.currentThread().isInterrupted()) {
                    CommonCalculateMethodUtil.getPries(0, 100000, CommonCalculateMethodUtil.Strategy.STRATEGY_A);
                }
            }
        } , 1000 , TimeUnit.MILLISECONDS);
    }


    private static final ScheduledExecutorService cancalExec = Executors.newScheduledThreadPool(1);

    public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        /// 不在 Thread 中处理， 而在Runnable中做处理
        RethowableTask task = new RethowableTask(r);
        final Thread taskThread = new Thread(task);

        /// 执行子任务
        taskThread.start();

        //// 定时 中断 Thread
        cancalExec.schedule(new Runnable() {
            public void run() {
                System.out.println("Start Interrupt task!");
                taskThread.interrupt();
            }
        }, timeout, unit);

        //限时等待任务子线程执行完毕
        taskThread.join(unit.toMillis(timeout));

        //尝试抛出中断时出现的异常
        task.rethrow();

    }

}
