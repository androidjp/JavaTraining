package multiple_thread.how_to_cancel.sample_manage_sub_thread;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        LogService logService = new LogService();
        logService.start();

        for(int i=0;i<10;i++){
            logService.log("你好 ---> "+ i);
        }

        Thread.sleep(2000);
        logService.stop();

        Thread.sleep(10000);
        logService.start();
//        logService.destroy();
    }
}
