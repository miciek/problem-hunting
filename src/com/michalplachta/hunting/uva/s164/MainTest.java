package com.michalplachta.hunting.uva.s164;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.michalplachta.hunting.uva.s164.Main.StringAlignment;

public class MainTest {
    Main m = new Main();
    
    @Test
    public void example() {       
        assertEquals("[Da01, Cg03, If04, E]", m.getInstructionsForStrings("abcde", "bcgfe").toString());
    }
    
    @Test
    public void nochange() {       
        assertEquals("[E]", m.getInstructionsForStrings("abcde", "abcde").toString());
    }
    
    @Test
    public void oneinsertion() {       
        assertEquals("[Id04, E]", m.getInstructionsForStrings("abc", "abcd").toString());
    }
    
    @Test
    public void onedeletion() {       
        assertEquals("[Db02, E]", m.getInstructionsForStrings("abc", "ac").toString());
    }
    
    @Test
    public void onechange() {       
        assertEquals("[Cd02, E]", m.getInstructionsForStrings("abc", "adc").toString());
    }
    
    @Test
    public void zerotohero() {       
        assertEquals("[Ia01, Ib02, Ic03, E]", m.getInstructionsForStrings("", "abc").toString());
    }
    
    @Test
    public void overhaul() {       
        assertEquals("[Cd01, Ce02, Cf03, E]", m.getInstructionsForStrings("abc", "def").toString());
    }
    
    @Test
    public void cornercase() {
        assertEquals("[Cx20, E]", m.getInstructionsForStrings("abcdefghijklmnopqrst", "abcdefghijklmnopqrsx").toString());
    }
}
