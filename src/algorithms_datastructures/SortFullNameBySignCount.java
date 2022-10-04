package algorithms_datastructures;

import java.util.Comparator;

public class SortFullNameBySignCount implements Comparator<FullName> {
    @Override
    public int compare(FullName o1, FullName o2) {
        int signCountO1 = o1.toString().length();
        int signCountO2 = o2.toString().length();



        return signCountO1 - signCountO2;
    }
}
