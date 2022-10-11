import java.util.concurrent.Semaphore;

public class RingBuffer {
    String[] buffer;
    int nextFree;
    int ctr;
    int nextFull;
    Semaphore mutex;
    Semaphore full;
    Semaphore empty;

    RingBuffer(int size){
        buffer = new String[size];
        nextFree = 0;
        ctr = 0;
        nextFull = 0;

        mutex = new Semaphore(1, true);
        full = new Semaphore(0, true);
        empty = new Semaphore(size, true);
    }

    public void append(String s){
        try{
            System.out.println("producer arriving");
            empty.acquire();
            mutex.acquire();
            System.out.println("producer active with " + s);

            buffer[nextFree] = s;
            nextFree = (nextFree + 1) % buffer.length;
            ctr++;

            mutex.release();
            full.release();
            System.out.println("producer gone");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String remove(){
        String s = "";

        try{
            System.out.println("consumer arriving");
            full.acquire();
            mutex.acquire();
            System.out.println("consumer active");

            s = buffer[nextFull];
            nextFull = (nextFull + 1) % buffer.length;
            ctr--;

            mutex.release();
            empty.release();
            System.out.println("consumer gone with " + s);
        }catch (Exception e){
            System.out.println(e);
        }

        return s;
    }

    static class Consumer implements Runnable{
        RingBuffer rb;
        public Consumer(RingBuffer rb){
            this.rb = rb;
        }

        @Override
        public void run() {
            String data;
            while(true){
                data = rb.remove();
            }
        }
    }

    static class Producer implements Runnable{
        RingBuffer rb;
        public Producer(RingBuffer rb){
            this.rb = rb;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++){
                rb.append("Data: " + i);
            }
        }
    }

    public static void main(String[] args) {
        RingBuffer myBuffer = new RingBuffer(5);
        new Thread(new Consumer(myBuffer)).start();
        new Thread(new Producer(myBuffer)).start();
    }

}
