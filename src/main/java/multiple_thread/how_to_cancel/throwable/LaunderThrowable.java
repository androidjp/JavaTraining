package multiple_thread.how_to_cancel.throwable;

public class LaunderThrowable {
    // accept the throwable(Error or Exception) from upstream
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
        throw new IllegalStateException("Not unchecked" ,t);
    }
}
