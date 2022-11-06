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
        while(true){ //ab Start: ständig neue Pakete aus dem Buffer herausnehmen und bearbeiten
            System.out.println("Worker(" + Thread.currentThread().getName() + ") waiting for packet");
            packet = WorkerPoolServer.instance.buffer.remove();
            work();
        }
    }

    private void work(){ //Paket bearbeiten
        String message = new String(packet.getData(), 0, packet.getLength());
        String[] parts = message.split(" ", 2); //Nachricht im Paket in Befehl und Parameter teilen
        String[] params = parts[1].split(",", 3); //Parameter aufteilen

        if(parts[0].equals("READ")){
            read(params[0], Integer.parseInt(params[1]));
        }

        if (parts[0].equals("WRITE")) {
            write(params[0], Integer.parseInt(params[1]), params[2]);
        }
    }

    private void read(String fileName, int lineNo){
        System.out.println("Worker(" + Thread.currentThread().getName() + ") reading file: " + fileName + " line: " + lineNo);

        WorkerPoolServer.instance.startRead(); //Anfang: kritischer Abschnitt zum Lesen
        try {
            Thread.sleep(10000); //Wartezeit, zum Testen, der Nebenläufigkeit
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WorkerPoolFile file = new WorkerPoolFile(fileName);
        String answer = file.read(lineNo);
        WorkerPoolServer.instance.endRead(); //Ende: kritischer Abschnitt

        sendAnswer(answer);
    }

    private void write(String fileName, int lineNo, String data){
        System.out.println("Worker(" + Thread.currentThread().getName() + ") writing in file: " + fileName + " line: " + lineNo + " data: " + data);

        WorkerPoolServer.instance.startWrite(); //Anfang: kritischer Abschnitt zum Schreiben
        try {
            Thread.sleep(10000); //Wartezeit, zum Testen, der Nebenläufigkeit
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WorkerPoolFile file = new WorkerPoolFile(fileName);
        String answer = file.write(lineNo, data);
        WorkerPoolServer.instance.endWrite(); //Ende: kritischer Abschnitt

        sendAnswer(answer);
    }

    private void sendAnswer(String answer){
        byte[] returnData = answer.getBytes();
        DatagramPacket returnPacket = new DatagramPacket(returnData, returnData.length, packet.getAddress(), packet.getPort());

        try {
            socket.send(returnPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
