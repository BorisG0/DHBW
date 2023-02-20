package krypto;

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
        char space = ' ';
        int[] frequencies = new int[alphabet.length];
        int sum = 0;
        for(int i = 0; i < frequencies.length; i++){
            frequencies[i] = 0;
        }

        a: for(int i = 0; i < text.length(); i++){
            char currentChar = text.charAt(i);
            if(currentChar == space) continue;

            for(int j = 0; j < alphabet.length; j++){
                if(currentChar == alphabet[j]){
                    frequencies[j] = frequencies[j] + 1;
                    sum++;
                    continue a;
                }
            }
        }

        if(sum == 0) return 0;

        double inzidenz = 0;

        for(int i = 0; i < frequencies.length; i ++){
            System.out.println(alphabet[i] + " " + i + " " + (frequencies[i] / ((double)sum)));
            inzidenz += Math.pow(frequencies[i] / ((double)sum), 2);
        }

        return inzidenz;
    }
}
