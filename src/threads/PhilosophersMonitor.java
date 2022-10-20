package threads;

public class PhilosophersMonitor {
    static MyMonitor monitor;

    static class MyMonitor{
        private boolean[] isForkFree;
        public MyMonitor(int amountOfPhilosophers){
            isForkFree = new boolean[amountOfPhilosophers];
            for(int i = 0; i < amountOfPhilosophers; i++){
                isForkFree[i] = true;
            }
        }

        public synchronized void enter(int id){
            int forkLeftIndex = id;
            int forkRightIndex = (id + 1) % isForkFree.length;
            //System.out.println("monitor: id = " + id + ", left = " + isForkFree[forkLeftIndex]
            //        + ", right = " + isForkFree[forkRightIndex]);
            while(!(isForkFree[forkLeftIndex] && isForkFree[forkRightIndex])){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            isForkFree[forkLeftIndex] = false;
            isForkFree[forkRightIndex] = false;
        }

        public synchronized void exit(int id){
            int forkLeftIndex = id;
            int forkRightIndex = (id + 1) % isForkFree.length;

            isForkFree[forkLeftIndex] = true;
            isForkFree[forkRightIndex] = true;

            notifyAll();
        }
    }

    static class Philosopher implements Runnable{
        int id;

        public Philosopher(int id){
            this.id = id;
        }

        @Override
        public void run() {
            try{
                Thread.sleep((int)(Math.random() * 1000));
                System.out.println("Philosoph" + id + " will essen");
                monitor.enter(id);
                System.out.println("Philoshoph" + id + " isst");
                Thread.sleep(1000);
                System.out.println("Philosoph" + id + " ist fertig");
                monitor.exit(id);

            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        int amountOfPhilosophers = 5;
        monitor = new MyMonitor(amountOfPhilosophers);

        for(int i = 0; i < amountOfPhilosophers; i++){
            new Thread(new Philosopher(i)).start();
        }
    }
}
