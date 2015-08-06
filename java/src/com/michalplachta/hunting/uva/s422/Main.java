package com.michalplachta.hunting.uva.s422;

import java.util.Scanner;

/**
 * UVA 422 - Word search wonder solution 
 * 
 * http://uva.onlinejudge.org/external/4/422.html
 * 
 * @category string, ad-hoc
 * @author micio
 */
class Main {
    private final int N;
    private final char[][] matrix;
    private int rows = 0;
    
    public Main(int N) {
        this.N = N;
        matrix = new char[N][];
    }
    
    public void addRow(String s) {
        matrix[rows++] = s.toCharArray();
    }
    
    public Result find(String s) {
        if (s.length() > N) {
            return null;
        }
        
        char[] array = s.toCharArray();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int idiff = -1; idiff <= 1; idiff++) {
                    for (int jdiff = -1; jdiff <= 1; jdiff++) {
                        if (idiff != 0 || jdiff != 0) {
                            Result result = find(i, j, idiff, jdiff, array, 0);
                            if (result != null) {
                                result.startRow = i + 1;
                                result.startCol = j + 1;
                                return result;
                            }
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    private Result find(int i, int j, int idiff, int jdiff, char[] array, int k) {
        if (array[k] == matrix[i][j]) {
            if (k == array.length - 1) {
                Result result = new Result();
                result.endRow = i + 1;
                result.endCol = j + 1;
                return result;
            } else {
                int nexti = i + idiff;
                int nextj = j + jdiff;
                if (nexti >= 0 && nextj >= 0 && nexti < N && nextj < N) {
                    Result result = find(nexti, nextj, idiff, jdiff, array, k + 1);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        
        return null;
    }
    
    public static class Result {
        int startRow, startCol, endRow, endCol;
        
        @Override
        public String toString() {
            return String.format("%d,%d %d,%d", startRow, startCol, endRow, endCol);
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        
        Main m = new Main(N);
        while (N-- > 0) {
            m.addRow(in.nextLine());
        }
        
        String s = in.nextLine();
        while (!s.equals("0")) {
            Result r = m.find(s);
            if (r != null) {
                System.out.println(r);
            } else {
                System.out.println("Not found");
            }
            s = in.nextLine();
        }
        
        in.close();
    }
}
