package server;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class FileServer {
    private static String relativePath = "/Users/borisg/IdeaProjects/DHBW/textFiles/";
    public final static int DEFAULT_PORT = 4999;


    public static void main(String[] args) {
        FileServer fileServer = new FileServer();
    }

    class Worker implements Runnable{
        private DatagramPacket packet;
        private DatagramSocket socket;
        Worker(DatagramPacket packet){
            this.packet = packet;
        }

        @Override
        public void run() {
            try {
                socket = new DatagramSocket();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            String message = new String(packet.getData(), 0, packet.getLength());
            String[] parts = message.split(" ");

            if(parts[0].equals("read")){
                String requestedLineText = getLine(parts[1], Integer.parseInt(parts[2]));

                byte[] data = requestedLineText.getBytes();
                DatagramPacket returnPacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());

                try {
                    socket.send(returnPacket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if(parts[0].equals("write")){
                writeLine(parts[1], Integer.parseInt(parts[2]), parts[3]);
            }
        }

        String getLine(String fileName, int lineNumber){

            System.out.println("starting to read");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String returnLine = "";
            BufferedReader fileIn = null;

            try {
                fileIn = new BufferedReader(new FileReader(relativePath + fileName));
            } catch (FileNotFoundException e) {
                System.out.println(e);
                return "";
            }

            try {
                for(int i = 0; i < lineNumber - 1; i++){
                    fileIn.readLine();
                }
                returnLine = fileIn.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }

            System.out.println("finished reading");
            return returnLine;
        }

        void writeLine(String fileName, int lineNumber, String newLine){
            try {
                ArrayList<String> allLines = new ArrayList<>();
                BufferedReader fileIn = new BufferedReader(new FileReader(relativePath + fileName));
                String line = fileIn.readLine();
                while(line != null){
                    allLines.add(line);
                    line = fileIn.readLine();
                }
                allLines.add(lineNumber - 1, newLine);

                PrintWriter fileOut = new PrintWriter(new FileWriter(relativePath + fileName));

                for (String s: allLines){
                    fileOut.println(s);
                }
                fileOut.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    FileServer(){
        try {
            System.out.println("server started");
            DatagramSocket socket = new DatagramSocket(DEFAULT_PORT);

            while(true){
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                socket.receive(packet);

                Thread worker = new Thread(new Worker(packet));
                worker.start();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e){
            System.err.println(e);
        }
    }
}
