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

}
