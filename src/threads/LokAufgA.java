package threads;

import java.util.concurrent.Semaphore;

public class LokAufgA {
    static Semaphore empty = new Semaphore(1, true);

    static class Lok0 extends Thread{
        public void run(){
            try{
                while(true){
                    //fahren
                    enterLok0();
                    Thread.sleep(1000);
                    //Lok 0 im mittleren Teilstück
                    exitLok0();
                    Thread.sleep(2000);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void enterLok0(){
            try{
                System.out.println("Lok0 versucht auf mittleres Teilstueck zu fahren");
                empty.acquire();
                System.out.println("Lok0 befaehrt mittleres Teilstueck");
            }catch (Exception e){
                System.out.println(e);
            }
        }
        void exitLok0(){
            try{
                System.out.println("Lok0 verlaesst mittleres Teilstueck");
                empty.release();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    static class Lok1 extends Thread{
        public void run(){
            try{
                while(true){
                    //fahren
                    enterLok1();
                    Thread.sleep(1500);
                    //Lok 1 im mittleren Teilstück
                    exitLok1();
                    Thread.sleep(2500);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void enterLok1(){
            try{
                System.out.println("Lok1 versucht auf mittleres Teilstueck zu fahren");
                empty.acquire();
                System.out.println("Lok1 befaehrt mittleres Teilstueck");
            }catch (Exception e){
                System.out.println(e);
            }
        }
        void exitLok1(){
            try{
                System.out.println("Lok1 verlaesst mittleres Teilstueck");
                empty.release();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }


    public static void main(String[] args) {
        new Lok0().start();
        new Lok1().start();
    }
}
