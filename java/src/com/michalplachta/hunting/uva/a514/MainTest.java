package com.michalplachta.hunting.uva.a514;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    @Test
    public void example() {
        testYes(1, 2, 3, 4, 5);
        testNo(5, 4, 1, 2, 3);

        testYes(6, 5, 4, 3, 2, 1);
    }

    @Test
    public void simple() {
        testYes(1);
        testNo(5, 1, 2, 3, 4);
    }

    @Test
    public void reversed() {
        testYes(6, 5, 4, 3, 2, 1);
    }

    @Test
    public void cornerCaseOK() {
        Main m = new Main(1000);
        boolean result = true;
        for (int i = 1000; i > 0; i--) {
            result = m.add(i);
        }

        Assert.assertTrue(result);
    }

    @Test
    public void cornerCaseOK2() {
        Main m = new Main(1000);
        boolean result = true;
        for (int i = 1; i <= 1000; i++) {
            result = m.add(i);
        }

        Assert.assertTrue(result);
    }

    @Test
    public void cornerCaseWrong() {
        Main m = new Main(1000);
        boolean result = true;
        m.add(1000);
        for (int i = 1; i <= 999; i++) {
            result = m.add(i);
        }

        Assert.assertFalse(result);
    }

    private static boolean test(int... cars) {
        Main m = new Main(cars.length);
        boolean result = true;
        for (int car : cars) {
            result = m.add(car);
        }

        return result;
    }

    private static void testYes(int... cars) {
        Assert.assertTrue(test(cars));
    }

    private static void testNo(int... cars) {
        Assert.assertFalse(test(cars));
    }
}
