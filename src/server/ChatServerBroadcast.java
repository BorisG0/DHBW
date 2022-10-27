package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatServerBroadcast {
    private final static int serverPort = 4998;

    public static void main(String[] args) {
        try {
            System.out.println("server started");
            DatagramSocket socket = new DatagramSocket(serverPort);

            while(true){
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());

                System.out.println(packet.getAddress() + ":" + packet.getPort() + ": " + message);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e){
            System.err.println(e);
        }
    }
}
