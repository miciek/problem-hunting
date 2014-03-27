package com.michalplachta.hunting.uva.a10258;

import static org.junit.Assert.*;
import org.junit.Test;

public class MainTest {
    @Test
    public void example() {
        Main m = new Main();
        
        m.addEntry(3, 1, 11, 'C');
        m.addEntry(1, 2, 10, 'I');
        m.addEntry(3, 1, 11, 'C');
        m.addEntry(1, 2, 19, 'R');
        m.addEntry(1, 2, 21, 'C');
        m.addEntry(1, 1, 25, 'C');
        
        assertArrayEquals(new String[] {"1 2 66", "3 1 11"}, m.getRankingTestable());
    }
    
    @Test
    public void singleContestantWithSingleCorrectSubmission() {
        Main m = new Main();
        
        m.addEntry(3, 1, 11, 'C');        
        
        assertArrayEquals(new String[] {"3 1 11"}, m.getRankingTestable());
    }
    
    @Test
    public void singleContestantWithSingleIncorrectSubmission() {
        Main m = new Main();
        
        m.addEntry(3, 1, 11, 'I');        
        
        assertArrayEquals(new String[] {"3 0 0"}, m.getRankingTestable());
    }
    
    @Test
    public void singleContestantWith2CorrectSubmissionsAfterSomeTries() {
        Main m = new Main();
        
        m.addEntry(3, 1, 2, 'I'); 
        m.addEntry(3, 1, 11, 'I');
        m.addEntry(3, 2, 15, 'I');
        m.addEntry(3, 1, 25, 'C');
        
        assertArrayEquals(new String[] {"3 1 65"}, m.getRankingTestable());
        
        m.addEntry(3, 2, 30, 'C');
        assertArrayEquals(new String[] {"3 2 115"}, m.getRankingTestable());
    }
    
    @Test
    public void fiveContestantsWithSingleCorrectSolutions() {
        Main m = new Main();
        
        m.addEntry(1, 8, 1, 'C');
        m.addEntry(2, 6, 5, 'C');
        m.addEntry(3, 8, 3, 'C');
        m.addEntry(4, 7, 17, 'C');
        m.addEntry(5, 7, 10, 'C');
        
        assertArrayEquals(new String[] {"1 1 1", "3 1 3", "2 1 5", "5 1 10", "4 1 17"}, m.getRankingTestable());
    }
    
    @Test
    public void fourContestantsWithSomeVariousSubmissions() {
        Main m = new Main();

        // these should not count:
        m.addEntry(1, 7, 2, 'R');
        m.addEntry(2, 9, 4, 'U');
        m.addEntry(3, 9, 6, 'E');
        
        // these should count:
        m.addEntry(1, 8, 1, 'C');
        m.addEntry(2, 6, 5, 'C');
        m.addEntry(3, 8, 3, 'C');
        m.addEntry(3, 7, 17, 'C');
        m.addEntry(2, 7, 10, 'C');
        
        // these should not count:
        m.addEntry(3, 7, 29, 'I');
        m.addEntry(2, 9, 30, 'I');
        m.addEntry(1, 9, 31, 'R');
        
        assertArrayEquals(new String[] {"2 2 15", "3 2 20", "1 1 1"}, m.getRankingTestable());
        
        // this should add another contestant to the ranking
        m.addEntry(4, 9, 41, 'R');
        
        assertArrayEquals(new String[] {"2 2 15", "3 2 20", "1 1 1", "4 0 0"}, m.getRankingTestable());
    }
}
