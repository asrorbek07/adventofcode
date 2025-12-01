package org.example.AdvenceOfCode2025;

import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        int password = countZeros2();
        System.out.println("Parol (0 ga tushganlar soni): " + password);
    }

    public static int countZeros2() {
        Scanner scanner = new Scanner(System.in);
        int dial = 50;
        int zeroCount = 0;
        while (true) {
            String rotation = scanner.nextLine().trim();
            if (rotation.isEmpty()) break;
            char dir = rotation.charAt(0);
            int steps=Integer.parseInt(rotation.substring(1));
            int delta = (dir == 'L' ? -1 : (dir == 'R' ? 1 : 0));
            for (int i = 0; i < steps; i++) {
                dial = (dial + delta + 100) % 100;
                if (dial == 0) {
                    zeroCount++;
                }
            }
        }

        return zeroCount;
    }
}
