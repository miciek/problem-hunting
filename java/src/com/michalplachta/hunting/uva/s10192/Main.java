package com.michalplachta.hunting.uva.s10192;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * UVA 10192 - Vacation http://uva.onlinejudge.org/external/101/10192.html
 * 
 * @category string, longest-common-subsequence, dynamic-programming, string-alignment
 * @author micio
 */
class Main {
    private final StringAlignment sa = new StringAlignment(100, 1, -2100000000, 0);
        
    public int getLongestCommonSubsequenceLength(String a, String b) {
        sa.solve(a, b);
        return sa.getScore();
    }
    
    public static void main(String[] args) throws IOException {
        Main m = new Main();
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int i = 1;
        String a;
        while (!(a = in.nextLine()).startsWith("#")) {
            String b = in.nextLine();
            bw.write(String.format("Case #%d: you can visit at most %d cities.\n", i++, m.getLongestCommonSubsequenceLength(a, b)));
        }
        
        bw.flush();
        in.close();
    }
           
    public static class StringAlignment {
        public final static byte NONE = 0;
        public final static byte COMPARISON = 1;
        public final static byte DELETION = 2; // = insert space to B (or remove character from A)
        public final static byte INSERTION = 3; // = insert space to A (or insert character from B to A)
        private String A;
        private String B;
        private int[][] dp;
        private byte[][] dp_backtrack;
        private final int match;
        private final int mismatch;
        private final int modification;
        
        public StringAlignment(int maxStringSize, int matchScore, int mismatchScore, int modificationScore) {
            match = matchScore;
            mismatch = mismatchScore;
            modification = modificationScore; // modification = DELETION or INSERTION
            
            dp = new int[maxStringSize + 1][];
            dp_backtrack = new byte[maxStringSize + 1][];
            for(int i = 0; i < maxStringSize + 1; i++) {
                dp[i] = new int[maxStringSize + 1];
                dp_backtrack[i] = new byte[maxStringSize + 1];
            }
        }
        
        public void solve(String a, String b) {
            A = a;
            B = b;
            for (int i = 0; i <= A.length(); i++) {
                for (int j = 0; j <= B.length(); j++) {
                    dp[i][j] = calculateScoreFor(i, j);
                }
            }
        }
        
        public byte getScoreSource(int i, int j) {
            return dp_backtrack[i + 1][j + 1];
        }

        public int getScore() {
            return dp[A.length()][B.length()];
        }
        
        private int calculateScoreFor(int i, int j) {
            if (i == 0 && j == 0) {
                dp_backtrack[i][j] = NONE;
                return 0; // score of aligning two empty strings
            }
            
            if (i == 0) {
                dp_backtrack[i][j] = INSERTION;
                return dp[i][j - 1] + modification; // score of space insertion to A
            }
            
            if (j == 0) {
                dp_backtrack[i][j] = DELETION;
                return dp[i - 1][j] + modification; // score of char deletion from A (= space insertion to B)
            }
            
            int addedSpaceToAscore = dp[i][j - 1] + modification;
            int removedLetterFromAscore = dp[i - 1][j] + modification;
            int comparedScore = dp[i - 1][j - 1] + ((A.charAt(i - 1) == B.charAt(j - 1)) ? match : mismatch);
            
            if (comparedScore > removedLetterFromAscore) {
                if (comparedScore > addedSpaceToAscore) {
                    dp_backtrack[i][j] = COMPARISON;
                    return comparedScore;
                }
            } else if (removedLetterFromAscore > addedSpaceToAscore) {
                dp_backtrack[i][j] = DELETION;
                return removedLetterFromAscore;
            }
            
            dp_backtrack[i][j] = INSERTION;
            return addedSpaceToAscore;
        }
    }
}
