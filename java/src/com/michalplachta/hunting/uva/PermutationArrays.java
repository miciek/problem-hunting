package com.michalplachta.hunting.uva;

import java.util.Scanner;

/**
 * UVA 482 (Permuation Arrays) problem solution.
 * 
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=
 * show_problem&category=24&problem=423
 * 
 * @author micio
 */
public class PermutationArrays {
    public static class Solution {
        private String[] result;

        public void solve(int[] ids, String[] tokens) {
            result = new String[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                result[ids[i] - 1] = tokens[i];
            }
        }

        public void printSolution() {
            for (String s : result) {
                System.out.println(s);
            }
        }

        public String[] getResult() {
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        while (n-- > 0) {
            String ints = null;
            do {
                ints = in.nextLine();
            } while (ints.isEmpty());

            int[] ids = mapStringsToInts(ints.split(" "));
            String[] tokens = in.nextLine().split(" ");
            Solution pa = new Solution();
            pa.solve(ids, tokens);
            pa.printSolution();
            if (n > 0) {
                System.out.println();
            }
        }

        in.close();
    }

    private static int[] mapStringsToInts(String[] strings) {
        int[] result = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }

        return result;
    }
}
