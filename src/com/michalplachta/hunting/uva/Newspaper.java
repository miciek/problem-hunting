package com.michalplachta.hunting.uva;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * UVA 11340 (Newspaper) problem solution.
 * 
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category
 * =24&page=show_problem&problem=2315
 * 
 * @author micio
 */
public class Newspaper {
    public static class Solution {
        private final int[] prices;

        public Solution(int[] prices) {
            this.prices = prices;
        }

        public int getPriceForLine(String line) {
            int sum = 0;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                sum += prices[c];
            }
            return sum;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int k = Integer.parseInt(br.readLine());

            int[] prices = new int[100000];
            while (k-- > 0) {
                String[] price = br.readLine().split(" ");
                char c = price[0].charAt(0);
                int p = Integer.parseInt(price[1]);
                prices[c] = p;
            }

            Solution s = new Solution(prices);

            long sum = 0;
            int m = Integer.parseInt(br.readLine());
            while (m-- > 0) {
                sum += s.getPriceForLine(br.readLine());
            }

            bw.write(Long.toString(sum / 100));
            long r = sum % 100;
            if (r < 10) {
                bw.write(".0");
            } else {
                bw.write(".");
            }
            bw.write(Long.toString(r));
            bw.write("$\n");
        }

        bw.flush();
    }
}
