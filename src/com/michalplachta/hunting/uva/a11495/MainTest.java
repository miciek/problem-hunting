package com.michalplachta.hunting.uva.a11495;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {
    @Test
    public void example() {
        assertEquals("Marcelo", getMainFor(1, 5, 3, 4, 2).whoWins());
        assertEquals("Carlos", getMainFor(5, 1, 3, 4, 2).whoWins());
        assertEquals("Carlos", getMainFor(1, 2, 3, 4, 5).whoWins());
        assertEquals("Carlos", getMainFor(3, 5, 2, 1, 4, 6).whoWins());
        assertEquals("Carlos", getMainFor(5, 4, 3, 2, 1).whoWins());
        assertEquals("Marcelo", getMainFor(6, 5, 4, 3, 2, 1).whoWins());
    }

    @Test
    public void sorted() {
        assertEquals("Carlos", getMainFor(1, 2, 3, 4, 5).whoWins());
    }

    @Test
    public void oneMove() {
        assertEquals("Marcelo", getMainFor(1, 2, 3, 5, 4).whoWins());
    }

    @Test
    public void cornerCase() {
        Main m = new Main();
        for (int i = 100000; i > 0; i--) {
            m.add(i);
        }
        assertEquals("Carlos", m.whoWins());
    }

    private Main getMainFor(int... array) {
        Main m = new Main();
        for (int i = 0; i < array.length; i++) {
            m.add(array[i]);
        }

        return m;
    }
}
