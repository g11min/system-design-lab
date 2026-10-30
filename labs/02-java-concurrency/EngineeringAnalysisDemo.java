import java.util.concurrent.*;

public class EngineeringAnalysisDemo {

    static String work(String name, int seconds) {
        try {
            System.out.println(name + " 开始");
            Thread.sleep(seconds * 1000L);
            System.out.println(name + " 完成");
            return name + "结果";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture<String> document = CompletableFuture.supplyAsync(
                    () -> work("资料分析", 2),
                    executor);

            CompletableFuture<String> cost = CompletableFuture.supplyAsync(
                    () -> work("成本查询", 1),
                    executor);

            CompletableFuture<String> history = CompletableFuture.supplyAsync(
                    () -> work("历史案例检索", 3),
                    executor).completeOnTimeout(
                            "历史案例检索超时",
                            2,
                            TimeUnit.SECONDS);

            CompletableFuture<String> report = document.thenCombine(
                    cost,
                    (d, c) -> d + "\n" + c).thenCombine(
                            history,
                            (partial, h) -> partial + "\n" + h);

            System.out.println("等待分析报告...");

            System.out.println("\n最终报告：");
            System.out.println(report.join());

        } finally {
            executor.shutdown();
        }
    }
}