package threads;

import java.util.concurrent.Semaphore;

public class LokAufgA {
    static Semaphore empty = new Semaphore(1, true);

    static class Lok extends Thread{
        int id;
        int speed;
        public Lok(int id, int speed){
            this.id = id;
            this.speed = speed;
        }

        public void run(){
            try{
                Thread.sleep(speed);
                while(true){
                    //fahren
                    enter();

                    //Lok im mittleren Teilstück
                    Thread.sleep(speed);

                    exit();
                    Thread.sleep(speed * 2);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void enter(){
            try{
                System.out.println("Lok" + id + " versucht auf mittleres Teilstueck zu fahren");
                empty.acquire();
                System.out.println("Lok" + id + " befaehrt mittleres Teilstueck");
            }catch (Exception e){
                System.out.println(e);
            }
        }
        void exit(){
            try{
                System.out.println("Lok" + id + " verlaesst mittleres Teilstueck");
                empty.release();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }



    public static void main(String[] args) {
        new Lok(0, 1000).start();
        new Lok(1, 1500).start();
    }
}
