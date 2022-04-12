import java.io.File;
import java.util.Properties;
import java.util.Set;

public class FileSystem {
    public static void main(String[] args) {

        File[] rootList = File.listRoots();


        for(File root : rootList){
            System.out.println("-------------");
            System.out.println(root.getPath() + " \nexistiert: " + root.exists());
            System.out.println("can read: " + root.canRead());
            System.out.println("can write: " + root.canWrite());
        }

        //File anyFile = new File("C:\\");

        System.out.println("\nSystem Properties in Java: ");
        System.out.println("============================");
        Properties systemProperties = System.getProperties();

        Set systemPropertiesKeys = systemProperties.keySet();
//        for(Object key : systemPropertiesKeys){
//            System.out.println(key + ": " + systemProperties.get(key));
//        }

        System.out.println("\nSystem Properties in Java: ");
        System.out.println("============================");

        File userDir = new File(System.getProperty("user.dir"));

        System.out.println("Name: " + userDir.getName());
        System.out.println("Pfad: " + userDir.getPath());
        System.out.println("Existiert: " + userDir.exists());
        System.out.println("Ist Verzeichnis: " + userDir.isDirectory());
        System.out.println("Ist Datei: " + userDir.isFile());

        System.out.println("\nInhalt von " + userDir.getName() + ":");
        System.out.println("==============================");

//        File[] userFiles = userDir.listFiles();
//        for(File file : userFiles){
//            System.out.println(file.getName());
//            if(file.isDirectory()){
//                for(File f: file.listFiles()){
//                    System.out.println(" - " + f.getName());
//                }
//            }
//        }
        listDirectory(userDir, "");

        System.out.println("Umgang mit Verzeichnissen und Dateien");
        System.out.println("========================================");

        String pathString = System.getProperty("user.dir") + File.separator + "myDirectory";
        System.out.println(pathString);
        File myDirectory = new File(pathString);

        System.out.println("Existiert: " + myDirectory.exists());


    }

    public static void listDirectory(File dir, String prefix){
        for(File file : dir.listFiles()){
            System.out.println(prefix + file.getName());
            if(file.isDirectory()){
                listDirectory(file, " - " + prefix);
            }
        }
    }
}
