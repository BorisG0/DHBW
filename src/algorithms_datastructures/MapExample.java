package algorithms_datastructures;

import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        Map<Integer, String> colorCodes = new HashMap();

        colorCodes.put(1000, "rot");
        colorCodes.put(2000, "blau");
        colorCodes.put(3000, "grün");
        colorCodes.put(4000, "gelb");
        colorCodes.put(5000, "gelb");

        System.out.println("Farbe für Code 2000: " + colorCodes.get(2000));

        colorCodes.remove(3000);

        colorCodes.put(2000, "lila");

        System.out.println("Farbe für Code 2000: " + colorCodes.get(2000));

        Set<Integer> colorCodeKeys = colorCodes.keySet();

        for(int colorKey: colorCodeKeys){
            System.out.println(colorKey + " is color: " + colorCodes.get(colorKey));
        }


        Collection<String> colorList = colorCodes.values();

        for(String color: colorList){
            System.out.println(color);
        }
    }
}
