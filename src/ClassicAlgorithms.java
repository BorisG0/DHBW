import java.util.Scanner;

public class ClassicAlgorithms {
    public static void main(String[] args) {

        int a = 391;
        int b = 544;

        System.out.println("ggT: a = " + a + ", b = " + b + ", ggT: " + ggT(a, b));

        int n = 5;

        System.out.println("Fakultät: n = " + n + ", fac(n) = " + fac(n));


        Scanner sc = new Scanner(System.in);

        System.out.println("Alle primzahlen bis n ausgeben.");
        System.out.println("n eingeben:");

        String input = sc.nextLine();

        n = Integer.parseInt(input);


        int[] z = siebDesEratosthenes(n);


        int counter = 0;
        for(int i: z){
            if(i != -1){
                System.out.println(i);
                counter++;
            }

        }

        System.out.println("Gesamt Primzahlen: " + counter);




    }

    public static int[] siebDesEratosthenes(int n){
        int[] zahlen = new int[n];

        int s = (int) Math.sqrt((double)n); //Schranke

        for(int i = 0; i < n; i++){
            zahlen[i] = i+1;
        }

        int a = 2;

        b: while(a <= s){
            for(int i = a * a; i <= zahlen.length; i += a){
                zahlen[i - 1] = -1;
            }

            for(int i = a; i < zahlen.length; i++){
                if(zahlen[i] != -1){
                    a = zahlen[i];
                    break;
                }
                if(i == zahlen.length -1) break b;
            }

        }


        return zahlen;
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
