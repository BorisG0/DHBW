package threads;

import java.util.concurrent.Semaphore;

public class PhilosophersPrivSem {
    static int numberOfPhilosophers = 5;
    static boolean usingTimer = false;

    static boolean[] hungry = new boolean[numberOfPhilosophers];
    static boolean[] eating = new boolean[numberOfPhilosophers];

    static Semaphore mutex = new Semaphore(1, true);
    static Semaphore[] privSemaphore = new Semaphore[numberOfPhilosophers];

    static class Philosopher implements Runnable{
        int id;
        int idLeft;
        int idFarLeft;
        int idRight;
        int idFarRight;
        public Philosopher(int id){
            this.id = id;

            idLeft = (id - 1) % numberOfPhilosophers;
            if(idLeft == -1){
                idLeft = numberOfPhilosophers -1;
            }

            idFarLeft = (idLeft - 1) % numberOfPhilosophers;
            if(idFarLeft == -1){
                idFarLeft = numberOfPhilosophers - 1;
            }

            idRight = (id + 1) % numberOfPhilosophers;
            idFarRight = (idRight + 1) % numberOfPhilosophers;
        }
        @Override
        public void run() {
            try{
                System.out.println("Philosoph " + id + " denkend");

                if(usingTimer)
                Thread.sleep((int)(Math.random() * 5000));

                System.out.println("Philosoph " + id + " wird hungrig");

                mutex.acquire();
                if((!eating[idLeft]) && (!eating[idRight])){
                    privSemaphore[id].release();
                    hungry[id] = false;
                    eating[id] = true;
                }else {
                    hungry[id] = true;
                }
                mutex.release();

                privSemaphore[id].acquire();

                //k.A.
                System.out.println("Philosoph " + id + " essend");

                if(usingTimer)
                Thread.sleep((int)(Math.random() * 5000));

                //k.A. Ende

                mutex.acquire();
                eating[id] = false;
                System.out.println("Philosoph " + id + " wieder denkend");

                if(hungry[idLeft] && (!eating[idFarLeft])){
                    hungry[idLeft] = false;
                    eating[idLeft] = true;
                    privSemaphore[idLeft].release();
                }

                if(hungry[idRight] && (!eating[idFarRight])){
                    hungry[idRight] = false;
                    eating[idRight] = true;
                    privSemaphore[idRight].release();
                }
                mutex.release();


            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {

        for(int i = 0; i < numberOfPhilosophers; i++){
            hungry[i] = false;
            eating[i] = false;
            privSemaphore[i] = new Semaphore(0, true);
        }

        for(int i = 0; i < numberOfPhilosophers; i++){
            new Thread(new Philosopher(i)).start();
        }
    }
}
