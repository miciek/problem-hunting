package com.michalplachta.hunting.uva.a514;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * UVA 514 - Rails problem solution
 * 
 * http://uva.onlinejudge.org/external/5/514.html
 * 
 * @category ad-hoc, stack
 * @author micio
 */
class Main {
    private final Queue<Integer> in = new LinkedList<Integer>();
    private final Stack<Integer> out = new Stack<Integer>();
    private boolean state = true;

    public Main(int N) {
        for (int i = 1; i <= N; i++) {
            in.add(i);
        }
    }

    public boolean add(int car) {
        if (state) {
            if (!out.isEmpty() && out.peek() == car) {
                out.pop();
                return true;
            } else if (!in.isEmpty() && in.peek() <= car) {
                while (!in.isEmpty() && in.peek() <= car) {
                    out.push(in.poll());
                }
                out.pop();
                return true;
            }
        }
        state = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        while (N > 0) {
            int car = in.nextInt();
            if (car == 0) {
                N = in.nextInt();
                if (N > 0) {
                    System.out.println();
                }
            } else {
                Main m = new Main(N);
                boolean possible = m.add(car);
                int i = 1;
                while (i < N) {
                    possible = m.add(in.nextInt());
                    i++;
                }

                System.out.println(possible ? "Yes" : "No");
            }

        }
        System.out.println();
        in.close();
    }
}
