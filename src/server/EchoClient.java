package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public static final int serverPort = 7;

    public static void main(String[] args) {
        String hostname = "localhost";
        PrintWriter networkOut = null;
        BufferedReader networkIn = null;
        Socket s = null;

        try{
            s = new Socket(hostname, serverPort);
            System.out.println("Connected to echo Server");
            networkIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            networkOut = new PrintWriter(s.getOutputStream());

            while(true){
                String theLine = userIn.readLine();
                if(theLine.equals(".")) break;
                networkOut.println(theLine);
                networkOut.flush();
                System.out.println(networkIn.readLine());
            }
        }catch (IOException e){
            System.err.println(e);
        }finally {
            try{
                if(s != null) s.close();
            }catch (IOException e){

            }
        }
    }
}
