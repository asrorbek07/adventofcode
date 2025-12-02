package org.example.AdvenceOfCode2025;

import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) {
        long sum = calculateInvalidIDSum();
        System.out.println("Sum of invalid IDs: " + sum);
    }

    public static long calculateInvalidIDSum() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        scanner.close();
        String[] ranges = input.split(",");
        long sum = 0;
        for (String range : ranges) {
            String[] bounds = range.split("-");
            long start = Long.parseLong(bounds[0]);
            long end = Long.parseLong(bounds[1]);

            for (long id = start; id <= end; id++) {
                if (isRepeatedDigits2(id)) {
                    sum += id;
                }
            }
        }
        return sum;
    }

    private static boolean isRepeatedDigits1(long n) {
        String s = String.valueOf(n);
        int len = s.length();
        if (len % 2 != 0) return false;
        String firstHalf = s.substring(0, len / 2);
        String secondHalf = s.substring(len / 2);
        return firstHalf.equals(secondHalf);
    }

    private static boolean isRepeatedDigits2(long n) {
        String s = String.valueOf(n);
        int len = s.length();
        for (int subLen = 1; subLen <= len / 2; subLen++) {
            if (len % subLen != 0) continue;
            String sub = s.substring(0, subLen);
            int repeats = len / subLen;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < repeats; i++) {
                sb.append(sub);
            }
            if (sb.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
