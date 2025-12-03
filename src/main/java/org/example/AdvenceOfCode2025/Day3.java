package org.example.AdvenceOfCode2025;
import java.math.BigInteger;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        long total = 0;
//        System.out.println("Qatorlarni kiriting (bo'sh qator bilan yakunlang):");
//        while (true) {
//            String line = sc.nextLine().trim();
//            if (line.isEmpty()) break; // input tugadi
//            total += maxPairValue1(line);
//        }
//        System.out.println("Jami maksimal joltage: " + total);

        Scanner sc = new Scanner(System.in);
        BigInteger total = BigInteger.ZERO;
        System.out.println("Qatorlarni kiriting (bo'sh qator bilan yakunlang):");
        while (true) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;
            String best12 = maxPairValue2(line);
            BigInteger value = new BigInteger(best12);
            System.out.println("Tanlangan 12 ta raqam: " + best12 + " and total =  " + total);
            total = total.add(value);
        }
        System.out.println("Jami maksimal joltage: " + total);
    }

    private static int maxPairValue1(String s) {
        int n = s.length();
        int[] maxRight = new int[n];
        int curMax = -1;
        for (int i = n - 1; i >= 0; i--) {
            maxRight[i] = curMax;
            int d = s.charAt(i) - '0';
            if (d > curMax) curMax = d;
        }
        int best = 0;
        for (int i = 0; i < n - 1; i++) {
            int left = s.charAt(i) - '0';
            int right = maxRight[i];
            if (right >= 0) {
                int val = left * 10 + right;
                if (val > best) best = val;
            }
        }
        return best;
    }
    private static String maxPairValue2(String s) {
        final int K = 12;
        int n = s.length();
        if (n <= K) return s;
        int toRemove = n - K;
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (stack.length() > 0 && toRemove > 0 && stack.charAt(stack.length() - 1) < c) {
                stack.deleteCharAt(stack.length() - 1);
                toRemove--;
            }
            stack.append(c);
        }
        if (toRemove > 0) {
            stack.setLength(stack.length() - toRemove);
        }
        return stack.toString();
    }
}
