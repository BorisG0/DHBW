package server;

import java.io.*;

public class FileServer {
    public static void main(String[] args) {
        System.out.println(getLine("myText.txt", 3));
    }

    private static String getLine(String fileName, int lineNumber){
        String returnLine = "";
        String filePath = new File("").getAbsolutePath();
        BufferedReader fileIn = null;
        try {
            fileIn = new BufferedReader(new FileReader(filePath.concat("/src/server/" + fileName)));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


        try {
            String line = null;
            line = fileIn.readLine();
            while(line != null){
                System.out.println(line);
                line = fileIn.readLine();
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return returnLine;
    }
}
