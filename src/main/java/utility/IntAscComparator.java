package utility;

import java.util.Comparator;

public class IntAscComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer t1, Integer t2) {
        return Integer.compare(t1, t2);
    }

    public static void main(String[] args) {
        IntAscComparator comparator = new IntAscComparator();
        System.out.println(comparator.compare(1, 2));
    }
}
