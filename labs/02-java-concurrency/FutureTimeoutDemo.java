import java.util.concurrent.*;

public class FutureTimeoutDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future = executor.submit(() -> {
            System.out.println("开始执行耗时任务...");
            Thread.sleep(5000);
            return 300;
        });

        try {
            System.out.println("主线程开始等待结果");

            Integer result = future.get(2, TimeUnit.SECONDS);

            System.out.println("结果：" + result);

        } catch (TimeoutException e) {
            System.out.println("等待超时！");
            future.cancel(true);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("等待被中断");

        } catch (ExecutionException e) {
            System.out.println("任务执行失败：" + e.getCause());

        } finally {
            executor.shutdown();
        }
    }
}