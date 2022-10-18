package threads;

import java.util.concurrent.Semaphore;

public class Testat1B {
    static Semaphore mutex = new Semaphore(1, true);
    //mutex: gegenseitiger Ausschluss zum Lesen und Bearbeiten von kritischen Verwaltungsdaten

    static final int NICHT_AUF_MITTLERER_SCHIENE = 0;
    static final int AUF_MITTLERER_SCHIENE = 1;
    static final int WARTET = 2;

    static boolean istLok0AnDerReihe = true; // Überblick darüber wer an der Reihe ist, um Abwechslung zu sichern

    static Semaphore privLok0 = new Semaphore(0, true); //privater Semaphor für Lok0, um sie aufzuwecken
    static int statusLok0 = NICHT_AUF_MITTLERER_SCHIENE;

    static Semaphore privLok1 = new Semaphore(0, true); //privater Semaphor für Lok1, um sie aufzuwecken
    static int statusLok1 = NICHT_AUF_MITTLERER_SCHIENE;


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
                if(istLok0AnDerReihe && (statusLok1 != AUF_MITTLERER_SCHIENE)){
                    statusLok0 = AUF_MITTLERER_SCHIENE;
                    privLok0.release();
                }else {
                    statusLok0 = WARTET;
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
                statusLok0 = NICHT_AUF_MITTLERER_SCHIENE;
                istLok0AnDerReihe = false;
                if(statusLok1 == WARTET){
                    statusLok1 = AUF_MITTLERER_SCHIENE;
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
                if(!istLok0AnDerReihe && (statusLok0 != AUF_MITTLERER_SCHIENE)){
                    statusLok1 = AUF_MITTLERER_SCHIENE;
                    privLok1.release();
                }else {
                    statusLok1 = WARTET;
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
                statusLok1 = NICHT_AUF_MITTLERER_SCHIENE;
                istLok0AnDerReihe = true;
                if(statusLok0 == WARTET){
                    statusLok0 = AUF_MITTLERER_SCHIENE;
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
