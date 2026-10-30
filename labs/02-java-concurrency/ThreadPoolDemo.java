
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {

            final int taskId = i;

            executor.submit(() -> {

                String name = Thread.currentThread().getName();

                System.out.println(
                        "START Task " + taskId + " - " + name);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println(
                        "END Task " + taskId + " - " + name);
            });
        }

        executor.shutdown();
    }
}
