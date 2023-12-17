import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class AsyncExceptionHandlingWithCompletableFuture {
    private static final Logger log = Logger.getLogger(AsyncExceptionHandlingWithCompletableFuture.class.getName());

    public static void main(String[] args) {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
            // Simulating an exception in the first step
            throw new RuntimeException("Exception in the first step");
        })
        .thenApplyAsync(value -> {
            // This stage won't be executed due to the exception in the previous stage
            log.info("Executing thenApplyAsync: " + value);
            return (Integer)(value) * 2;
        })
        .thenApplyAsync(value -> {
            // Another transformation (won't be executed due to exception in the previous stage)
            log.info("Executing another thenApplyAsync: " + value);
            return (Integer)(value) / 3;
        })
        .exceptionally(ex -> {
            log.info("An exception occurred: " + ex.getMessage());
            return 0;
        });

        // Joining the CompletableFuture to wait for the result
        Integer finalResult = (Integer)result.join();
        System.out.println("Final Result: " + finalResult);
    }
}
