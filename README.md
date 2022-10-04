# Burrows-Wheeler

An archiver that compresses files with mutiple processing steps, including Run-length encoding, Move-to-front transform, Huffman Encoding, and Burrows-Wheeler transform.

## Tech Stack

- Java

## How to use

The project depends on Princeton's [algs4](https://javadoc.io/doc/edu.princeton.cs/algs4/latest/edu/princeton/cs/algs4/package-summary.html) library. It can be installed from [their official website](https://algs4.cs.princeton.edu/code/).

### Compressing file

```console
java-algs4 BurrowsWheeler - < inputfile | java-algs4 MoveToFront - | java-algs4 edu.princeton.cs.algs4.Huffman - > outputfile
```

### Decompressing file

```console
java-algs4 edu.princeton.cs.algs4.Huffman + < inputfile | java-algs4 MoveToFront + | java-algs4 BurrowsWheeler + > outputfile
```

### Compression Performance

For plaintext file, the archiver achieves high compression ratio, upward of 70%.

```console
(base) foo@bar burrows % ls -l | grep aesop
-rw-rw-r--@ 1 foo  staff    191943 Mar 16  2013 aesop.txt
-rw-r--r--  1 foo  staff     66026 Oct  3 14:50 aesopcompressed

```
(Example compressing [Aesop's Fables](https://en.wikipedia.org/wiki/Aesop%27s_Fables))

However, it has mixed performance when compressing certain popular files formats such as **gif**. The archiver has low compression ratio, and occasionally the compression ratio is negative.
