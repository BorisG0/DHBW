package algorithms_datastructures;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 6;
        System.out.println("sorting.Fibonacci iterative of " + n + " is " + fibIterative(n));
        System.out.println("sorting.Fibonacci recursive of " + n + " is " + fibRecursive(n));
    }

    public static int fibIterative(int n){
        int fib = 1;
        int help = 1;

        for(int i = 1; i < n; i++){
            fib += help;
            help = fib - help;
        }

        return fib;
    }

    public static int fibRecursive(int n){
        if(n <= 1){
            return 1;
        }

        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }
}
