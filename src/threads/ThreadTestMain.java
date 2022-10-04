package threads;

public class ThreadTestMain {
    public static void main(String[] args) {
        int[] toSum = new int[(int)Math.pow(2, 21)];
        for(int i = 0; i < toSum.length; i++){
            toSum[i] = 1;
        }

        long start = System.nanoTime();
        int sum = sumWithoutThreads(toSum);
        long elapsedTime = System.nanoTime() - start;

        System.out.println(sum + " calculated in: " + elapsedTime + "ns or " + (((double)elapsedTime)/1000000) + "ms");
    }

    public static int sumWithoutThreads(int[] arr){
        int sum = 0;
        for(int i: arr){
            sum += i;
        }
        return sum;
    }
}
