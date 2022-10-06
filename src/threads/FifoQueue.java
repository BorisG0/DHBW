package threads;

import java.util.concurrent.Semaphore;

public class FifoQueue {
    static Semaphore mutex = new Semaphore(1, true);

    class Node{
        String data;
        Node next;

        Node(String data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    Node head;

    public void put(String s){
        try {
            mutex.acquire();


            if(head == null){
                head = new Node(s, null);
                mutex.release();
                return;
            }

            Node n = head;

            while(n.next != null){
                n = n.next;
            }

            n.next = new Node(s, null);

            mutex.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        String allData = "";
        Node n = head;
        while(n != null){
            allData += n.data + ", ";
            n = n.next;
        }

        return allData;
    }

    public String get(){
        String data = null;
        if(head == null) return null;

        try{
            mutex.acquire();

            data = head.data;
            head = head.next;

            mutex.release();

        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        int amountOfThreads = 4;
        Thread[] threads = new Thread[amountOfThreads];
        for(int i = 0; i < threads.length; i++){
            Thread t = new Thread(r);
            t.start();
            threads[i] = t;
        }
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int counter = 0;
        String d = myQueue.get();
        while(d != null){
            System.out.println(d);
            d = myQueue.get();
            counter++;
        }
        System.out.println("counter: " + counter);
    }

    static FifoQueue myQueue = new FifoQueue();

    static Runnable r = () -> {
        for(int i = 0; i < 10; i++){
            myQueue.put(Thread.currentThread().getName() + " added " + i);
        }
    };
}
