/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class CircularSuffixArray {

    private String s;
    private int[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null!");
        }
        this.s = s;
        index = new int[s.length()];
        // initialize & sort the index
        for (int i = 0; i < index.length; ++i) {
            index[i] = i;
        }
        // JVM please optimize the boxing away
        index = Arrays.stream(index).boxed().sorted(this::compare).mapToInt(i -> i).toArray();
    }

    // compare s[a:] and s[b:]
    private int compare(Integer a, Integer b) {
        for (int i = 0; i < s.length(); ++i) {
            if (charAt(i + a) < charAt(i + b)) {
                return -1;
            }
            if (charAt(i + a) > charAt(i + b)) {
                return 1;
            }
        }
        return 0;
    }

    private char charAt(int i) {
        if (i >= s.length()) {
            i -= s.length();
        }
        return s.charAt(i);
    }

    // length of s
    public int length() {
        return s.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= index.length) {
            throw new IllegalArgumentException("out of bound access");
        }
        return index[i];
    }

    // unit testing
    public static void main(String[] args) {
        CircularSuffixArray CSA = new CircularSuffixArray(args[0]);
        for (int i = 0; i < args[0].length(); ++i) {
            System.out.printf("%d ", CSA.index(i));
        }
    }
}
