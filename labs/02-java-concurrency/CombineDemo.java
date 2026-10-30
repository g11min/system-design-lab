import java.util.concurrent.*;

public class CombineDemo {

    static String queryCost() {
        sleep(2000);
        return "成本：500万美元";
    }

    static String queryHistory() {
        sleep(3000);
        return "历史案例：3个相似项目";
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            long start = System.currentTimeMillis();

            CompletableFuture<String> cost = CompletableFuture.supplyAsync(
                    () -> queryCost(), executor);

            CompletableFuture<String> history = CompletableFuture.supplyAsync(
                    () -> queryHistory(), executor);

            CompletableFuture<String> report = cost.thenCombine(history, (c, h) -> {
                return "项目报告\n" + c + "\n" + h;
            });

            System.out.println(report.join());

            System.out.println(
                    "耗时：" +
                            (System.currentTimeMillis() - start) +
                            " ms");

        } finally {
            executor.shutdown();
        }
    }
}