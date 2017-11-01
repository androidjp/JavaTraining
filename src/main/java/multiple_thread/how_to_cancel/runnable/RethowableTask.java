package multiple_thread.how_to_cancel.runnable;

import multiple_thread.how_to_cancel.throwable.LaunderThrowable;

public class RethowableTask implements Runnable {
    private volatile Throwable t;
    private Runnable r;

    public RethowableTask() {

    }

    public RethowableTask(Runnable r) {
        this.r = r;
    }

    public void setRunable(Runnable r) {
        this.r = r;
    }

    public void run() {
        try {
            if (r != null) {
               r.run();
            }
        } catch (Throwable t) {
            //中断策略，保存当前抛出的异常，退出
            this.t = t;
        }
    }

    public void rethrow() {
        if (t != null) {
            throw LaunderThrowable.launderThrowable(t);
        }
    }
}