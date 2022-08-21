import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Integer;

public class IntegerSorter<T> implements Sortable<T> {
    @Override
    public List sort(ArrayList<T> list) {
        int[] array = new int[list.size()];
        try {
            for (int i = 0; i < list.size(); i++) {
                array[i] = Integer.parseInt(String.valueOf(list.get(i)));
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect type of data.");
            System.out.println(e.getStackTrace());
        }

        int[] buffer1 = Arrays.copyOf(array, array.length);
        int[] buffer2 = new int[array.length];


        return Arrays.asList(array);
    }

//    public static int[] mergeSort(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
//        if (startIndex >= endIndex - 1) {
//            return buffer1;
//        }
//
//        int middle = startIndex + (endIndex - startIndex) / 2;
//
//    }
}
