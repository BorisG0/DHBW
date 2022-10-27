package server;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] data;
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet;

            Scanner scanner = new Scanner(System.in);
            String message;

            while (true){
                message = scanner.nextLine();
                data = message.getBytes();
                packet = new DatagramPacket(data, data.length, address, 4999);
                socket.send(packet);

                if(message.split(" ")[0].equals("read")){
                    DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(receivePacket);

                    String requestedLine = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println(requestedLine);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(UnknownHostException e){
            System.err.println("unknown host");
        }catch(IOException e){
            System.err.println(e);
        }
    }
}
