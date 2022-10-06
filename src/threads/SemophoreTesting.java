package threads;

import java.util.concurrent.Semaphore;

public class SemophoreTesting {

    static Semaphore mutex = new Semaphore(1, true);
    static Runnable r = new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    mutex.acquire();

                    System.out.println(Thread.currentThread().getName() + " im kritischen Abschnitt");
                    Thread.sleep(3000);

                    mutex.release();

                }catch(InterruptedException e){
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    break;
                }catch(Exception e){
                    System.out.println(e);
                }

            }
        }
    };

    public static void main(String[] args) {
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}
