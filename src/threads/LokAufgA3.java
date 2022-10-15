package threads;

import java.util.concurrent.Semaphore;

public class LokAufgA3 {
    static Semaphore track = new Semaphore(1, true);

    static class Lok0 implements Runnable{
        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                while(true){
                    enterLok0();
                    Thread.sleep(1000);
                    exitLok0();
                    Thread.sleep(2000);
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }

        void enterLok0(){
            try{
                System.out.println("Lok0 am anfang mittlere Schiene");
                track.acquire();
                System.out.println("Lok0 auf mittlerer Schiene");
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void exitLok0(){
            try{
                System.out.println("Lok0 verlaesst mittlere Schiene");
                track.release();
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }

    static class Lok1 implements Runnable{
        @Override
        public void run() {
            try{
                while(true){
                    enterLok1();
                    Thread.sleep(1500);
                    exitLok1();
                    Thread.sleep(2500);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void enterLok1(){
            try{
                System.out.println("Lok1 am anfang mittlere Schiene");
                track.acquire();
                System.out.println("Lok1 auf mittlerer Schiene");
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void exitLok1(){
            try{
                System.out.println("Lok1 verlaesst mittlere Schiene");
                track.release();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Lok0()).start();
        new Thread(new Lok1()).start();
    }
}
