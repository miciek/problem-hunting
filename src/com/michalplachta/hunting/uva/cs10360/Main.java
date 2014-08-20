package com.michalplachta.hunting.uva.cs10360;

import java.util.Scanner;

/**
 * UVA 10360 - Rat Attack problem solution
 * 
 * http://uva.onlinejudge.org/external/103/10360.html
 * 
 * @category complete-search, iterative
 * @author micio
 */
class Main {
    public static final int GRID_SIZE = 1025;
    private int[][] populations = new int[GRID_SIZE][GRID_SIZE];
    private final int power;

    public int best_x;
    public int best_y;
    public int most_killed;

    private int[][] killed = new int[GRID_SIZE][GRID_SIZE];

    public Main(int d) {
        power = d;
    }

    public void setPopulation(int x, int y, int size) {
        populations[x][y] = size;
        if (size > 0) {
            for (int xk = -power; xk <= power; xk++) {
                for (int yk = -power; yk <= power; yk++) {
                    addIfPossible(x + xk, y + yk, size);
                }
            }
        }
    }

    public void solve() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if (killed[x][y] > most_killed) {
                    most_killed = killed[x][y];
                    best_x = x;
                    best_y = y;
                }
            }
        }
    }

    private int addIfPossible(int x, int y, int size) {
        if (x >= 0 && y >= 0 && x < GRID_SIZE && y < GRID_SIZE) {
            killed[x][y] += size;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        while (N-- > 0) {
            int d = in.nextInt();
            Main m = new Main(d);

            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int size = in.nextInt();
                m.setPopulation(x, y, size);
            }

            m.solve();
            System.out.println(m.best_x + " " + m.best_y + " " + m.most_killed);
        }
        in.close();
    }
}
