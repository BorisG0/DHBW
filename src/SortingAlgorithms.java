import java.util.ArrayList;

public class SortingAlgorithms {
    public static void main(String[] args) {
        ArrayList<Integer> myNumbers = new ArrayList<>();

        myNumbers.add(70);
        myNumbers.add(50);
        myNumbers.add(30);
        myNumbers.add(100);
        myNumbers.add(90);
        myNumbers.add(80);
        myNumbers.add(60);
        myNumbers.add(40);
        myNumbers.add(20);
        myNumbers.add(10);

        for(int i: myNumbers){
            System.out.print(i + ", ");
        }

        System.out.println("\nSorting list");
        bubbleSort(myNumbers);

        for(int i: myNumbers){
            System.out.print(i + ", ");
        }


    }

    public static void bubbleSort(ArrayList<Integer> list){
        for(int i = list.size() - 1; i >= 0; i--){
            for(int j = 0; j < i; j++){
                if(list.get(j) > list.get(j + 1)){
                    int bigger = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, bigger);
                }
            }
        }
    }
}
