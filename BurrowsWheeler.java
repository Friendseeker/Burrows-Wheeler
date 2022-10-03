/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray CSA = new CircularSuffixArray(s);
        for (int i = 0; i < s.length(); ++i) {
            if (CSA.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < s.length(); ++i) {
            int idx = CSA.index(i) == 0 ? s.length() - 1 : CSA.index(i) - 1;
            BinaryStdOut.write(s.charAt(idx));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String lastCol = BinaryStdIn.readString();
        int n = lastCol.length();
        // use key-indexed counting to sort lastCol
        // start by computing k
        int k = 256;
        // stores cumulative frequency of letters
        // more specifically cnt[c] stores number of letters before c
        int[] cnt = new int[k + 1];

        char[] firstCol = new char[n];
        int[] next = new int[n];
        // computes individual frequency of letters
        for (int i = 0; i < n; ++i) {
            ++cnt[lastCol.charAt(i) + 1];
        }
        // converts individual frequency to cumulative frequency
        for (int i = 1; i <= k; ++i) {
            cnt[i] += cnt[i - 1];
        }
        // construct sorted array from frequency
        // and, using the fact that we know where the letter is
        // in both the sorted & unsorted array, next[] can be initialized
        for (int i = 0; i < n; ++i) {
            firstCol[cnt[lastCol.charAt(i)]] = lastCol.charAt(i);
            next[cnt[lastCol.charAt(i)]] = i;
            ++cnt[lastCol.charAt(i)];
        }

        int r = first;
        for (int i = 0; i < n; ++i) {
            BinaryStdOut.write(firstCol[r]);
            r = next[r];
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        else {
            inverseTransform();
        }
    }

}
