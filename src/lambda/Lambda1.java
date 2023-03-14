package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Two kinds of comparators are used to sort the list of string arrays
 */
public class Lambda1 {
    public static void main(String[] args) {
        String[] array1 = {"mom", "washed", "frame"};
        String[] array2 = {"I", "love", "java", "very", "much"};
        String[] array3 = {"world", "work", "May"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        Comparator<String[]> sortByLength = new Comparator<>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        };

        arrays.sort(sortByLength);
        println(arrays);
        System.out.println();

        Comparator<String[]> sortByWordsLength = new Comparator<>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int length1 = 0;
                int length2 = 0;
                for (String s : o1) {
                    length1 += s.length();
                }
                for (String s : o2) {
                    length2 += s.length();
                }
                return length1 - length2;
            }
        };

        arrays.sort(sortByWordsLength);
        println(arrays);
    }

    private static void println(List<String[]> arrays) {
        for (String[] array : arrays) {
            System.out.println(Arrays.toString(array));
        }
    }
}
