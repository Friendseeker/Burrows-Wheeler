/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    // O(nR) worst case, O(R) typical case
    public static void encode() {
        char[] positions = new char[R];
        for (int i = 0; i < R; ++i) {
            positions[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(positions[c]);
            int progress = 0; // optimization: track how many times I have incremented
            for (int i = 0; progress < positions[c]; ++i) {
                if (positions[i] < positions[c]) {
                    ++positions[i];
                    ++progress;
                }
            }
            positions[c] = 0;
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] letters = new char[R];
        for (int i = 0; i < R; ++i) {
            letters[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            int pos = BinaryStdIn.readChar();
            char currLetter = letters[pos];
            BinaryStdOut.write(currLetter);
            for (int i = pos; i >= 1; --i) {
                letters[i] = letters[i - 1];
            }
            letters[0] = currLetter;
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        }
        else {
            decode();
        }
    }
}
