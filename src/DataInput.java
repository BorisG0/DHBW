import java.io.*;
import java.util.ArrayList;

public class DataInput {
    public static void main(String[] args) {
        System.out.println("Geben Sie einen Text ein:");

        byte[] input = new byte[255];

        try{
            System.in.read(input, 0, input.length);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(new String(input));


        InputStreamReader systemInReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(systemInReader);

        String inputString = "";
        ArrayList<String> inputTextLines = new ArrayList<>();

        System.out.println("Bitte geben Sie einen Text ein (Beenden mit :q):");

        while(true){

            try{
                inputString = bufferedReader.readLine();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if(inputString.equals(":q")){
                break;
            }

            System.out.println("Eingegeben: " + inputString);
            inputTextLines.add(inputString);

        }

        for(String s : inputTextLines){
            System.out.println(s);
        }

        File file = new File(System.getProperty("user.dir") + File.separator + "MyText.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for(String line : inputTextLines){
                fileWriter.write(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
            }
        }


        System.out.println("\nAuslesen aus einer Datei:");
        System.out.println("=============================");

        try(FileReader fileReader = new FileReader(file);
            BufferedReader fileBufferedReader = new BufferedReader(fileReader)){

            String line;

            while((line = fileBufferedReader.readLine()) != null){
                System.out.println(line);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
