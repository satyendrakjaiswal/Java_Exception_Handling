import java.io.File;
import java.io.IOException;

public class ResourceCleanupExample {

    public static void main(String[] args) {
        // Registering Shutdown Hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> cleanupResources()));

        // Application logic here
        try {
            // Creating a temporary file
            File tempFile = createTempFile();

            // Simulating application logic
            System.out.println("Application is running. Press Ctrl+C to simulate unexpected termination.");        // Application logic here
            try {
                simulateUnexpectedTermination();
            } catch (RuntimeException e) {
                System.err.println("Caught an unhandled exception: " + e.getMessage());
            }

            // Simulating a long-running process
            Thread.sleep(5000); // Sleep for 5 seconds

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void simulateUnexpectedTermination() {
        // Simulate an unexpected termination by throwing an unhandled exception
        throw new RuntimeException("Simulating unexpected termination");
    }

    private static void cleanupResources() {
        // Cleanup code for releasing resources
        // Delete temporary files, close connections, etc.
        System.out.println("Cleaning up resources...");

        // Delete the temporary file created during application execution
        deleteTempFile();
    }

    private static File createTempFile() throws IOException {
        // Create a temporary file in the system's temporary directory
        File tempFile = File.createTempFile("example", ".txt");

        // Print the path of the created temporary file
        System.out.println("Temporary file created: " + tempFile.getAbsolutePath());

        return tempFile;
    }

    private static void deleteTempFile() {
        // Delete the temporary file created during application execution
        File tempFile = new File(System.getProperty("java.io.tmpdir"), "example.txt");

        if (tempFile.exists()) {
            if (tempFile.delete()) {
                System.out.println("Temporary file deleted successfully.");
            } else {
                System.err.println("Failed to delete temporary file.");
            }
        } else {
            System.out.println("Temporary file does not exist.");
        }
    }
}
