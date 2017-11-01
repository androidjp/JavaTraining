package multiple_thread.how_to_cancel.thread;

public abstract class BaseCancelableThread extends Thread{


    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            onRun();
        }
    }

    protected abstract void onRun();

    public void cancel(){
        interrupt();
    }

}
