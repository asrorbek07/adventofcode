package org.example.AdvenceOfCode2025;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose solution to run: 1 = total fresh IDs, 2 = fresh among available");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            solution1(sc);
        } else if (choice == 2) {
            solution2(sc);
        } else {
            System.out.println("Invalid choice");
        }
    }

    private static void solution1(Scanner sc) {
        List<BigInteger[]> ranges = readRanges(sc);
        if (ranges.isEmpty()) {
            System.out.println("No ranges entered.");
            return;
        }
        ranges.sort(Comparator.comparing(r -> r[0]));
        List<BigInteger[]> merged = new ArrayList<>();
        BigInteger[] current = ranges.get(0);
        for (int i = 1; i < ranges.size(); i++) {
            BigInteger[] next = ranges.get(i);
            if (next[0].compareTo(current[1].add(BigInteger.ONE)) <= 0) {
                current[1] = current[1].max(next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);
        BigInteger total = BigInteger.ZERO;
        for (BigInteger[] range : merged) {
            total = total.add(range[1].subtract(range[0]).add(BigInteger.ONE));
        }
        System.out.println("Total fresh ingredient IDs: " + total);
    }

    private static void solution2(Scanner sc) {
        List<BigInteger[]> ranges = readRanges(sc);
        List<BigInteger> available = readAvailableIDs(sc);
        int freshCount = 0;
        for (BigInteger id : available) {
            for (BigInteger[] range : ranges) {
                if (id.compareTo(range[0]) >= 0 && id.compareTo(range[1]) <= 0) {
                    freshCount++;
                    break;
                }
            }
        }
        System.out.println("Number of fresh ingredients: " + freshCount);
    }

    private static List<BigInteger[]> readRanges(Scanner sc) {
        List<BigInteger[]> ranges = new ArrayList<>();
        System.out.println("Enter fresh ingredient ranges (e.g. 0-5), end with empty line:");
        while (true) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;
            String[] parts = line.split("-");
            BigInteger start = new BigInteger(parts[0]);
            BigInteger end = new BigInteger(parts[1]);
            ranges.add(new BigInteger[]{start, end});
        }
        return ranges;
    }

    private static List<BigInteger> readAvailableIDs(Scanner sc) {
        List<BigInteger> available = new ArrayList<>();
        System.out.println("Enter available ingredient IDs, end with empty line:");
        while (true) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;
            try {
                available.add(new BigInteger(line));
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid line: " + line);
            }
        }
        return available;
    }
}
