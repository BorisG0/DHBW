package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class WorkerPoolServer {
    public static final int DEFAULT_PORT = 5999;
    public static final int MAX_PACKET_SIZE = 65507;

    public static WorkerPoolServer instance; //Server dient gleichzeitig als Monitor
    public WorkerPoolBuffer buffer;
    private int bufferSize = 8;

    private int numberOfThreads = 3;

    //-------------------------------------------------------------------
    //Infos für Monitor
    private int readCount = 0;
    private boolean isWriting = false;
    private int writeQueue = 0;
    //-------------------------------------------------------------------

    DatagramSocket socket;

    public static void main(String[] args) {
        instance = new WorkerPoolServer();
        instance.start();
    }

    WorkerPoolServer(){
        System.out.println("new server");
        buffer = new WorkerPoolBuffer(bufferSize);
        try {
            socket = new DatagramSocket(DEFAULT_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        System.out.println("server started");
        for(int i = 0; i < numberOfThreads; i++){
            new Thread(new WorkerPoolWorker()).start();
        }

        try {
            while(true){
                DatagramPacket packet = new DatagramPacket(new byte[MAX_PACKET_SIZE], MAX_PACKET_SIZE);
                socket.receive(packet);
                dispatch(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatch(DatagramPacket packet){
        buffer.append(packet);
        //new Thread(new WorkerPoolWorker(packet)).start();
    }

    synchronized void startRead(){
        while(isWriting || (writeQueue > 0)){ //warten, falls gerade geschrieben wird, oder geschrieben werden will
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        readCount++;
    }

    synchronized void endRead(){
        readCount--;
        notifyAll();
    }

    synchronized void startWrite(){
        writeQueue++; //Wunsch zum Schreiben setzen
        while (readCount > 0 || isWriting){ //warten, falls geschrieben oder gelesen wird
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        writeQueue--;
        isWriting = true;
    }

    synchronized void endWrite(){
        isWriting = false;
        notifyAll();
    }
}
