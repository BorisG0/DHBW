package algorithms_datastructures;

import java.util.*;

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

        System.out.println("\nsequential output: ");
        for (String s : names){
            System.out.println(s);
        }


        System.out.println("\nAdd Viktor: " + names.add("Viktor"));

        System.out.println("\nsequential output: ");
        Iterator<String> i = names.iterator();
        while(i.hasNext()){
            String name = i.next();
            System.out.println(name);
        }

        System.out.println("\nWorking with custom Objects");
        Set<FullName> fullNames = new TreeSet<>();

        fullNames.add(new FullName("Boris", "Gratchev"));
        fullNames.add(new FullName("Ivanka", "Trump"));
        fullNames.add(new FullName("Ronald", "McDonald"));
        fullNames.add(new FullName("Donald", "Trump"));

        for(FullName currentName: fullNames){
            System.out.println(currentName);
        }


        System.out.println("\nCustom Comparator:");
        Set<FullName> namesBySignCount = new TreeSet<>(new SortFullNameBySignCount());

        namesBySignCount.add(new FullName("Boris", "Gratchev"));
        namesBySignCount.add(new FullName("Ivanka", "Trump"));
        namesBySignCount.add(new FullName("Ronald", "McDonald"));
        namesBySignCount.add(new FullName("Donald", "Trump"));

        for(FullName currentName: namesBySignCount){
            System.out.println(currentName);
        }



        System.out.println("\nArrayList custom sort:");
        List<FullName> namesArrayList = new ArrayList<FullName>();
        namesArrayList.add(new FullName("Boris", "Gratchev"));
        namesArrayList.add(new FullName("Ivanka", "Trump"));
        namesArrayList.add(new FullName("Ronald", "McDonald"));
        namesArrayList.add(new FullName("Donald", "Trump"));

        for(FullName n: namesArrayList){
            System.out.println(n);
        }

        Collections.sort(namesArrayList, (FullName name1, FullName name2)->{
            return name1.toString().length() - name2.toString().length();
        });

        System.out.println("\nsorted custom");

        for(FullName n: namesArrayList){
            System.out.println(n);
        }


    }
}
