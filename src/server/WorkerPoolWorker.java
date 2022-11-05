package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class WorkerPoolWorker implements Runnable{
    DatagramPacket packet;
    private DatagramSocket socket;

    WorkerPoolWorker(){
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(true){
            System.out.println("Worker(" + Thread.currentThread().getName() + ") waiting for packet");
            packet = WorkerPoolServer.instance.buffer.remove();
            work();
        }
    }

    private void work(){
        String message = new String(packet.getData(), 0, packet.getLength());
        String[] parts = message.split(" ", 2);
        String[] params = parts[1].split(",", 3);

        if(parts[0].equals("READ")){
            read(params[0], Integer.parseInt(params[1]));
        }

        if (parts[0].equals("WRITE")) {
            write(params[0], Integer.parseInt(params[1]), params[2]);
        }
    }

    private void read(String fileName, int lineNo){
        System.out.println("Worker(" + Thread.currentThread().getName() + ") reading file: " + fileName + " line: " + lineNo);
        WorkerPoolServer.instance.startRead();
        WorkerPoolFile file = new WorkerPoolFile(fileName);

        String answer = file.read(lineNo);

        byte[] returnData = answer.getBytes();
        DatagramPacket returnPacket = new DatagramPacket(returnData, returnData.length, packet.getAddress(), packet.getPort());

        try {
            socket.send(returnPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WorkerPoolServer.instance.endRead();
    }

    private void write(String fileName, int lineNo, String data){
        System.out.println("Worker(" + Thread.currentThread().getName() + ") writing in file: " + fileName + " line: " + lineNo + " data: " + data);
        WorkerPoolServer.instance.startWrite();
        WorkerPoolFile file = new WorkerPoolFile(fileName);

        String answer = file.write(lineNo, data);

        byte[] returnData = answer.getBytes();
        DatagramPacket returnPacket = new DatagramPacket(returnData, returnData.length, packet.getAddress(), packet.getPort());

        try {
            socket.send(returnPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WorkerPoolServer.instance.endWrite();
    }
}
