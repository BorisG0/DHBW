package server;

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
            ServerSocket server = new ServerSocket(DEFAULT_PORT);
            Socket connection;
            PrintWriter out;
            BufferedReader in;

            String generalPath = "C:\\Users\\boris\\Desktop\\TextMessages\\";

            while(true){
                String lineOut = "";
                connection = server.accept();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String lineIn = in.readLine();
                System.out.println("new request: " + lineIn);

                String[] lineInSplit = lineIn.split(" ", 2);
                if(lineInSplit[0].equals("SAVE")){
                    File file = null;
                    String key = "";
                    do{
                        key = "" + ((int)(Math.random() * 5));
                        file = new File(generalPath + key);

                    }while(!file.createNewFile());

                    FileWriter fileWriter = new FileWriter(generalPath + key);
                    fileWriter.write(lineInSplit[1]);
                    fileWriter.close();

                    lineOut = "KEY " + key;
                }

                if(lineInSplit[0].equals("GET")){
                    String key = lineInSplit[1];

                    File file = new File(generalPath + key);
                    if(file.exists()){
                        Scanner fileReader = new Scanner(file);
                        String data = fileReader.nextLine();
                        fileReader.close();

                        lineOut = "OK " + data;
                    }else{
                        lineOut = "FAILED";
                    }
                }


                out = new PrintWriter(connection.getOutputStream());
                out.println(lineOut);
                out.flush();
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
