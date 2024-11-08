import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
import java.math.BigInteger;

class PrimeTask implements Runnable {
    private final int number;

    PrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        if (isPrime(number)) {
            System.out.println("Time: " + System.currentTimeMillis() + ", Thread: " + Thread.currentThread().getName() + ", Prime: " + number);
        }
        randomDelay();
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private void randomDelay() {
        try {
            Thread.sleep(new Random().nextInt(401) + 100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class FibonacciTask implements Runnable {
    private final int n;

    FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        long result = fibonacci(n);
        System.out.println("Time: " + System.currentTimeMillis() + ", Thread: " + Thread.currentThread().getName() + ", Fibonacci number: " + result);
        randomDelay();
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private void randomDelay() {
        try {
            Thread.sleep(new Random().nextInt(401) + 100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class FactorialTask implements Runnable {
    private final int n;

    FactorialTask(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        BigInteger result = factorial(n);
        System.out.println("Time: " + System.currentTimeMillis() + ", Thread " + Thread.currentThread().getName() + ", " + n + "! = " + result);
        randomDelay();
    }

    private BigInteger factorial(int n) {
        if (n == 0) return BigInteger.ONE;// 0！= 1
        BigInteger result = BigInteger.ONE;
        int i;
        for( i = 1 ; i <= n; i++ ){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    private void randomDelay() {
        try {
            Thread.sleep(new Random().nextInt(401) + 100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


public class PartB {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Calculate the first 25 prime numbers
        for (int i = 2; i < 100; i++) {
            executorService.execute(new PrimeTask(i));
        }

        // Calculate the first 50 Fibonacci numbers
        for (int i = 0; i < 50; i++) {
            executorService.execute(new FibonacciTask(i));
        }

        // Calculate the first 50 factorials
        for (int i = 0; i <= 50; i++) {
            executorService.execute(new FactorialTask(i));
        }

        executorService.shutdown();
    }


}
