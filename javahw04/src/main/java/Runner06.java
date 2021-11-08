import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Runner06 implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Runner06 r6 = new Runner06();
        Thread t = new Thread(r6);
        t.start();
        t.interrupt();
        Thread.sleep(500);
        System.out.println(Thread.currentThread()+" execute finish");
    }

    @Override
    public void run() {
        int res = 1;
        for(int i = 1; i<10; i++){
            res *= i;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final res "+res);
    }
}
