package org.example.AdvenceOfCode2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<String> rows = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;
            rows.add(line);
        }
        int n = rows.size();
        int m = rows.get(0).length();
        char[][] g = new char[n][m];
        for (int i = 0; i < n; i++) g[i] = rows.get(i).toCharArray();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        int result1 = 0, result2 = 0;
        result1 = rollsOfPaper1(g, m, n, dx, dy, 0);
        result2 = rollsOfPaper2(g, n, m, dx, dy, 0);
        System.out.println("First result: " + result1);
        System.out.println("Second result: " + result2);
    }

    private static int rollsOfPaper1(char[][] g, int m, int n, int[] dx, int[] dy, int count) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (g[r][c] != '@') continue;
                int neighbors = 0;
                for (int i = 0; i < 8; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                        if (g[nr][nc] == '@') neighbors++;
                    }
                }
                if (neighbors < 4) count++;
            }
        }
        return count;
    }

    private static int rollsOfPaper2(char[][] g, int n, int m, int[] dx, int[] dy, int count) {
        int innerCount = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (g[r][c] != '@') continue;
                int neighbors = 0;
                for (int i = 0; i < 8; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                        if (g[nr][nc] == '@') neighbors++;
                    }
                }
                if (neighbors < 4) {
                    innerCount++;
                    g[r][c] = 'x';
                }
            }
        }
        return innerCount > 0 ? rollsOfPaper2(g, n, m, dx, dy, count + innerCount) : count;
    }
}