package com.michalplachta.hunting.hackercup;
import java.util.Scanner;

public class SquareDetectorSolution {
	public static class SquareDetector {
		public final boolean result;
		
		public SquareDetector(String[] board) {			
			result = solve(board);
		}

        private boolean solve(String[] board) {            
            boolean inside = false; // state = not inside potential square            
            int width = 0; // potential square width = 0
            int height = 0; // potential square height = 0
            
            // go through rows
            for(int i = 0; i < board.length; i++) {
                // check whether at least one black cell is found ->
                int lineWidth = getLineWidth(board[i]);
                // if there is a black cell, check whether it's valid (in line) - if not RETURN FALSE
                if(lineWidth < 0) return false;
                
                // if it is valid then check if we are inside potential square ->
                if(lineWidth > 0) {                
                    // if we are NOT IN ->
                    if(!inside) { 
                        // if width != 0 or height != 0 (we found earlier square) - return FALSE
                        if(width != 0 || height != 0) return false;
                        // then start making it only if width and height == 0 (not found any earlier) - save square width to line width, and square height to 1
                        inside = true;
                        width = lineWidth;
                        height = 1;
                    } else { // if we are IN - then check whether square width = line width - if not RETURN FALSE
                        if(width != lineWidth) return false;                        
                        // if it is - then increment square height - if it is larger than width RETURN FALSE
                        height++;
                        if(height > width) return false;
                    }                    
                } else { // if we don't have a line (all white cells)
                    inside = false;
                }
            }
            
            return width == height;            
        }
        
        /** @return >0 if there is valid line, 0 if there is not any line, -1 if there is invalid line */
        public static int getLineWidth(String line) {
            int width = 0;
            assert line.length() > 0;
            boolean inside = false;
            for(int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == '.') {
                    if(inside) {
                        inside = false;
                    }
                } else {
                    if(inside) {
                        width++;
                    } else {
                        if (width > 0) return -1;
                        width = 1;
                        inside = true;
                    }
                }
            }
            return width;
        }
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int i = 1; i <= T; i++) {
			int N = in.nextInt();
			String[] board = new String[N];
			for(int j = 0; j < N; j++) {
				board[j] = in.next("[\\.#]+");				
			}
			
			printResult(i, new SquareDetector(board).result);
		}
	}
	
	private static void printResult(int caseNo, boolean result) {
		System.out.println("Case #" + caseNo + ": " + (result ? "YES" : "NO"));
	}
}
