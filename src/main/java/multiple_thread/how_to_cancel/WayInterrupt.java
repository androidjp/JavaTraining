package multiple_thread.how_to_cancel;

import multiple_thread.how_to_cancel.thread.MyPrimeThread;

import java.util.List;

public class WayInterrupt {
    public static void main(String[] args) throws InterruptedException {
        MyPrimeThread  thread = new MyPrimeThread();
        thread.start();
        Thread.sleep(1000);
        thread.cancel();
        List<Integer> resList = thread.getPrimeList();
        System.out.println(resList.size());
    }
}
