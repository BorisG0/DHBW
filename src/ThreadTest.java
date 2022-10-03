public class ThreadTest extends Thread{
    static int[] toSum;
    static int sum;
    static int[] localSums;

    private int id;
    private int amountOfThreads;

    public ThreadTest(int id, int amountOfThreads){
        this.id = id;
        this.amountOfThreads = amountOfThreads;
    }

    public void run(){
        int startIndex = (toSum.length/amountOfThreads) * id;
        int endIndex = startIndex + (toSum.length/amountOfThreads);
        int localSum = 0;

        for(int i = startIndex; i < endIndex; i++){
            localSum += toSum[i];
        }

        localSums[id] = localSum;
    }


    public static void main(String[] args) {
        toSum = new int[(int)Math.pow(2, 21)];
        for(int i = 0; i < toSum.length; i++){
            toSum[i] = 1;
        }

        sum = 0;
        long start = System.nanoTime();
        sumWithoutThreads();
        long elapsedTime = System.nanoTime() - start;
        System.out.println("Not Using Threads: "+ sum + " calculated in: " + elapsedTime + "ns or " + (((double)elapsedTime)/1000000) + "ms");


        for(int i = 2; i <= 128; i *= 2){
            sum = 0;
            start = System.nanoTime();
            sumWithThreads(i);
            elapsedTime = System.nanoTime() - start;
            System.out.println("Using " + i + " Threads: "+ sum + " calculated in: " + elapsedTime + "ns or " + (((double)elapsedTime)/1000000) + "ms");
        }



    }

    public static void sumWithoutThreads(){
        for(int i: toSum){
            sum += i;
        }
    }

    public static void sumWithThreads(int amountOfThreads){
        Thread[] threads = new Thread[amountOfThreads];
        localSums = new int[amountOfThreads];
        for(int i = 0; i < amountOfThreads; i++){
            Thread t = new ThreadTest(i, amountOfThreads);
            t.start();
            threads[i] = t;
        }

        for(Thread t: threads){
            try {
                t.join();
            }catch (Exception e){
                System.out.println(e);
            }
        }

        for(int i: localSums){
            sum += i;
        }
    }
}
