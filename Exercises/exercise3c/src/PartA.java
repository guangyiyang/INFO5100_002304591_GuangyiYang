import java.util.Random;
import java.math.BigInteger;

class PrimeThread extends Thread {
    @Override
    public void run() {
        
        System.out.println("Thread " + Thread.currentThread().getName() + " started calculating prime numbers");
        int count = 0;
        int number = 2;

        while (count < 25) {
            if (isPrime(number)) {
                count++;
                System.out.println("Time: " + System.currentTimeMillis() + " , Thread: " + Thread.currentThread().getName() + ", Prime: " + number);

            }

            number++;
            randomDelay();
        }
    }

    //Determine if it is a prime number
    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    //random delay 100-500msec
    private void randomDelay() {
        try {
            Thread.sleep(new Random().nextInt(401) + 100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class FibonacciThread extends Thread {
    @Override
    public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " started calculating Fibonacci series numbers");
        long a = 0, b = 1;
            System.out.println("Time: " + System.currentTimeMillis() + " , Thread: " + Thread.currentThread().getName() + ", Fibonacci number: " + a);
        for (int i = 1; i < 50; i++) {
            System.out.println("Time: " + System.currentTimeMillis() + " , Thread: " + Thread.currentThread().getName() + ", Fibonacci number: " + b);
            long next = a + b;
            a = b;
            b = next;
            randomDelay();
        }
    }
    private void randomDelay(){
        try {
            Thread.sleep(new Random().nextInt(401) + 100);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

class FactorialThread extends Thread {
    @Override
    public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " started calculating factorials");
        for (int i = 0; i <= 50; i++) {
            System.out.println("Time: " + System.currentTimeMillis() + " , Thread: " + Thread.currentThread().getName() + ", " + i + "! = " + factorial(i));
            randomDelay();
        }
    }

    private BigInteger  factorial(int n) {
        if (n == 0) return BigInteger.ONE;// 0！= 1
        BigInteger result = BigInteger.ONE;
        int i;
        for( i = 1 ; i <= n; i++ ){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;

    }

    private void randomDelay(){
        try {
            Thread.sleep(new Random().nextInt(401) + 100);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }


}


public class PartA {

    public static  void main(String[] args){

        Thread primeThread = new PrimeThread();
        Thread fibonacciThread = new FibonacciThread();
        Thread factorialThread = new FactorialThread();

        primeThread.start();
        fibonacciThread.start();
        factorialThread.start();


    }

}
