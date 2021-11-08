import java.util.concurrent.*;

public class Runner05 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<Long> task = executor.submit(()->{
                long  res = 1;
                for(int i = 1; i<100; i++){
                    res *= i;
                    if(res > Integer.MAX_VALUE){
                        break;
                    }
                    System.out.println(i+"th res: "+res);
                }
                return res;
            });
            System.out.println("线程运行结果："+task.get());
        }catch (Exception e){
            System.out.printf("submit catch");
            e.printStackTrace();
        }
        executor.shutdown();
//        System.out.println("线程运行结果："+task.get());
    }
}
