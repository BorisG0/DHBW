import java.util.Set;
import java.util.TreeSet;

public class SetExamples {

    public static void main(String[] args) {
        Set<String> names = new TreeSet<String>();

        names.add("Klaus");
        names.add("Gabi");
        names.add("Frida");
        names.add("Zeus");
        names.add("Klaus");
        names.add("Michael");

        System.out.println("Amount of Names: " + names.size());
        System.out.println("Contains Gabi: " + names.contains("Gabi"));
        System.out.println("Remove Michael: " + names.remove("Michael"));
        System.out.println("Amount of Names: " + names.size());
    }
}
