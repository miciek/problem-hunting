package com.michalplachta.hunting.uva.s11385;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * UVA 11385 - Da Vinci Code solution http://uva.onlinejudge.org/external/113/11385.html
 * @category string, ad-hoc, fibonacci
 * @author micio
 */
class Main {
    private char[] decoded = new char[100];
    private Map<Long, Integer> fibToIndex = new HashMap<Long, Integer>();
    
    public Main() {
        long last = 2;
        long slast = 1;
        long max = 1L << 31;
        
        fibToIndex.put(slast, 0);
        fibToIndex.put(last, 1);
        int current = 2;
        
        while (last <= max) {
            long n = last + slast;
            slast = last;
            last = n;
            fibToIndex.put(n, current++);
        }
    }
    
    public String decode(long[] fibs, String cipherString) {
        clearDecoded();
        char[] cipher = cipherString.toCharArray();
        int currentUppercase = -1;
        for (int i = 0; i < fibs.length; i++) {
            currentUppercase = getNextUppercaseIndex(cipher, currentUppercase);
            decoded[fibToIndex.get(fibs[i])] = cipher[currentUppercase];
        }
        
        return createStringFromDecoded();
    }
    
    private int getNextUppercaseIndex(char[] array, int current) {
        current++;
        while (current < array.length && (array[current] < 'A' || array[current] > 'Z')) {
            current++;
        }
        return current;
    }
    
    private void clearDecoded() {
        for (int i = 0; i < decoded.length; i++) {
            decoded[i] = 0;
        }
    }
    
    private String createStringFromDecoded() {
        int length = decoded.length;
        while (decoded[length - 1] == 0 && length >= 0) {
            length--;
        }
        
        for (int i = 0; i < length; i++) {
            if (decoded[i] == 0) {
                decoded[i] = ' ';
            }
        }
        
        return new String(decoded, 0, length);
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        Main m = new Main();
        while (T-- > 0) {
            int N = in.nextInt();
            long[] d = new long[N];
            for (int i = 0; i < N; i++) {
                d[i] = in.nextInt();
            }
            in.nextLine();
            String cipherText = in.nextLine();
            System.out.println(m.decode(d, cipherText));
        }
        
        in.close();
    }
}
