
import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args)
            throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            Future<Integer> future = executor.submit(() -> {

                Thread.sleep(2000);

                return 100 + 200;
            });

            System.out.println("任务已经提交");

            System.out.println("正在等待结果...");

            // Integer result = future.get();
            System.out.println("任务是否完成：" + future.isDone());

            Integer result = future.get();

            System.out.println("任务是否完成：" + future.isDone());

            System.out.println("Result: " + result);

        } finally {
            executor.shutdown();
        }
    }
}
