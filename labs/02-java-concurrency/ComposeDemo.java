import java.util.concurrent.*;

public class ComposeDemo {

    static String getProject(int projectId) {
        System.out.println("查询项目：" + projectId);
        sleep(1000);
        return "Abuja School";
    }

    static String getCost(String projectName) {
        System.out.println("查询成本：" + projectName);
        sleep(2000);
        return projectName + " 预计成本：500万美元";
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
            CompletableFuture<String> result = CompletableFuture
                    .supplyAsync(
                            () -> getProject(101),
                            executor)
                    .thenCompose(project -> CompletableFuture.supplyAsync(
                            () -> getCost(project),
                            executor));

            System.out.println("主线程可以继续执行");

            System.out.println(result.join());

        } finally {
            executor.shutdown();
        }
    }
}