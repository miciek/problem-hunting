package com.michalplachta.hunting.uva.s4388;

import java.util.Scanner;

/**
 * UVA ACM-ICPC Live Archive 4388 - Suffix reconstruction
 * https://icpcarchive.ecs.baylor.edu/external/43/4388.html
 * 
 * @category string, suffix-tree, ad-hoc
 * @author micio
 */
class Main {
    final private int[] sa;
    final private int[] positions;

    public Main(int[] sa) {
        this.sa = sa;
        positions = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            sa[i] -= 1;
            positions[sa[i]] = i;
        }
    }

    public String generateStringForSuffixArray() {
        char[] result = new char[sa.length];
        char cur = 'a';
        result[sa[0]] = cur;
        for (int i = 1; i < result.length; i++) {
            if (!canBothBeEqual(sa[i - 1], sa[i])) {
                cur++;
            }

            if (cur > 'z') {
                return "-1";
            }

            result[sa[i]] = cur;
        }

        return new String(result);
    }

    private boolean canBothBeEqual(int x, int y) {
        if (x + 1 == sa.length) {
            return true;
        }

        if (y + 1 == sa.length) {
            return false;
        }

        return positions[x + 1] < positions[y + 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = in.nextInt();
            }
            Main m = new Main(array);
            System.out.println(m.generateStringForSuffixArray());
        }

        in.close();
    }
}
