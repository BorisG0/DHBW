import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List myList = new ArrayList();

        myList.add("Hamburger");
        myList.add("Royal");
        myList.add("TS");


        for(Object o : myList){
            System.out.print(o + " ");
        }

        System.out.println();
        System.out.println(myList.contains("Hamburger"));
        System.out.println(myList.contains("BigMac"));

        System.out.println(myList.size());
        myList.remove(2);
        System.out.println(myList.size());
        myList.remove("Hamburger");

        for(Object o : myList){
            System.out.print(o + " ");
        }
    }
}
