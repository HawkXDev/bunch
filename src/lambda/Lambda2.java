package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * If we use only one comparator, we don't need to create a separate variable for it.
 * Just create an object of an anonymous class right at the moment of sort() method call
 */
public class Lambda2 {
    public static void main(String[] args) {
        String[] array1 = {"mom", "washed", "frame"};
        String[] array2 = {"I", "love", "java", "very", "much"};
        String[] array3 = {"world", "work", "May"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        arrays.sort(new Comparator<>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o2.length - o1.length;
            }
        });
        println(arrays);
    }

    private static void println(List<String[]> arrays) {
        for (String[] array : arrays) {
            System.out.println(Arrays.toString(array));
        }
    }
}
