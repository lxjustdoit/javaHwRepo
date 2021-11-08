import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Runner03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        return Thread.currentThread().getName()+"线程执行结束";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable r = new Runner03();
        FutureTask future = new FutureTask(r);
        Thread t = new Thread(future);
        System.out.println(Thread.currentThread().getName()+"当前线程");
        t.start();
        System.out.printf((String)future.get());
    }
}
