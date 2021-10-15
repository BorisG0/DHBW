public class ClassicAlgorithms {
    public static void main(String[] args) {

        int a = 391;
        int b = 544;

        System.out.println("a = " + a + ", b = " + b + ", ggT: " + ggT(a, b));

        int n = 5;

        System.out.println("n = " + n + ", fac(n) = " + fac(n));

    }

    public static int ggT(int a, int b){ //größter gemeinsamer teiler
        while(a != b){
            if(a > b) a -= b;
            else b -= a;
        }
        return b;
    }

    public static int fac(int n){ //faculty
        int fac = 1;
        for(int i = 1; i <= n; i++){
            fac *= i;
        }
        return fac;
    }
}
