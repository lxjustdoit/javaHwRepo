public class Runner02 extends Thread {

    @Override
    public void run() {
        int sum = 0;
        for(int i = 0; i<10; i++){
            sum += i;
        }
        System.out.println("获取sum："+sum);
    }

    public static void main(String[] args) {
        Runner02 r2 = new Runner02();
        Thread t = new Thread(r2);
        t.start();
    }
}
