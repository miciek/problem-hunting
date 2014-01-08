package com.michalplachta.hunting.uva;

import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

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
        private final Map<Character, Integer> prices;

        public Solution(Map<Character, Integer> prices) {
            this.prices = prices;
        }

        public int getPriceForLine(String line) {
            int sum = 0;
            for (int i = 0; i < line.length(); i++) {
                Character c = line.charAt(i);
                if (prices.containsKey(c)) {
                    sum += prices.get(c);
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        while (n-- > 0) {
            int k = in.nextInt();
            in.nextLine();

            Hashtable<Character, Integer> prices = new Hashtable<Character, Integer>(k);
            while (k-- > 0) {
                String[] price = in.nextLine().trim().split(" ");
                char c = price[0].charAt(0);
                int p = Integer.parseInt(price[1]);
                prices.put(c, p);
            }

            Solution s = new Solution(prices);

            long sum = 0;
            int m = in.nextInt();
            in.nextLine();
            while (m-- > 0) {
                sum += s.getPriceForLine(in.nextLine());
            }

            printResult(sum);
        }

        in.close();
    }

    private static void printResult(long sum) {
        System.out.format("%d.%02d$\n", sum / 100, sum % 100);
    }
}
