package com.michalplachta.hunting.uva.s164;

import java.util.Scanner;

/**
 * UVA 164 - String Computer
 * 
 * http://uva.onlinejudge.org/external/1/164.html
 * 
 * @category string, dynamic-programming, edit-distance, alignment
 * @author micio
 */
class Main {
    
    public static class StringAlignment {
        private char[] A;
        private char[] B;
        private int[][] dp;
        private final int match;
        private final int mismatch;
        private final int deletion; // represented as '\u0000' in B
        private final int insertion; // represented as '\u0000' in A
        
        public StringAlignment(int matchScore, int mismatchScore, int deletionScore, int insertionScore) {
            match = matchScore;
            mismatch = mismatchScore;
            deletion = deletionScore;
            insertion = insertionScore;
        }
        
        public void solve(String a, String b) {
            // TODO
        }
        
        public int getScore() {
            return dp[A.length - 1][B.length - 1];
        }
    }

    public static void main(String[] args) {
        
    }
}
