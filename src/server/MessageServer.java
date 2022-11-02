package server;

/*
Testat: 2
Autoren: Boris Gratchev, Tom Luca Grabowski
Matrikelnummern: 87824551, 7517076
 */

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MessageServer {
    public static final int DEFAULT_PORT = 7777;

    public static void main(String[] args) {
        try {
            //Server wird gestartet
            System.out.println("starting server");
            ServerSocket server = new ServerSocket(DEFAULT_PORT);
            Socket connection;
            PrintWriter out;
            BufferedReader in;

            String fileSeparator = File.separator; //Kompatibilät für Windows und Mac
            String userDirectory = System.getProperty("user.home") + fileSeparator + "Desktop" + fileSeparator + "Messages" + fileSeparator;

            while(true){
                String lineOut = "";
                connection = server.accept(); //auf neue Verbindung zu einem Client warten
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String lineIn = in.readLine(); //Input des Clients lesen
                System.out.println("new request: " + lineIn);

                String[] lineInSplit = lineIn.split(" ", 2); //Aufteilen in Befehl und Inhalt

                if(lineInSplit[0].equals("SAVE")){ //SAVE Befehl abarbeiten
                    Thread.sleep(10000); //Verzögerung um gleichzeitigen Zugriff zu testen
                    File file;
                    String key;
                    do{ //Schleife um zu prüfen ob der Key nicht schon existiert, falls ja: neuen Key erzeugen
                        key = "" + ((int)(Math.random() * 1000));
                        file = new File(userDirectory + key);

                    }while(!file.createNewFile());

                    FileWriter fileWriter = new FileWriter(userDirectory + key);
                    fileWriter.write(lineInSplit[1]); //Datei mit erhaltenem Inhalt befüllen
                    fileWriter.close();

                    lineOut = "KEY " + key;

                }else if(lineInSplit[0].equals("GET")){ //GET Befehl abarbeiten
                    String key = lineInSplit[1]; //Key aus Nachricht bekommen
                    File file = new File(userDirectory + key);

                    if(file.exists()){ //Prüfen ob Datei mit Key als Name existiert
                        Scanner fileReader = new Scanner(file);
                        String data = fileReader.nextLine();
                        fileReader.close();

                        lineOut = "OK " + data;
                    }else{
                        lineOut = "FAILED"; //Fehler: Key existiert nicht
                    }
                }else{
                    lineOut = "FAILED"; //Fehler: Befehl falsch
                }

                out = new PrintWriter(connection.getOutputStream());
                out.println(lineOut); //Antwort an Client zurückschicken
                out.flush();
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
