package algorithms_datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<String>();

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


        myList.add("Chicken Nuggets");
        myList.add("McRib");
        myList.add("Filet'o'Fish");

        for (String s: myList){
            System.out.println(s);
        }

        //Zugriff über Iteraror

        System.out.println("\nAccess with Iterator:");

        Iterator<String> iterator = myList.iterator();
        while(iterator.hasNext()){
            String name = iterator.next();
            System.out.println(name);
        }
    }
}
