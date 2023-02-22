package krypto;

import java.util.HashMap;
import java.util.Scanner;

public class Koinzidenzindex {
    public static void main(String[] args) {
        char[] germanAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ß', 'ä', 'ö', 'ü'};

        String text = "";
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            if(line.equals(":q")) break;
            text += line;
        }

        System.out.println("Text: " + text);
        System.out.println("Koinzidenzindex: " + koinzidenzindex(text.toLowerCase(), germanAlphabet));
    }

    public static double koinzidenzindex(String text, char[] alphabet){
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        int sum = 0;

        for(int i = 0; i < alphabet.length; i++){
            frequencyMap.put(alphabet[i], 0);
        }

        for(int i = 0; i < text.length(); i++){
            char currentChar = text.charAt(i);
            if(frequencyMap.get(currentChar) == null) continue;
            frequencyMap.put(currentChar, frequencyMap.get(currentChar) + 1);
            sum++;
        }

        if(sum == 0) return 0;

        double inzidenz = 0;

        for(char c: alphabet){
            System.out.println(c + ": " + frequencyMap.get(c));
            inzidenz += Math.pow(((double) frequencyMap.get(c)) / sum, 2);
        }

        return inzidenz;
    }
}
