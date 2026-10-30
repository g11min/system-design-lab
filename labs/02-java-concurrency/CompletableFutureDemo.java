import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

            System.out.println("子线程开始计算");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

            return 100 + 200;
        });

        System.out.println("主线程继续执行");

        CompletableFuture<Integer> processed = future.thenApply(result -> result * 2);

        System.out.println("最终结果：" + processed.join());
    }
}