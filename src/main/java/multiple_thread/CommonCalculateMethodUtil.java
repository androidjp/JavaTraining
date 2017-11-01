package multiple_thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟耗时方法
 */
public class CommonCalculateMethodUtil {

    public static enum Strategy {
        STRATEGY_A, STRATEGY_B, STRATEGY_C
    }

    public static List<Integer> getPries(int rangeFrom, int rangeTo, Strategy strategy) {
        List<Integer> resultList = new ArrayList<Integer>();
        switch (strategy) {
            case STRATEGY_A:
                for(int i=rangeFrom;i<=rangeTo;i++){
                    if(isPrimeA(i)){
                        resultList.add(i);
                    }
                }
                break;
            case STRATEGY_B:
                for(int i=rangeFrom;i<=rangeTo;i++){
                    if(isPrimeB(i)){
                        resultList.add(i);
                    }
                }
                break;
            case STRATEGY_C:
                for(int i=rangeFrom;i<=rangeTo;i++){
                    if(isPrimeC(i)){
                        resultList.add(i);
                    }
                }
                break;
            default:
                break;
        }
        System.out.println("Task finished");
        return resultList;
    }


    /// O(n)
    private static boolean isPrimeA(int n) {
        if (n < 2) return false;
        for (int i = 2; i < n; ++i)
            if (n % i == 0) return false;
        return true;
    }

    /// O(n/2)
    private static boolean isPrimeB(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i < n; i += 2)
            if (n % i == 0) return false;
        return true;

    }


    /// O(sqrt(n)/2)
    private static boolean isPrimeC(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int foo = (int) Math.sqrt(n);
        for (int i = 3; i <= foo; i += 2)
            if (n % i == 0) return false;
        return true;
    }

}
