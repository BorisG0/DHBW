public class EuclideanAlgorithm {
    public static void main(String[] args) {

        int a = 391;
        int b = 544;

        System.out.println("a = " + a + ", b = " + b + ", ggT: " + ggT(a, b));

    }

    public static int ggT(int a, int b){
        while(a != b){
            if(a > b) a -= b;
            else b -= a;
        }
        return b;
    }
}
