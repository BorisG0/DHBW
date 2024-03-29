package threads;

import java.util.concurrent.Semaphore;

public class LokAufgA2 {
    static LokAufgA2 bb = new LokAufgA2(1);

    private int size;
    private int onTrack;
    private int waiting = -1;
    private Semaphore full = new Semaphore(0, true);
    private Semaphore empty;


    public LokAufgA2(int size){
        this.size = size;
        empty = new Semaphore(size, true);
    }

    public void append(int id){
         try{
             System.out.println("Lok" + id + " kommt am");
             empty.acquire();
             System.out.println("Lok" + id + " auf mittlerer schiene");

             onTrack = id;

             full.release();

         }catch(Exception e){
             System.out.println(e);
         }
    }

    public int remove(){
        int toReturn = -1;
        try{
            System.out.println("Lok" + onTrack + " verlaesst");
            full.acquire();
            empty.release();

        }catch(Exception e){
            System.out.println(e);
        }

        return onTrack;
    }

    static class Lok0 implements Runnable{
        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                while(true){
                    enterLok0();
                    Thread.sleep(1000);
                    exitLok0();
                    Thread.sleep(1500);
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }

        void enterLok0(){
            try{
                bb.append(0);
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void exitLok0(){
            try{
                bb.remove();
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
                bb.append(1);
            }catch (Exception e){
                System.out.println(e);
            }
        }

        void exitLok1(){
            try{
                bb.remove();
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
