import java.util.concurrent.*;

public class ExceptionDemo {

    public static void main(String[] args) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            System.out.println("调用 AI 服务...");

            if (true) {
                throw new RuntimeException("AI 服务不可用");
            }

            return "AI 分析成功";
        });

        CompletableFuture<String> result = future.exceptionally(ex -> {
            System.out.println(
                    "捕获异常：" + ex.getMessage());

            return "AI 暂时不可用，请稍后重试";
        });

        System.out.println(result.join());
    }
}