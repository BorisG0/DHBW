package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageClient {
    public static void main(String[] args) {
        try {
            System.out.println("starting client");
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            Socket socket;
            BufferedReader networkIn;
            PrintWriter networkOut;


            while(true){
                String userLine = userIn.readLine();

                socket = new Socket("localhost", MessageServer.DEFAULT_PORT);
                networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                networkOut = new PrintWriter(socket.getOutputStream());

                networkOut.println(userLine);
                networkOut.flush();

                System.out.println(networkIn.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
