import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread2 = new Thread(() -> {
            for(int i=0;i<20 ;i++){
                System.out.println("We are in thread: " + Thread.currentThread().getName());
            }

        });
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.setName("xxx");

        Thread thread = new Thread(() -> {
            // code that will run in a new thread
            for(int i=0;i<20 ;i++){
                System.out.println("We are in thread: " + Thread.currentThread().getName());
            }
        });
        thread.setName("new worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        Thread thread3 = new Thread(() -> {
            // code that will run in a new thread
           throw new RuntimeException("intentional exception");
        });
        thread3.setName("new worker thread");
        thread3.setPriority(Thread.NORM_PRIORITY);

        thread3.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("A critical error happend in thread " + t.getName() + " the error is " + e.getMessage());
        });


        System.out.println("We are in thread: " + Thread.currentThread().getName()+" before starting a new thread");
        thread2.start();
        thread.start();
        thread3.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName()+" after starting a new thread");
        Thread.sleep(10000);

    }
}
