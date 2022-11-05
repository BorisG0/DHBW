package server;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class WorkerPoolClient {
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
                packet = new DatagramPacket(data, data.length, address, WorkerPoolServer.DEFAULT_PORT);
                socket.send(packet);

                DatagramPacket receivePacket = new DatagramPacket(new byte[WorkerPoolServer.MAX_PACKET_SIZE], WorkerPoolServer.MAX_PACKET_SIZE);
                socket.receive(receivePacket);

                String answer = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(answer);
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
