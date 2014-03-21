package com.michalplachta.hunting.uva.a10810;

import java.util.Scanner;

/**
 * UVA 10810 - Contest Scoreboard problem solution
 * 
 * http://uva.onlinejudge.org/index.php?option
 * =com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=1751
 * 
 * @author micio
 */
class Main {
    private final int[] array = new int[500000];
    private int length = 0;
    
    public void add(int c) {
        array[length++] = c;
    }
    
    public void reset() {
        length = 0;
    }
    
    // TODO: implement merge sort
    public int getMinSwaps() {
        int swaps = 0;
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length - 1 - i; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    swaps++;
                }
            }
        }
        return swaps;
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        while (N > 0) {
            m.reset();
            while(N-- > 0) {
                m.add(in.nextInt());
            }
            System.out.println(m.getMinSwaps());
            N = in.nextInt();
        }
        in.close();
    }
}
