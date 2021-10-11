public class EuclideanAlgorithm {
    public static void main(String[] args) {

        int a = 391;
        int b = 544;

        System.out.println("a = " + a + ", b = " + b + ", ggT: " + ggT(a, b));

    }

    public static int ggT(int a, int b){

        if(a < b){
            a = a + b;
            b = a - b;
            a = a - b;
        }

        int c = 0;

        while(a % b != 0){
            c = a % b;
            a = b;
            b = c;

        }

        return c;
    }
}
