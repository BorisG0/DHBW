package other;

import java.io.*;
import java.util.ArrayList;

public class PersistPersons {
    public static void main(String[] args) {
        ArrayList<Person> personList = new ArrayList<>();

        personList.add(new Person("Markus", "Söder"));
        personList.add(new Person("Ronald", "McDonald"));
        personList.add(new Person("Donald", "Trump"));
        personList.add(new Person("Ivanka", "Trump"));

        File myPersonFile = new File(System.getProperty("user.dir") + File.separator + "persons.txt");

        if(!myPersonFile.exists()){
            try{
                myPersonFile.createNewFile();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        try(FileWriter myPersonFileWriter = new FileWriter(myPersonFile)){
            for(Person currentPerson: personList){
                myPersonFileWriter.write(currentPerson.getFamilyName() + "," + currentPerson.getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nmanuelle serialisierung");
        ArrayList<Person> personFromFileList = new ArrayList<>();

        try(FileReader fileReader = new FileReader(myPersonFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String line;

            while((line = bufferedReader.readLine()) != null){
                System.out.println("Gelesen von File: " + line);
                String[] personNames = line.split(",");
                personFromFileList.add(new Person(personNames[1], personNames[0]));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Person p: personFromFileList){
            System.out.println(p.getFamilyName() + ", " + p.getName());
        }

        System.out.println("\nJava Serializable");

        File mySerializedPersonFile = new File(System.getProperty("user.dir") + File.separator + "persons.dat");

        if(!mySerializedPersonFile.exists()){
            try{
                mySerializedPersonFile.createNewFile();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(mySerializedPersonFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){

            for(Person currentPerson : personList){
                objectOutputStream.writeObject(currentPerson);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        ArrayList<Person> personsSerialized = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(mySerializedPersonFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

            while(true){
                try {

                    System.out.println(((Person)objectInputStream.readObject()).getFullName());
                }catch(EOFException eofException){
                    break;
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
