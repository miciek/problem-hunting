package com.michalplachta.hunting.uva.s11151;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * UVA 11151 - Longest Palindrome http://uva.onlinejudge.org/external/111/11151.html 
 * 
 * This solution searches for biggest possible palindrome (deletions possible).
 * 
 * @category string, longest-palindrome, dynamic-programming
 * @author micio
 */
class Main {
    private int[][] sublengths;
    
    public Main(int maxLength) {
        sublengths = new int[maxLength][];
        for (int i = 0; i < maxLength; i++) {
            sublengths[i] = new int[maxLength];
            sublengths[i][i] = 1; // size = 1
        }
    }
    
    public int getLongestPalindromeLength(String s) {
        if (s.length() == 0) {
            return 0;
        }
        
        // size = 2
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                sublengths[i][i + 1] = 2;
            } else {
                sublengths[i][i + 1] = 1;
            }
        }
        
        for (int size = 3; size <= s.length(); size++) {
            for (int i = 0; i < s.length() - size + 1; i++) {
                int j = i + size - 1;
                int option1 = sublengths[i + 1][j - 1];
                if (s.charAt(i) == s.charAt(j)) {
                    option1 += 2;
                }
                
                int option2 = sublengths[i][j - 1];
                int option3 = sublengths[i + 1][j];
                
                sublengths[i][j] = Math.max(option1, Math.max(option2, option3));
            }
        }
        
        return sublengths[0][s.length() - 1];
    }
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main m = new Main(1000);
        
        int T = in.nextInt();
        in.nextLine();
        while (T-- > 0) {
            String a = in.nextLine();
            bw.write(m.getLongestPalindromeLength(a) + "\n");
        }
        
        bw.flush();
        in.close();
    }
}
