# 处理非正常线程的终止
* 遇到`RuntimeException`(不可修复的错误)。此时，如果是子线程抛出的异常，那么这个异常将不会被父线程捕获，而是直接抛出到控制台。
* 解决方式：认真处理每个子线程中的异常，尽量设计完备的try-catch-finally代码块。
* 使用 `UncaughtExceptionHandler` 主动解决未检测异常问题。
* 如果JVM发现一个线程因未捕获异常而退出，就会把该异常交个Thread对象设置的UncaughtExceptionHandler来处理。
* 如果Thread对象没有设置任何异常处理器，那么默认的行为就是上面提到的抛出到控制台，在System.err中输出。
* 使用方式：
  ```
  Thread.setUncaughtExceptionHandler(XXXExceptionhandler);
  ```

# 注意：
1. 在Executor框架中，需要将异常的捕获封装到Runnable或者Callable中并通过execute提交的任务，才能将它抛出的异常交给UncaughtExceptionHandler，而通过submit提交的任务，无论是抛出的未检测异常还是已检查异常，都将被认为是任务返回状态的一部分。
2. 如果一个由submit提交的任务由于抛出了异常而结束，那么这个异常将被Future.get封装在ExecutionException中重新抛出。
  
  