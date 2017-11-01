package multiple_thread.how_to_cancel.sample_manage_sub_thread;

import sun.rmi.runtime.Log;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogService extends BaseService {
    private LogThread consumerThread;
    private BlockingQueue<String> queue;
    private boolean isShutdown;
    private boolean isSuspend;
    private int reserveations;
    private PrintWriter writer;

    public LogService(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public LogService() {
    }

    public void init() {
        this.queue = new LinkedBlockingQueue<String>();
        this.reserveations = 0;
        this.isSuspend = true;
        this.isShutdown = true;
    }

    public void start() {
        synchronized (this){
            this.isSuspend = false;
            if (this.isShutdown) {
                this.isShutdown = false;
                this.consumerThread = new LogThread();
                this.consumerThread.start();
            }
        }
    }

    public void stop() {
        synchronized (this) {
            this.isSuspend = true;
        }
    }

    public void destroy() {
        synchronized (this) {
            this.isShutdown = true;
        }
        consumerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalStateException("is already shutdown");
            if (isSuspend) {
                throw new IllegalStateException("The LogService is stopped");
            }
            ++reserveations;
        }
        queue.put(msg);
    }


    private void consumeLog(String msg) throws InterruptedException {
        System.out.println("Log: " + msg);
        //                        writer.println(msg);
        Thread.sleep(1000);
    }

    private class LogThread extends Thread {
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.class) {
                            ///线程退出
                            if (isShutdown && reserveations == 0)
                                break;
                            if(isSuspend){
                                Thread.sleep(1000);
                                continue;
                            }
                        }
                        String msg = null;
                        synchronized (LogService.this) {
                            msg = queue.take();
                            --reserveations;
                        }
                        consumeLog(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }


}
