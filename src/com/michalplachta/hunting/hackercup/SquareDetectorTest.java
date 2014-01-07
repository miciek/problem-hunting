package com.michalplachta.hunting.hackercup;
import static org.junit.Assert.*;

import org.junit.Test;

public class SquareDetectorTest {
    @Test
    public void standardCaseWrong() {
        String[] board = new String[] {           
            "......",
            "......",
            "..###.",
            "..###.",
            "..#.#.",
            "......"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
    
    @Test
    public void standardCaseOK() {
        String[] board = new String[] {           
            "......",
            "......",
            "..###.",
            "..###.",
            "..###.",
            "......"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
    @Test
    public void testBorderCaseMax11() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "..................##.",
            "..................##",
            "..................##"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
    
    @Test
    public void testBorderCaseMax10() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "..................##",
            "..................##"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
    @Test
    public void testBorderCaseMax9() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "..................##"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
    
    @Test
    public void testBorderCaseMax8() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...................#"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
    @Test
    public void testBorderCaseMax7() {
        String[] board = new String[] {
            "#...................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...................."            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
    @Test
    public void testBorderCaseMax6() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...........##.......",
            "...........##.......",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...................#"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
    
    @Test
    public void testBorderCaseMax5() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...........##.......",
            "...........##.......",
            "...........##.......",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...................."            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
    
    @Test
    public void testBorderCaseMax4() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...........##.......",
            "...........##.......",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...................."            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
    @Test
    public void testBorderCaseMax3() {
        String[] board = new String[] {
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...........#........",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "....................",
            "...................."            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
    @Test
    public void testBorderCaseMax2() {
        String[] board = new String[] {
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "#################.##",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
    
    @Test
    public void testBorderCaseMax1() {
        String[] board = new String[] {
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################",
            "####################"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
    @Test
    public void testBorderCase0() {
        String[] board = new String[] {
            "#"            
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
    
	@Test
	public void testExample1() {
		String[] board = new String[] {
		    "..##",
		    "..##",
		    "....",
		    "...."
		};
		boolean result = new SquareDetectorSolution.SquareDetector(board).result;
		assertTrue(result);
	}
	
	@Test
    public void testExample2() {
        String[] board = new String[] {
            "..##",
            "..##",
            "#...",
            "...."
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
	
	@Test
    public void testExample3() {
        String[] board = new String[] {
            "####",
            "#..#",
            "#..#",
            "####"
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
	
	@Test
    public void testExample4() {
        String[] board = new String[] {
            "#####",
            "#####",
            "#####",
            "#####",
            "....."
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertFalse(result);
    }
	
	@Test
    public void testExample5() {
        String[] board = new String[] {
            "#####",
            "#####",
            "#####",
            "#####",
            "#####"
        };
        boolean result = new SquareDetectorSolution.SquareDetector(board).result;
        assertTrue(result);
    }
	
	@Test
    public void lineWidthTest() {
        assertEquals(0, SquareDetectorSolution.SquareDetector.getLineWidth("......."));
        assertEquals(-1, SquareDetectorSolution.SquareDetector.getLineWidth(".#.#..."));
        assertEquals(1, SquareDetectorSolution.SquareDetector.getLineWidth(".#"));
        assertEquals(2, SquareDetectorSolution.SquareDetector.getLineWidth("##"));
        assertEquals(10, SquareDetectorSolution.SquareDetector.getLineWidth("##########"));
        assertEquals(10, SquareDetectorSolution.SquareDetector.getLineWidth(".##########."));
        assertEquals(10, SquareDetectorSolution.SquareDetector.getLineWidth("##########."));
        assertEquals(2, SquareDetectorSolution.SquareDetector.getLineWidth(".##"));
        assertEquals(2, SquareDetectorSolution.SquareDetector.getLineWidth(".##."));
        assertEquals(1, SquareDetectorSolution.SquareDetector.getLineWidth(".#."));
        assertEquals(-1, SquareDetectorSolution.SquareDetector.getLineWidth("#.#"));
        assertEquals(0, SquareDetectorSolution.SquareDetector.getLineWidth("."));
    }
}
