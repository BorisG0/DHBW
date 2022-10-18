package threads;

import java.util.concurrent.Semaphore;

public class Testat1B {
    static Semaphore mutex = new Semaphore(1, true);

    static boolean istMittlereSchieneFrei = true;

    static Semaphore privLok0 = new Semaphore(0, true);
    static boolean istLok0AnDerReihe = true;
    static boolean wartetLok0AnSchiene = false;

    static Semaphore privLok1 = new Semaphore(0, true);
    static boolean istLok1AnDerReihe = false;
    static boolean wartetLok1AnSchiene = false;


    static class Lok0 implements Runnable{
        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                while(true){
                    enterLok0();
                    Thread.sleep(1000); //Zeit um mittlere Schiene vollständig zu befahren
                    exitLok0();
                    Thread.sleep(5000); //Zeit um den Rest des Kreis zu befahren
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }

        void enterLok0(){
            try{
                mutex.acquire();
                if(istLok0AnDerReihe && istMittlereSchieneFrei){
                    istMittlereSchieneFrei = false;
                    privLok0.release();
                }else {
                    wartetLok0AnSchiene = true;
                    System.out.println("Lok0 wartet vor mittlerer Schiene");
                }
                mutex.release();

                privLok0.acquire();
                System.out.println("Lok0 befaehrt mittlere Schiene");
            }catch (Exception e ){
                System.out.println(e);
            }
        }

        void exitLok0(){
            try{
                mutex.acquire();
                System.out.println("Lok0 verlaesst mittlere Schiene");
                istMittlereSchieneFrei = true;
                istLok0AnDerReihe = false;
                istLok1AnDerReihe = true;
                if(wartetLok1AnSchiene){
                    privLok1.release();
                }
                mutex.release();
            }catch (Exception e ){
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
                    Thread.sleep(500); //Zeit um mittlere Schiene vollständig zu befahren
                    exitLok1();
                    Thread.sleep(1500); //Zeit um den Rest des Kreis zu befahren
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }

        void enterLok1(){
            try{
                mutex.acquire();
                if(istLok1AnDerReihe && istMittlereSchieneFrei){
                    istMittlereSchieneFrei = false;
                    privLok1.release();
                }else {
                    wartetLok1AnSchiene = true;
                    System.out.println("Lok1 wartet vor mittlerer Schiene");
                }
                mutex.release();

                privLok1.acquire();
                System.out.println("Lok1 befaehrt mittlere Schiene");
            }catch (Exception e ){
                System.out.println(e);
            }
        }

        void exitLok1(){
            try{
                mutex.acquire();
                System.out.println("Lok1 verlaesst mittlere Schiene");
                istMittlereSchieneFrei = true;
                istLok1AnDerReihe = false;
                istLok0AnDerReihe = true;
                if(wartetLok0AnSchiene){
                    privLok0.release();
                }
                mutex.release();
            }catch (Exception e ){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Lok0()).start();
        new Thread(new Lok1()).start();
    }
}
