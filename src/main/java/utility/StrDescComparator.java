package utility;

import java.util.Comparator;

public class StrDescComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        if (s1 == s2) {
            return 0;
        }
        if (s1 == null) {
            return -1;
        }
        if (s2 == null) {
            return -1;
        }
        return s2.compareTo(s1);
    }
}

