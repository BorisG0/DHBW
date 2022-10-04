package other;

public class Calculator {
    public static void main(String[] args) {
        for(int i = 1; i <= 30; i++){
            System.out.println(i + " is prime: " + isPrime(i));
        }
    }

    public double add(double a, double b){
        return a + b;
    }

    public double subtract(double a, double b){
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public static boolean isPrime(int number){
        for(int i = 2; i <= number / 2; i++){
            if(number % i == 0) return false;
        }
        return true;
    }
}
