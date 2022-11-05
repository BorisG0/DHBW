package server;

import java.net.DatagramPacket;


public class WorkerPoolBuffer {
    private DatagramPacket[] buffer;
    private int nextFree;
    private int nextFull;

    WorkerPoolBuffer(int size){
        buffer = new DatagramPacket[size];
        nextFree = 0;
        nextFull = 0;
    }

    public synchronized void append(DatagramPacket packet){
        while (nextFree == ((nextFull + 1) % buffer.length)){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        buffer[nextFree] = packet;
        nextFree = (nextFree + 1) % buffer.length;

        notifyAll();
    }

    public synchronized DatagramPacket remove(){
        while (nextFull == nextFree){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        DatagramPacket packet = buffer[nextFull];
        nextFull = (nextFull + 1) % buffer.length;

        notifyAll();
        return packet;
    }

}
