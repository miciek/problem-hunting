package com.michalplachta.hunting.uva.cs10360;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {
    @Test
    public void example() {
        Main m = new Main(1);

        m.setPopulation(4, 4, 10);
        m.setPopulation(6, 6, 20);

        m.solve();
        assertEquals("5 5 30", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void min() {
        Main m = new Main(1);

        m.setPopulation(1, 1, 1);

        m.solve();
        assertEquals("0 0 1", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void trivial() {
        Main m = new Main(1);

        m.setPopulation(0, 0, 1);
        m.setPopulation(2, 2, 1);

        m.solve();
        assertEquals("1 1 2", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void simple1() {
        Main m = new Main(2);

        m.setPopulation(3, 3, 7);
        m.setPopulation(3, 4, 6);
        m.setPopulation(4, 9, 12);
        m.setPopulation(4, 6, 8);

        m.solve();
        assertEquals("2 4 21", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void simple2() {
        Main m = new Main(2);

        m.setPopulation(3, 3, 7);
        m.setPopulation(3, 4, 6);
        m.setPopulation(4, 9, 12);
        m.setPopulation(6, 6, 8);

        m.solve();
        assertEquals("4 4 21", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void complex1() {
        Main m = new Main(9);

        m.setPopulation(23, 128, 80);
        m.setPopulation(783, 768, 88);
        m.setPopulation(347, 474, 198);
        m.setPopulation(551, 894, 188);
        m.setPopulation(1015, 684, 195);
        m.setPopulation(534, 882, 199);
        m.setPopulation(513, 1, 198);
        m.setPopulation(430, 558, 207);
        m.setPopulation(589, 741, 188);
        m.setPopulation(349, 423, 82);
        m.setPopulation(684, 447, 107);
        m.setPopulation(414, 207, 101);

        m.solve();
        assertEquals("542 885 387", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void complex2() {
        Main m = new Main(46);

        m.setPopulation(527, 580, 214);
        m.setPopulation(93, 298, 146);
        m.setPopulation(444, 722, 99);
        m.setPopulation(103, 146, 206);
        m.setPopulation(517, 353, 178);
        m.setPopulation(327, 912, 139);
        m.setPopulation(738, 994, 92);
        m.setPopulation(115, 44, 155);
        m.setPopulation(569, 653, 26);
        m.setPopulation(828, 750, 230);
        m.setPopulation(550, 254, 102);
        m.setPopulation(620, 347, 177);
        m.setPopulation(936, 791, 134);

        m.solve();
        assertEquals("523 607 240", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void big() {
        Main m = new Main(50);

        m.setPopulation(0, 0, 1000);
        m.setPopulation(1024, 1024, 100000);
        m.setPopulation(0, 1024, 10000);
        m.setPopulation(1024, 0, 10000);

        m.solve();
        assertEquals("974 974 100000", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void max() {
        Main m = new Main(50);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 1000; j++) {
                m.setPopulation(i, j, 255);
            }
        }

        m.solve();
        assertEquals("0 50 515100", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }

    @Test
    public void maxqty() {
        Main m = new Main(50);

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                m.setPopulation(i, j, 255);
            }
        }

        m.solve();
        assertEquals("49 49 2550000", String.format("%d %d %d", m.best_x, m.best_y, m.most_killed));
    }
}
