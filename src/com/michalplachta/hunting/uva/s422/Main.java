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
    private final String[] matrix;
    private int rows = 0;
    private final Result result = new Result();

    public Main(int N) {
        matrix = new String[N];
    }

    public void addRow(String s) {
        matrix[rows++] = s;
    }

    public Result find(String s) {
        return result;
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
