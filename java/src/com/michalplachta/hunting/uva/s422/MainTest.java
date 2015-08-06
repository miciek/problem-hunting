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
        assertEquals(null, m.find("DISP"));
        assertEquals("2,3 2,3", m.find("S").toString());
        assertEquals("2,3 2,5", m.find("SKE").toString());
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
        assertEquals(null, m.find("AA"));
        assertEquals(null, m.find("BB"));
        assertEquals(null, m.find("CC"));
        assertEquals(null, m.find("DD"));
    }  
    
    @Test
    public void simple1element() {
        Main m = new Main(1);
        m.addRow("A");
        assertEquals("1,1 1,1", m.find("A").toString());        
        assertEquals(null, m.find("B"));
        assertEquals(null, m.find("AB"));
        assertEquals(null, m.find("AA"));
    }  
}
