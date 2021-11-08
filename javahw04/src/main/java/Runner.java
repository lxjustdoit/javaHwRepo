public class Runner implements Runnable{
    @Override
    public void run() {
        int i = 0;
        for(; i<=100; i++){
            if(i == 99){
                getResult(i);
            }
            System.out.println("当前线程计数："+i);
        }
    }

    public int getResult(int i){
        System.out.println("获取目标数");
        return i;
    }

    public static void main(String[] args) {
        Runner r = new Runner();
        Thread t1 = new Thread(r);
        t1.start();
    }
}
