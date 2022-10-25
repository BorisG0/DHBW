package server;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            byte[] data = "first message".getBytes();

            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(data, data.length, address, ChatServer.serverPort);

            socket.send(packet);

            Scanner scanner = new Scanner(System.in);
            String message = "";

            while (true){
                message = scanner.nextLine();
                data = message.getBytes();
                packet = new DatagramPacket(data, data.length, address, ChatServer.serverPort);
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
