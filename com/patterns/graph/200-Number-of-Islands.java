package com.patterns.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
 * LeetCode: https://leetcode.com/problems/number-of-islands/
 */
 
class Solution200 {

    private int[][] directions = new int[][] {{-1,0}, {1,0}, {0, -1}, {0, 1}}; // up, down, left, right

    public int numIslandsBFS(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;

        boolean [][] visited = new boolean[m][n];

        Queue<int[]> q = new LinkedList<>();
        
        for (int row=0; row<m; row++) {
            for (int col=0; col<n; col++) {
                if (!visited[row][col] && grid[row][col] != '0') {
                    q.offer(new int[] {row,col});
                    bfs(q, visited, grid);
                    count++;
                }
            }
        }

        return count;
    }

    private void bfs(Queue<int[]> q, boolean[][] visited, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int row = current[0];
            int col = current[1];

            visited[row][col] = true;

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                    && !visited[newRow][newCol] && grid[newRow][newCol] != '0') {

                    q.offer(new int[] {newRow, newCol});
                }
            }
        }

        return;
    }

    public int numIslandsDFS(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;

        boolean [][] visited = new boolean[m][n];

        for (int row=0; row<m; row++) {
            for (int col=0; col<n; col++) {
                if (!visited[row][col] && grid[row][col] != '0') {
                    dfs(row, col, visited, grid);
                    count++;
                }
            }
        }
        
        return count;
    }

    private void dfs(int row, int col, boolean[][] visited, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] || grid[row][col] == '0') {
            return;
        }

        visited[row][col] = true;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            dfs(newRow, newCol, visited, grid);
        }
    }

    public static void main(String[] args) {
        Solution200 solution = new Solution200();

        char[][] grid = new char [][] {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}};

        System.out.println(
            solution.numIslandsBFS(grid) // 1
        );
        System.out.println(
            solution.numIslandsDFS(grid) // 1
        );
        
        grid = new char [][] {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}};

        System.out.println(
            solution.numIslandsBFS(grid) // 3
        );
        System.out.println(
            solution.numIslandsDFS(grid) // 3
        );
    }
}