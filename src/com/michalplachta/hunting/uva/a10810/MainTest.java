package com.michalplachta.hunting.uva.a10810;

import static org.junit.Assert.*;
import org.junit.Test;

public class MainTest {
    @Test
    public void example() {
        Main m = new Main();
        
        m.add(9);
        m.add(1);
        m.add(0);
        m.add(5);
        m.add(4);        
        assertEquals(6, m.getMinSwaps());
        
        m.reset();
        m.add(1);
        m.add(2);
        m.add(3);                        
        assertEquals(0, m.getMinSwaps());
    }
    
    @Test
    public void reverseSorted() {
        Main m = new Main();
        m.add(3);
        m.add(2);
        m.add(1);
        assertEquals(3, m.getMinSwaps());
    }
    
    @Test
    public void oneElement() {
        Main m = new Main();        
        m.add(1);
        assertEquals(0, m.getMinSwaps());
    }    
    
    @Test
    public void oneSwap() {
        Main m = new Main();        
        m.add(1);
        m.add(2);
        m.add(3);
        m.add(5);
        m.add(4);
        m.add(6);
        assertEquals(1, m.getMinSwaps());
    }    
    
    @Test
    public void biggestAtBeginning() {
        Main m = new Main();        
        m.add(6);
        m.add(1);
        m.add(2);
        m.add(3);        
        m.add(4);
        m.add(5);        
        assertEquals(5, m.getMinSwaps());
    }    
    
    @Test
    public void cornerCase() {
        Main m = new Main();
        m.add(999999999);
        for(int i = 2; i < 500000; i++) {
            m.add(i);           
        }
        assertEquals(499998, m.getMinSwaps());
    }    
}
