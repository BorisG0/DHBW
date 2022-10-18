package threads;

import java.util.concurrent.Semaphore;

public class Testat1A {
    static Semaphore empty = new Semaphore(1, true); //besagt ob Lok0 an der Reihe ist
    static Semaphore full = new Semaphore(0, true); //besagt ob Lok1 an der Reihe ist

    static class Lok0 implements Runnable{ // Kommentare in der Klasse Lok0 gelten auch für Lok1
        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                while(true){ // durch Aufgabe gegebene Struktur
                    enterLok0();
                    System.out.println("Lok0 auf mittlerer Schiene");
                    Thread.sleep(1000); //Zeit um mittlere Schiene vollständig zu befahren
                    exitLok0();
                    Thread.sleep(5000); //Zeit um den Rest des Kreises zu befahren
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }

        void enterLok0(){
            try{
                System.out.println("Lok0 vor mittlerer Schiene");
                empty.acquire();
            }catch (Exception e ){
                System.out.println(e);
            }
        }

        void exitLok0(){
            try{
                System.out.println("Lok0 verlaesst mittlere Schiene");
                full.release();
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
                    System.out.println("Lok1 auf mittlerer Schiene");
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
                System.out.println("Lok1 vor mittlerer Schiene");
                full.acquire();
            }catch (Exception e ){
                System.out.println(e);
            }
        }

        void exitLok1(){
            try{
                System.out.println("Lok1 verlaesst mittlere Schiene");
                empty.release();
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
