package server;

/*
Testat: 2
Autoren: Boris Gratchev, Tom Luca Grabowski
Matrikelnummern: 87824551, 7517076
 */

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
                String userLine = userIn.readLine(); //auf Tastatureingabe warten

                socket = new Socket("localhost", MessageServer.DEFAULT_PORT); //Verbindung zum Server
                networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                networkOut = new PrintWriter(socket.getOutputStream());

                networkOut.println(userLine); //Befehl an Server schicken
                networkOut.flush();

                System.out.println(networkIn.readLine()); //Antwort vom Server anzeigen
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
