package server;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ChatClientBroadcast {
    private final static int port = 4998;
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            byte[] data = "first message".getBytes();

            InetAddress address = InetAddress.getByName("255.255.255.255");
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);

            socket.send(packet);

            Scanner scanner = new Scanner(System.in);
            String message = "";

            while (true){
                message = scanner.nextLine();
                data = message.getBytes();
                packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);
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
