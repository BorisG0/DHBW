package threads;

import java.util.concurrent.Semaphore;

public class Philosophers {
    static class Philosopher implements Runnable{
        int id;
        Semaphore[] forks;
        Philosopher(int id, Semaphore[] forks){
            this.id = id;
            this.forks = forks;
        }

        @Override
        public void run() {
            try{
                System.out.println("Philosopher " + id + " wants to start eating");

                if(id == forks.length - 1){
                    forks[(id + 1) % forks.length].acquire();
                    Thread.sleep(1000);
                    forks[id].acquire();
                }else{
                    forks[id].acquire();
                    Thread.sleep(1000);
                    forks[(id + 1) % forks.length].acquire();
                }


                System.out.println("Philosopher " + id + " eating");
                for(int i = 0; i < 50; i++){
                    System.out.print(".");
                    Thread.sleep(40);
                }
                System.out.println();
                System.out.println("Philosopher " + id + " finished");

                forks[id].release();
                forks[(id + 1) % forks.length].release();


            }catch (Exception e){
                System.out.println(e);
            }

        }
    }

    public static void main(String[] args) {
        int numberOfPhilosophers = 5;
        Semaphore[] forks = new Semaphore[numberOfPhilosophers];

        for(int i = 0; i < forks.length; i++){
            forks[i] = new Semaphore(1, true);
        }

        for(int i = 0; i < forks.length; i++){
            new Thread(new Philosopher(i, forks)).start();
        }
    }
}
