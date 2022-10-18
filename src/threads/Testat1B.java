package threads;
/*
Testat: 1
Teil: B
Autoren: Boris Gratchev, Luca Grabowski
Matrikelnummern: 87824551, 7517076
 */
import java.util.concurrent.Semaphore;

public class Testat1B {
    static Semaphore mutex = new Semaphore(1, true);
    //mutex: gegenseitiger Ausschluss zum Lesen und Bearbeiten von kritischen Verwaltungsdaten

    // Status, gehören zu kritischen Daten
    static final int NICHT_AUF_MITTLERER_SCHIENE = 0;
    static final int AUF_MITTLERER_SCHIENE = 1;
    static final int WARTET = 2;

    static boolean istLok0AnDerReihe = true; // Überblick darüber wer an der Reihe ist, um Abwechslung zu sichern, funktioniert nur bei 2 Loks

    static Semaphore privLok0 = new Semaphore(0, true); //privater Semaphor für Lok0, um sie aufzuwecken
    static int statusLok0 = NICHT_AUF_MITTLERER_SCHIENE;

    static Semaphore privLok1 = new Semaphore(0, true); //privater Semaphor für Lok1, um sie aufzuwecken
    static int statusLok1 = NICHT_AUF_MITTLERER_SCHIENE;


    static class Lok0 implements Runnable{ // Kommentare in der Klasse Lok0 gelten auch für Lok1
        @Override
        public void run() {
            try{
                Thread.sleep(1000); // Einzigartig für Lok0: 1 Sekunde warten,
                                            // um zu prüfen, ob Lok0 immer Schiene zuerst befährt, auch wenn sie nicht zuerst ankommt
                while(true){ // durch Aufgabe gegebene Struktur
                    enterLok0();
                    Thread.sleep(1000); //Zeit um mittlere Schiene vollständig zu befahren, bei Lok0 und Lok1 unterschiedlich
                    exitLok0();
                    Thread.sleep(5000); //Zeit um den Rest des Kreises zu befahren
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }

        void enterLok0(){
            try{
                mutex.acquire();
                if(istLok0AnDerReihe && (statusLok1 != AUF_MITTLERER_SCHIENE)){ //prüfen, ob man an der Reihe ist und Schiene frei ist
                    statusLok0 = AUF_MITTLERER_SCHIENE; // mittlere Schiene besetzen
                    privLok0.release(); // Semaphor im vorhinein freischalten, weil Befahren erlaubt ist
                }else {
                    statusLok0 = WARTET; // WARTEN Status setzen, um von anderer Lok geweckt zu werden
                    System.out.println("Lok0 wartet vor mittlerer Schiene");
                }
                mutex.release();

                privLok0.acquire(); // entweder ins Warten gehen oder Schienen befahren
                System.out.println("Lok0 befaehrt mittlere Schiene");
            }catch (Exception e ){
                System.out.println(e);
            }
        }

        void exitLok0(){
            try{
                mutex.acquire();
                System.out.println("Lok0 verlaesst mittlere Schiene");
                statusLok0 = NICHT_AUF_MITTLERER_SCHIENE; // Schiene als frei markieren
                istLok0AnDerReihe = false; // nächsten in der Reihe ändern, um Abwechslung zu sichern
                if(statusLok1 == WARTET){ // abfragen, ob andere Lok auf freischaltung warten und ggf. freischalten und Schiene als besetzt markieren
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
                    Thread.sleep(500);
                    exitLok1();
                    Thread.sleep(1500);
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
