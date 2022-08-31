package utility;

import java.util.Comparator;

public class IntDescComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer t1, Integer t2) {
        return Integer.compare(t2, t1);
    }


    public static void main(String[] args) {
        IntDescComparator comparator = new IntDescComparator();
        System.out.println(comparator.compare(2, 3));
    }
}
