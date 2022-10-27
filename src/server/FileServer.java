package server;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class FileServer {
    private static String relativePath = new File("").getAbsolutePath().concat("/textFiles/");
    public final static int serverPort = 4999;

    public static void main(String[] args) {
        try {
            System.out.println("server started");
            DatagramSocket socket = new DatagramSocket(serverPort);

            while(true){
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                String[] parts = message.split(" ");

                if(parts[0].equals("read")){
                    String requestedLineText = getLine(parts[1], Integer.parseInt(parts[2]));

                    byte[] data = requestedLineText.getBytes();
                    DatagramPacket returnPacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                    socket.send(returnPacket);
                }else if(parts[0].equals("write")){
                    writeLine(parts[1], Integer.parseInt(parts[2]), parts[3]);
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e){
            System.err.println(e);
        }
    }

    private static String getLine(String fileName, int lineNumber){
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

        return returnLine;
    }

    private static void writeLine(String fileName, int lineNumber, String newLine){
        try {
            ArrayList<String> allLines = new ArrayList<>();
            BufferedReader fileIn = new BufferedReader(new FileReader(relativePath + fileName));
            String line = fileIn.readLine();
            while(line != null){
                allLines.add(line);
                line = fileIn.readLine();
            }
            allLines.add(lineNumber, newLine);

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
