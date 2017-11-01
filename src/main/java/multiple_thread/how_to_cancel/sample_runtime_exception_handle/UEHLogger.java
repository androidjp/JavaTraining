package multiple_thread.how_to_cancel.sample_runtime_exception_handle;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自定义的Handler
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: "+ t.getName() ,e);
    }
}
