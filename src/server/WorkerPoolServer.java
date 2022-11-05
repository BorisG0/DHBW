package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WorkerPoolServer {
    public static final int DEFAULT_PORT = 5999;
    public static final int MAX_PACKET_SIZE = 65507;
    public static void main(String[] args) {
        new WorkerPoolServer();
    }

    WorkerPoolServer(){
        try {
            DatagramSocket socket = new DatagramSocket(DEFAULT_PORT);

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
        new Thread(new WorkerPoolWorker(packet)).start();
    }
}
