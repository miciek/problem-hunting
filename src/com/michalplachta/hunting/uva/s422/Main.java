package com.michalplachta.hunting.uva.s422;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
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
    private final String[] matrix;
    private int rows = 0;
    private final Result result = new Result();

    public Main(int N) {
        this.N = N;
        matrix = new String[N];
    }

    public void addRow(String s) {
        matrix[rows++] = s;
    }

    public Result find(String s) {
        result.found = false;
        int currentIndex = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (matrix[row].charAt(col) == s.charAt(currentIndex)) {
                    result.startRow = row;
                    result.startCol = col;
                    List<SimpleEntry<Integer, Integer>> candidates = findCharAround(row, col, s.charAt(currentIndex));
                    while (!candidates.isEmpty()) {

                    }
                }
            }
        }
        return result;
    }

    private List<SimpleEntry<Integer, Integer>> findCharAround(int row, int col, char c) {
        List<SimpleEntry<Integer, Integer>> found = new ArrayList<SimpleEntry<Integer, Integer>>(8);
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < N && j >= 0 && j < N) {
                    if ((i != 0 || j != 0) && matrix[i].charAt(j) == c) {
                        found.add(new SimpleEntry<Integer, Integer>(i, j));
                    }
                }
            }
        }
        return found;
    }

    public static class Result {
        boolean found;
        int startRow, startCol, endRow, endCol;

        @Override
        public String toString() {
            if (found) {
                return String.format("%d,%d %d,%d", startRow, startCol, endRow, endCol);
            } else {
                return "Not found";
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        Main m = new Main(N);
        while (N-- > 0) {
            m.addRow(in.nextLine());
        }

        String s = in.nextLine();
        while (!s.equals("0")) {
            Result r = m.find(s);
            System.out.println(r);
            s = in.nextLine();
        }

        in.close();
    }
}
