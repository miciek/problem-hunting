package com.michalplachta.hunting.uva.s4388;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MainTest {
    @Test
    public void example() {
        check("1");
        check("1 2");
        check("2 1");
        check("2 3 1");
        check("3 1 4 2");
        check("3 4 5 1 2 6");
        check("5 1 7 4 3 2 6");
        check("3 10 2 12 14 5 13 4 1 8 6 11 7 9");
    }

    @Test
    public void healthCheck() {
        assertEquals("1", getSuffixArray("x"));
        assertEquals("3 1 4 2", getSuffixArray("abab"));
        assertEquals("1 2", getSuffixArray("ab"));
        assertEquals("2 1", getSuffixArray("aa"));
        assertEquals("2 3 1", getSuffixArray("bab"));
        assertEquals("3 4 5 1 2 6", getSuffixArray("suffix"));
        assertEquals("3 10 2 12 14 5 13 4 1 8 6 11 7 9", getSuffixArray("reconstruction"));
        assertEquals("5 1 7 4 3 2 6", getSuffixArray("issofun"));

        assertArrayEquals(new int[] { 44 }, getArrayFromString("44"));
        assertArrayEquals(new int[] { 1, 2, 13 }, getArrayFromString("1 2 13"));
    }

    private static void check(String sa) {
        System.out.println("TEST FOR: " + sa);
        Main m = new Main(getArrayFromString(sa));

        String gen = m.generateStringForSuffixArray();
        System.out.println("RETURNED: " + gen);

        String gen_sa = getSuffixArray(gen);
        System.out.println("ARRAY IS: " + gen_sa);

        assertEquals(sa, gen_sa);
        System.out.println();
    }

    private static int[] getArrayFromString(String s) {
        String[] splits = s.trim().split(" ");
        int[] result = new int[splits.length];
        for (int i = 0; i < splits.length; i++) {
            result[i] = Integer.parseInt(splits[i]);
        }
        return result;
    }

    private static String getSuffixArray(String s) {
        StringBuilder sb = new StringBuilder();
        SuffixArray sa = new SuffixArray(s);
        for (int i = 0; i < s.length(); i++) {
            sb.append(sa.index(i) + 1);
            if (i < s.length() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static class SuffixArray {
        private Suffix[] suffixes;

        /**
         * Initializes a suffix array for the given <tt>text</tt> string.
         * 
         * @param text
         *            the input string
         */
        public SuffixArray(String text) {
            int N = text.length();
            this.suffixes = new Suffix[N];
            for (int i = 0; i < N; i++)
                suffixes[i] = new Suffix(text, i);
            Arrays.sort(suffixes);
        }

        private static class Suffix implements Comparable<Suffix> {
            private final String text;
            private final int index;

            private Suffix(String text, int index) {
                this.text = text;
                this.index = index;
            }

            private int length() {
                return text.length() - index;
            }

            private char charAt(int i) {
                return text.charAt(index + i);
            }

            public int compareTo(Suffix that) {
                if (this == that)
                    return 0; // optimization
                int N = Math.min(this.length(), that.length());
                for (int i = 0; i < N; i++) {
                    if (this.charAt(i) < that.charAt(i))
                        return -1;
                    if (this.charAt(i) > that.charAt(i))
                        return +1;
                }
                return this.length() - that.length();
            }

            public String toString() {
                return text.substring(index);
            }
        }

        /**
         * Returns the length of the input string.
         * 
         * @return the length of the input string
         */
        public int length() {
            return suffixes.length;
        }

        /**
         * Returns the index into the original string of the <em>i</em>th
         * smallest suffix. That is, <tt>text.substring(sa.index(i))</tt> is the
         * <em>i</em>th smallest suffix.
         * 
         * @param i
         *            an integer between 0 and <em>N</em>-1
         * @return the index into the original string of the <em>i</em>th
         *         smallest suffix
         * @throws java.lang.IndexOutOfBoundsException
         *             unless 0 &le; <em>i</em> &lt; <Em>N</em>
         */
        public int index(int i) {
            if (i < 0 || i >= suffixes.length)
                throw new IndexOutOfBoundsException();
            return suffixes[i].index;
        }

        /**
         * Returns the length of the longest common prefix of the <em>i</em>th
         * smallest suffix and the <em>i</em>-1st smallest suffix.
         * 
         * @param i
         *            an integer between 1 and <em>N</em>-1
         * @return the length of the longest common prefix of the <em>i</em>th
         *         smallest suffix and the <em>i</em>-1st smallest suffix.
         * @throws java.lang.IndexOutOfBoundsException
         *             unless 1 &le; <em>i</em> &lt; <em>N</em>
         */
        public int lcp(int i) {
            if (i < 1 || i >= suffixes.length)
                throw new IndexOutOfBoundsException();
            return lcp(suffixes[i], suffixes[i - 1]);
        }

        // longest common prefix of s and t
        private static int lcp(Suffix s, Suffix t) {
            int N = Math.min(s.length(), t.length());
            for (int i = 0; i < N; i++) {
                if (s.charAt(i) != t.charAt(i))
                    return i;
            }
            return N;
        }

        /**
         * Returns the <em>i</em>th smallest suffix as a string.
         * 
         * @param i
         *            the index
         * @return the <em>i</em> smallest suffix as a string
         * @throws java.lang.IndexOutOfBoundsException
         *             unless 0 &le; <em>i</em> &lt; <Em>N</em>
         */
        public String select(int i) {
            if (i < 0 || i >= suffixes.length)
                throw new IndexOutOfBoundsException();
            return suffixes[i].toString();
        }

        /**
         * Returns the number of suffixes strictly less than the <tt>query</tt>
         * string. We note that <tt>rank(select(i))</tt> equals <tt>i</tt> for
         * each <tt>i</tt> between 0 and <em>N</em>-1.
         * 
         * @param query
         *            the query string
         * @return the number of suffixes strictly less than <tt>query</tt>
         */
        public int rank(String query) {
            int lo = 0, hi = suffixes.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = compare(query, suffixes[mid]);
                if (cmp < 0)
                    hi = mid - 1;
                else if (cmp > 0)
                    lo = mid + 1;
                else
                    return mid;
            }
            return lo;
        }

        // compare query string to suffix
        private static int compare(String query, Suffix suffix) {
            int N = Math.min(query.length(), suffix.length());
            for (int i = 0; i < N; i++) {
                if (query.charAt(i) < suffix.charAt(i))
                    return -1;
                if (query.charAt(i) > suffix.charAt(i))
                    return +1;
            }
            return query.length() - suffix.length();
        }
    }
}
