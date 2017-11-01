package multiple_thread.how_to_cancel.sample_manage_sub_thread;

///一个基本的服务，拥有自己的生命周期方法，而这些方法，可用于管理其内部的子线程的开闭
public abstract class BaseService {

    public BaseService(){
        init();
    }

    public abstract void init();

    public abstract void start();

    public abstract void stop();

    public abstract void destroy();

}
