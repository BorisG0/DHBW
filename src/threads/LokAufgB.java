package threads;

import java.util.concurrent.Semaphore;

public class LokAufgB {
    static int ausserhalbMittlereSchiene = 0;
    static int wartenVorMittlereSchiene = 1;
    static int aufMittlererSchiene = 2;

    static Semaphore mutex = new Semaphore(1, true);

    static Semaphore privLok0 = new Semaphore(0, true);
    static int stateLok0 = aufMittlererSchiene;

    static Semaphore privLok1 = new Semaphore(0, true);
    static int stateLok1 = ausserhalbMittlereSchiene;

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
                System.out.println("Lok0 erreicht mittlere Schiene");
                mutex.acquire();
                if(stateLok1 != aufMittlererSchiene){
                    privLok0.release();
                    stateLok0 = aufMittlererSchiene;
                }else {
                    stateLok0 = wartenVorMittlereSchiene;
                    System.out.println("Lok0 wartet vor mittlerer Schiene");
                }
                mutex.release();

                privLok0.acquire();
                System.out.println("Lok0 befaehrt mittlere Schiene");
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void exitLok0(){
            try{
                mutex.acquire();
                System.out.println("Lok0 verlaesst mittlere Schiene");
                stateLok0 = ausserhalbMittlereSchiene;
                if(stateLok1 == wartenVorMittlereSchiene){
                    privLok1.release();
                    stateLok1 = aufMittlererSchiene;
                }
                mutex.release();
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
                System.out.println("Lok1 erreicht mittlere Schiene");
                mutex.acquire();
                if(stateLok0 != aufMittlererSchiene){
                    privLok1.release();
                    stateLok1 = aufMittlererSchiene;
                }else {
                    stateLok1 = wartenVorMittlereSchiene;
                    System.out.println("Lok1 wartet vor mittlerer Schiene");
                }
                mutex.release();

                privLok1.acquire();
                System.out.println("Lok1 befaehrt mittlere Schiene");
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void exitLok1(){
            try{
                System.out.println("Lok1 verlaesst mittlere Schiene");
                mutex.acquire();
                stateLok1 = ausserhalbMittlereSchiene;
                if(stateLok0 == wartenVorMittlereSchiene){
                    privLok0.release();
                    stateLok0 = aufMittlererSchiene;
                }
                mutex.release();
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
