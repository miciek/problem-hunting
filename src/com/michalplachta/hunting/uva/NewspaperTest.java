package com.michalplachta.hunting.uva;

import static org.junit.Assert.assertEquals;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

public class NewspaperTest {

    @Test
    public void example() {
        Hashtable<Character, Integer> p = new Hashtable<Character, Integer>();
        p.put('a', 3);
        p.put('W', 10);
        p.put('A', 100);
        p.put(',', 10);
        p.put('k', 7);
        p.put('.', 3);
        p.put('I', 13);

        String[] lines = new String[] { "ACM International Collegiate Programming Contest (abbreviated",
                "as ACM-ICPC or just ICPC) is an annual multi-tiered competition",
                "among the universities of the world. The ICPC challenges students",
                "to set ever higher standards of excellence for themselves",
                "through competition that rewards team work, problem analysis,", "and rapid software development.",
                "From Wikipedia." };

        assertEquals(374, getSum(p, lines));
    }

    @Test
    public void small() {
        Hashtable<Character, Integer> p = new Hashtable<Character, Integer>();
        p.put('a', 3);

        String[] lines = new String[] { "abc", "a" };

        assertEquals(6, getSum(p, lines));
    }

    @Test
    public void bigsum() {
        Hashtable<Character, Integer> p = new Hashtable<Character, Integer>();
        p.put('a', 3000000);
        p.put('b', 1);
        p.put('c', 2);

        String[] lines = new String[] { "abc", "a" };

        assertEquals(6000003, getSum(p, lines));
    }

    private long getSum(Map<Character, Integer> prices, String[] lines) {
        int[] prices_opt = new int[1000];
        for (Character c : prices.keySet()) {
            prices_opt[c] = prices.get(c);
        }
        Newspaper.Solution s = new Newspaper.Solution(prices_opt);
        long sum = 0;
        for (String line : lines) {
            sum += s.getPriceForLine(line);
        }
        return sum;
    }
}
