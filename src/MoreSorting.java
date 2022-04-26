import java.util.Arrays;

public class MoreSorting {
    public static void main(String[] args) {
        int[] myNumbers = {
                50, 70, 100, 80, 40, 30, 20, 10, 60
        };

        System.out.println("not sorted: ");

        printArray(myNumbers);

        int[] mySortedNumbers = bubbleSort(myNumbers.clone());

        System.out.println("\nbubbleSort: ");

        printArray(mySortedNumbers);


        System.out.println("not sorted: ");

        printArray(myNumbers);

        mySortedNumbers = selectionSort(myNumbers.clone());

        System.out.println("\nselectionSort: ");

        printArray(mySortedNumbers);


    }

    public static int[] bubbleSort(int[] numbers){
        for(int i = numbers.length; i > 0; i--){
            for(int j = 0; j < i - 1; j++){
                if(numbers[j] > numbers[j + 1]){
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        return numbers;
    }

    public static int[] selectionSort(int[] numbers){
        int minPos;
        for(int i = 0; i < numbers.length - 1; i++){
            minPos = i;
            for(int j = i + 1; j < numbers.length; j++){

                if(numbers[j] < numbers[minPos]){
                    minPos = j;
                }

                int temp = numbers[i];
                numbers[i] = numbers[minPos];
                numbers[minPos] = temp;
            }
        }

        return numbers;
    }



    public static void printArray(int[] numbers){
        for(int i: numbers){
            System.out.print(i + ", ");
        }
    }
}
