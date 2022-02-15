import java.util.ArrayList;
import java.util.Scanner;

public class ClassicAlgorithms {
    public static void main(String[] args) {

        int n;
//        int a = 391;
//        int b = 544;
//
//        System.out.println("ggT: a = " + a + ", b = " + b + ", ggT: " + ggT(a, b));
//
//        n = 5;
//
//        System.out.println("Fakultät: n = " + n + ", fac(n) = " + fac(n));


//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Alle primzahlen bis n ausgeben.");
//        System.out.println("n eingeben:");
//
//        String input = sc.nextLine();
//
//        n = Integer.parseInt(input);
//
//
//        int[] z = siebDesEratosthenes(n);
//
//
//        int counter = 0;
//        for(int i: z){
//            if(i != -1){
//                System.out.println(i);
//                counter++;
//            }
//
//        }
//
//        System.out.println("Gesamt Primzahlen: " + counter);
        n = 10;

        printPas2(n);

    }

    public static void printPas2(int n){

        System.out.println(1);

        int a = 0, b = 0, c = 0;
        ArrayList<Integer> v = new ArrayList<Integer>(); //vorherige Zeile
        ArrayList<Integer> x = new ArrayList<Integer>(); //aktuelle Zeile

        for(int i = 0; i < n - 1; i++){
            System.out.print(1 + " ");
            for(int j = 0; j < i; j++){

                if(j - 1 >= 0){
                    a = v.get(j - 1);
                }else {
                    a = 1;
                }

                if(j < v.size()){
                    b = v.get(j);
                }else {
                    b = 1;
                }

                c = a + b;

                x.add(c);


                System.out.print(c + " ");
            }
            v = x;
            x = new ArrayList<Integer>();
            System.out.println(1);
        }

    }

    public static void printPas(int lines){

        for(int i = 0; i < lines; i++){
            for(int j = 0; j < i; j++){
                System.out.print(pas(i, j));
            }
            System.out.println(1);
        }

    }

    public static int pas(int line, int n){
        if(line <= 1) return 1;
        if(n <= 0) return 1;
        if(n >= line) return 1;

        return pas(line - 1, n - 1) + pas(line - 1, n);
    }


    public static boolean aufgabe4(boolean a, boolean b){
        return !(a == b);
    }

    public static int[] siebDesEratosthenes(int n){
        int[] zahlen = new int[n];



        int s = (int) Math.sqrt((double)n); //Schranke

        for(int i = 0; i < n; i++){
            zahlen[i] = i+1;
        }
        zahlen[0] = -1;

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
