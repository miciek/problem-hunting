package com.michalplachta.hunting.uva.s164;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * UVA 164 - String Computer http://uva.onlinejudge.org/external/1/164.html
 * @category string, dynamic-programming, edit-distance, string-alignment
 * @author micio
 */
class Main {
    private final StringAlignment sa = new StringAlignment(20, 2, -1, -1);
    private Stack<String> instructions = new Stack<String>();
    private List<String> result = new LinkedList<String>();
    char[] temp = new char[2];
    
    public static void main(String[] args) throws IOException {
        Main m = new Main();
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;
        while (!(line = in.nextLine()).equals("#")) {
            String[] input = line.split(" ");
            List<String> result = m.getInstructionsForStrings(input[0], input[1]);
            for (String s: result) {
                bw.write(s);
            }
            bw.newLine();
        }
        bw.flush();
        in.close();
    }
    
    public List<String> getInstructionsForStrings(String from, String to) {
        sa.solve(from, to);        
                
        int i = from.length() - 1;
        int j = to.length() - 1;
        while (i >= 0 || j >= 0) {
            byte source = sa.getScoreSource(i, j);
            if (source == StringAlignment.INSERTION) {
                temp[0] = 'I';
                temp[1] = to.charAt(j);
                instructions.add(new String(temp));
                j--;
            } else if (source == StringAlignment.DELETION) {
                temp[0] = 'D';
                temp[1] = from.charAt(i);
                instructions.add(new String(temp));                
                i--;
            } else {
                if (from.charAt(i) != to.charAt(j)) {
                    temp[0] = 'C';
                    temp[1] = to.charAt(j);
                    instructions.add(new String(temp));
                } else {                    
                    instructions.add("L");
                }
                
                i--;
                j--;
            }
        }        
        
        int index = 1;
        result.clear();
        while (!instructions.isEmpty()) {
            String item = instructions.pop();
            char op = item.charAt(0);
            if (op != 'L') {
                result.add(String.format("%s%02d", item, index));
            }
            
            if (op != 'D') { // after deletion, index stays the same 
                index++;
            }
        }
        
        result.add("E");
        
        return result;
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
