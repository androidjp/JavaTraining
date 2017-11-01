package multiple_thread.how_to_cancel;

import multiple_thread.CommonCalculateMethodUtil;

import java.util.concurrent.*;

import static multiple_thread.how_to_cancel.throwable.LaunderThrowable.launderThrowable;

public class WayFuture {

    public static void main(String[] args) throws InterruptedException {
        timedRun(new Runnable() {
            public void run() {
                while(!Thread.currentThread().isInterrupted()) {
                    CommonCalculateMethodUtil.getPries(0, 100000, CommonCalculateMethodUtil.Strategy.STRATEGY_A);
                }
            }
        } , 500 , TimeUnit.MILLISECONDS);
    }


    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // 因超时而取消任务
        } catch (ExecutionException e) {
            // 任务异常，重新抛出异常信息
            throw launderThrowable(e.getCause());
        } finally {
            // 如果该任务已经完成，将没有影响
            // 如果任务正在运行，将因为中断而被取消
            task.cancel(true); // interrupt if running
            System.out.println("Task is canceled.");
        }
    }
}
