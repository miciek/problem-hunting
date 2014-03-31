package com.michalplachta.hunting.uva.s422;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {
    @Test
    public void example() {
        Main m = new Main(5);
        m.addRow("EDEEE");
        m.addRow("DISKE");
        m.addRow("ESEEE");
        m.addRow("ECEEE");
        m.addRow("EEEEE");
        assertEquals("1,2 4,2", m.find("DISC").toString());
        assertEquals("2,1 2,4", m.find("DISK").toString());
        assertEquals("Not found", m.find("DISP").toString());
    }

    @Test
    public void simple2d() {
        Main m = new Main(2);
        m.addRow("AB");
        m.addRow("CD");
        assertEquals("1,1 1,2", m.find("AB").toString());
        assertEquals("1,2 1,1", m.find("BA").toString());
        assertEquals("2,1 2,2", m.find("CD").toString());
        assertEquals("2,2 2,1", m.find("DC").toString());
        assertEquals("1,1 2,2", m.find("AD").toString());
        assertEquals("2,2 1,1", m.find("DA").toString());
        assertEquals("1,1 2,1", m.find("AC").toString());
        assertEquals("2,1 1,1", m.find("CA").toString());
        assertEquals("1,2 2,2", m.find("BD").toString());
        assertEquals("2,2 1,2", m.find("DB").toString());
        assertEquals("2,1 1,2", m.find("CB").toString());
        assertEquals("1,2 2,1", m.find("BC").toString());
        assertEquals("Not found", m.find("AA").toString());
        assertEquals("Not found", m.find("BB").toString());
        assertEquals("Not found", m.find("CC").toString());
        assertEquals("Not found", m.find("DD").toString());
    }

}
