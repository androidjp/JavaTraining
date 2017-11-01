package multiple_thread.how_to_cancel.thread;

import multiple_thread.CommonCalculateMethodUtil;

import java.util.ArrayList;
import java.util.List;

public class MyPrimeThread extends BaseCancelableThread {

    private final List<Integer> primeList = new ArrayList<Integer>();
    private int range = 0;
    public MyPrimeThread() {
    }

    protected void onRun() {
        List<Integer> resList = CommonCalculateMethodUtil.getPries(range, range+1, CommonCalculateMethodUtil.Strategy.STRATEGY_A);
        synchronized (this){
            this.primeList.addAll(resList);
            this.range +=2;
        }
    }

    public synchronized void resetRange(){
        this.range  = 0;
    }

    public synchronized void resetPrimeList(){
        this.primeList.clear();
    }

    public synchronized List<Integer> getPrimeList(){
        return new ArrayList<Integer>(this.primeList);
    }
}
