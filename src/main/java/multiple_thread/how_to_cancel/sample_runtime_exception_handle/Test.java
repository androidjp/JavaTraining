package multiple_thread.how_to_cancel.sample_runtime_exception_handle;


/**
 * 在Executor框架中
 * ，需要将异常的捕获封装到Runnable或者Callable中并通过execute提交的任务
 * ，才能将它抛出的异常交给UncaughtExceptionHandler
 * ，而通过submit提交的任务，无论是抛出的未检测异常还是已检查异常，都将被认为是任务返回状态的一部分。
 */
public class Test {
    public static void main(String[] args){

    }
}
