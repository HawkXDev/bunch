package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Replace the comparator object with a lambda function
 */
public class Lambda3 {
    public static void main(String[] args) {
        String[] array1 = {"mom", "washed", "frame"};
        String[] array2 = {"I", "love", "java", "very", "much"};
        String[] array3 = {"world", "work", "May"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        arrays.sort((o1, o2) -> o2.length - o1.length);
        println(arrays);
    }

    private static void println(List<String[]> arrays) {
        for (String[] array : arrays) {
            System.out.println(Arrays.toString(array));
        }
    }
}
