package com.michalplachta.hunting.uva.s10192;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MainTest {
    Main m = new Main();
    
    @Test
    public void example() {
        assertEquals(3, m.getLongestCommonSubsequenceLength("abcd", "acdb"));
        assertEquals(2, m.getLongestCommonSubsequenceLength("abcd", "dacb"));
    }    
}
