package com.michalplachta.hunting.uva;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class PermutationArraysTest {
    private PermutationArrays.Solution pa;

    @Before
    public void setup() {
        pa = new PermutationArrays.Solution();
    }

    @Test
    public void example() {
        pa.solve(new int[] { 3, 1, 2 }, new String[] { "32.0", "54.7", "-2" });
        assertArrayEquals(new String[] { "54.7", "-2", "32.0" }, pa.getResult());
    }

    @Test
    public void small() {
        pa.solve(new int[] { 1 }, new String[] { "-1.1111" });
        assertArrayEquals(new String[] { "-1.1111" }, pa.getResult());
    }

    @Test
    public void nochange() {
        pa.solve(new int[] { 1, 2, 3, 4, 5 }, new String[] { "1.11", "2.22", "3.33", "4.444", "5.555" });
        assertArrayEquals(new String[] { "1.11", "2.22", "3.33", "4.444", "5.555" }, pa.getResult());
    }

    @Test
    public void reverse() {
        pa.solve(new int[] { 5, 4, 3, 2, 1 }, new String[] { "1.11", "2.22", "3.33", "4.444", "5.555" });
        assertArrayEquals(new String[] { "5.555", "4.444", "3.33", "2.22", "1.11" }, pa.getResult());
    }
}
