import java.util.concurrent.*;

public class ParallelTaskDemo {

    static String query(String name, int seconds) {
        try {
            System.out.println(name + " 开始查询");
            Thread.sleep(seconds * 1000L);
            System.out.println(name + " 查询完成");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return name + "的数据";
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        try {
            CompletableFuture<String> project = CompletableFuture.supplyAsync(
                    () -> query("项目", 2), executor);

            CompletableFuture<String> cost = CompletableFuture.supplyAsync(
                    () -> query("成本", 3), executor);

            CompletableFuture<String> history = CompletableFuture.supplyAsync(
                    () -> query("历史案例", 4), executor);

            CompletableFuture<Void> all = CompletableFuture.allOf(
                    project, cost, history);

            all.join();

            System.out.println(project.join());
            System.out.println(cost.join());
            System.out.println(history.join());

            long elapsed = System.currentTimeMillis() - start;

            System.out.println(
                    "总耗时：" + elapsed + " ms");

        } finally {
            executor.shutdown();
        }
    }
}