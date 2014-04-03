package com.michalplachta.hunting.uva.a10901;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {    
    @Test
    public void example() {
        Main m = new Main(2, 10);
        m.addNextCar(0, "left");
        m.addNextCar(10, "left");
        m.addNextCar(20, "left");
        m.addNextCar(30, "left");
        m.addNextCar(40, "left");
        m.addNextCar(50, "left");
        m.addNextCar(60, "left");
        m.addNextCar(70, "left");
        m.addNextCar(80, "left");
        m.addNextCar(90, "left");
        Assert.assertEquals("[10, 30, 30, 50, 50, 70, 70, 90, 90, 110]", m.simulateFerry()
                .toString());
        
        m = new Main(2, 10);
        m.addNextCar(10, "right");
        m.addNextCar(25, "left");
        m.addNextCar(40, "left");
        Assert.assertEquals("[30, 40, 60]", m.simulateFerry().toString());
    }
    
    @Test
    public void simple() {
        Main m = new Main(4, 3);
        m.addNextCar(76, "left");
        m.addNextCar(83, "left");
        m.addNextCar(97, "left");
        
        Assert.assertEquals("[79, 89, 103]", m.simulateFerry().toString());
    }
        
    @Test
    public void simple2() {
        Main m = new Main(1, 10);
        m.addNextCar(0, "left");
        m.addNextCar(10, "right");
        m.addNextCar(30, "left");
        
        Assert.assertEquals("[10, 20, 40]", m.simulateFerry().toString());
    }
    
    @Test
    public void unorderedCase() {
        Main m = new Main(2, 24);        
        m.addNextCar(2, "right");
        m.addNextCar(7, "left");
        m.addNextCar(12, "right");
        m.addNextCar(22, "right");
        m.addNextCar(34, "right");
        m.addNextCar(73, "right");
        m.addNextCar(76, "right");
        
        Assert.assertEquals("[50, 74, 50, 98, 98, 146, 146]", m.simulateFerry().toString());
    }
    
    @Test
    public void equalArrivalTimeOnOneQueue() {
        Main m = new Main(3, 5);        
        m.addNextCar(0, "left");
        m.addNextCar(1, "left");
        m.addNextCar(2, "left");
        m.addNextCar(3, "left");
        m.addNextCar(5, "left");
        m.addNextCar(5, "right");
        m.addNextCar(11, "right");
        
        Assert.assertEquals("[5, 15, 15, 15, 25, 10, 20]", m.simulateFerry().toString());
    }
    
    @Test
    public void equalArrivalTimeGlobally() {
        Main m = new Main(1, 5);        
        m.addNextCar(0, "left");
        m.addNextCar(0, "left");
        
        Assert.assertEquals("[5, 15]", m.simulateFerry().toString());
    }
    
    @Test
    public void cornerCase() {
        Main m = new Main(3, 2);        
        for(int i = 0; i < 100000; i++) {
            m.addNextCar(i + 3, "left");
            m.addNextCar(i, "right");
        }
        
        Assert.assertEquals(200000, m.simulateFerry().size());
    }
}
