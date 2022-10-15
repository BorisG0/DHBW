package threads;

import java.util.concurrent.Semaphore;

public class LokAufgA2 {
    private int size;
    private String[] buffer;
    private int ctr = 0;
    private int nextFree = 0;
    private int nextFull = 0;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore full = new Semaphore(0, true);
    private Semaphore empty;

    public LokAufgA2(int size){
        this.size = size;
        buffer = new String[size];
        empty = new Semaphore(size, true);
    }

    public void append(String data){
         try{
             empty.acquire();
         }catch(Exception e){
             System.out.println(e);
         }
    }


}
