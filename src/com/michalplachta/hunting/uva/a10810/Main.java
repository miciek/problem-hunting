package com.michalplachta.hunting.uva.a10810;

import java.util.Scanner;

/**
 * UVA 10810 - Ultra Quicksort problem solution
 * http://uva.onlinejudge.org/index.php?option
 * =com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=1751
 * 
 * @category ad-hoc, sorting, merge-sort
 * @author micio
 */
class Main {
    private final int[] array = new int[500000];
    private final int[] temp = new int[500000];
    private int length = 0;
    private long swaps = 0;

    public void add(int c) {
        array[length++] = c;
    }

    public void reset() {
        length = 0;
    }

    public boolean isSorted() {
        for (int i = 0; i < length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public long getMinSwaps() {
        swaps = 0;
        mergeSort(0, length);
        return swaps;
    }

    private void mergeSort(int left, int right) {
        if (left <= right) {
            int size = right - left;
            if (size > 2) {
                mergeSort(left, left + size / 2);
                mergeSort(left + size / 2, right);
                merge(left, left + size / 2, left + size / 2, right);
            } else if (size == 2 && array[left] > array[right - 1]) {
                temp[left] = array[left];
                array[left] = array[right - 1];
                array[right - 1] = temp[left];
                swaps++;
            }
        }
    }

    private void merge(int li, final int liend, int ri, final int riend) {
        int i = 0;
        int start = li;
        while (li < liend) {
            if (ri == riend || array[li] <= array[ri]) {
                temp[start + i] = array[li];
                li++;
            } else {
                temp[start + i] = array[ri];
                swaps += ri - start - i;
                ri++;
            }
            i++;
        }

        for (int j = start; j < start + i; j++) {
            array[j] = temp[j];
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        while (N > 0) {
            m.reset();
            while (N-- > 0) {
                m.add(in.nextInt());
            }
            System.out.println(m.getMinSwaps());
            N = in.nextInt();
        }
        in.close();
    }
}
