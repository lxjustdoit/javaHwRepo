public class Runner04 implements Runnable{
    private static int sum = 1;

    @Override
    public void run() {
        for(int i = 1; i<100000; i++){
            sum += i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner04 r = new Runner04();
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(500);
        System.out.println("result of sum is "+sum);
    }
}
