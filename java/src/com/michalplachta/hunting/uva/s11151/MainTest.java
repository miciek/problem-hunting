package com.michalplachta.hunting.uva.s11151;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MainTest {    
    private Main m = new Main(1000);
    
    
    @Test
    public void example() {
        assertEquals(3, m.getLongestPalindromeLength("ADAM"));
        assertEquals(5, m.getLongestPalindromeLength("MADAM"));
    }    
    
    @Test
    public void empty() {
        assertEquals(0, m.getLongestPalindromeLength(""));
    }   
    
    @Test
    public void oneletter() {
        assertEquals(1, m.getLongestPalindromeLength("V"));
    }    
    
    @Test
    public void twodifferentletters() {
        assertEquals(1, m.getLongestPalindromeLength("VA"));
    }
    
    @Test
    public void twoletters() {
        assertEquals(2, m.getLongestPalindromeLength("VV"));
    }
    
    @Test
    public void threeletters() {
        assertEquals(2, m.getLongestPalindromeLength("VVC"));
    }
    
    @Test
    public void manypalindromes() {
        assertEquals(3, m.getLongestPalindromeLength("AABBCCDDEEE"));
    }
    
    @Test
    public void manypalindromesandonebig() {
        assertEquals(8, m.getLongestPalindromeLength("EEEAABBCCDDEEE"));
    }
    
    @Test
    public void cornercase() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 1000; i++) {
            sb.append("A");
        }
        assertEquals(1000, m.getLongestPalindromeLength(sb.toString()));
    }
}
