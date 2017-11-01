package multiple_thread.how_to_cancel;

import multiple_thread.CommonCalculateMethodUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaySettingCancleFlag {

    public static void main(String[] args) {
        List<Integer> resList = null;
        try {
            resList = MyRunnable.getPrimes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(resList.toString());
    }
}

class MyRunnable implements Runnable {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    private volatile boolean canceled = false;

    private final List<Integer> totalPrimeList = new ArrayList<Integer>();

    private int range = 0;

    public void run() {
        range = 0;
        while (!canceled) {
            List<Integer> list = CommonCalculateMethodUtil.getPries(range, range + 10, CommonCalculateMethodUtil.Strategy.STRATEGY_A);
//            System.out.print(list.toString());
            synchronized (totalPrimeList) {
                this.totalPrimeList.addAll(list);
            }
            range += 11;
        }
        System.out.println("canceled!");
    }


    public void cancel() {
        this.canceled = true;
    }

    public synchronized List<Integer> getTotalPrimeList() {
        return new ArrayList<Integer>(this.totalPrimeList);
    }

    public static List<Integer> getPrimes() throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        exec.execute(myRunnable);
        try{
            Thread.sleep(1000);
        }
        finally {
            myRunnable.cancel();
        }
        return myRunnable.getTotalPrimeList();
    }
}
