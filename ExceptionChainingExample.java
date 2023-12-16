public class ExceptionChainingExample {

    public static void main(String[] args) {
        try {
            performOperation();
        } catch (CustomException e) {
            // Catching the custom exception and inspecting the exception chain
            System.out.println("Caught CustomException: " + e.getMessage());

            // Using getCause() to retrieve the root cause
            Throwable rootCause = getRootCause(e);
            System.out.println("Root Cause: " + rootCause.getClass().getSimpleName() + ": " + rootCause.getMessage());

            // Print the entire exception chain
            printExceptionChain(e);
        }
    }

    private static void performOperation() throws CustomException {
        try {
            // Simulating an operation that may throw an exception
            int result = 1 / 0; // This will cause an ArithmeticException
        } catch (ArithmeticException ae) {
            // Creating a custom exception and using initCause() to set the root cause
            CustomException customException = new CustomException("CustomException occurred");
            customException.initCause(ae);
            throw customException;
        }
    }

    private static Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }

    private static void printExceptionChain(Throwable throwable) {
        System.out.println("Exception Chain:");
        Throwable currentException = throwable;
        while (currentException != null) {
            System.out.println(currentException.getClass().getSimpleName() + ": " + currentException.getMessage());
            currentException = currentException.getCause();
        }
    }

    // CustomException class extending RuntimeException for simplicity
    static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }
}
