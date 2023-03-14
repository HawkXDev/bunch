package lambda;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Lambda for implementing the Runnable and Callable interfaces
 */
public class Lambda4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Runnable
        new Thread(() -> System.out.println("Hello world!")).start();

        Runnable runnable = () -> System.out.println("Java is the best language!");
        var thread = new Thread(runnable);
        thread.start();

        // Callable
        final var INT = 42;
        Callable<Integer> myCallable = () -> INT;
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);

        var thread1 = new Thread(futureTask);
        thread1.start();

        Integer result = futureTask.get();
        System.out.println("result = " + result);
    }
}
