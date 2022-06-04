package com.patterns.backtracking;

import java.util.HashSet;
import java.util.Set;

/*
 * LeetCode: https://leetcode.com/problems/n-queens-ii/
 * Approach:
 *  Recursive backtracking. Apply the general pattern:
 *  
 *  backtrack:
 *      is it solved?
 *          if so, add to list of solutions
 *          return
 *      
 *      for each candidate:
 *          is it valid?
 *              push onto current potential solution
 *              recursively backtrack
 *              remove from current potential solution
 * 
 *  Complexity:
 *      Time: O(N!)
 * 
 *  NOTE: Utilizes clever trick from LC solution. You can check if a square is on the same diagonal as another by either
 *  adding the row to the column of each or subtracting the row from the column of each. If they match, they are on the 
 *  same "hill" (slowing upwards) diagonal or "dale" (sloping downwards) anti-diagonal.
 * 
 * 
 *          Col
 *    Row    0   1   2   3   4
 *        xxxxxxxxxxxxxxxxxxxx       "hill" anti-diagonal (row + column)
 *     0  x  0   1   2   3   4
 *     1  x  1   2   3   4   5
 *     2  x  2   3   4   5   6
 *     3  x  3   4   5   6   7
 *     4  x  4   5   6   7   8
 *        xxxxxxxxxxxxxxxxxxxx
 * 
 *
 *          Col
 *    Row    0   1   2   3   4
 *        xxxxxxxxxxxxxxxxxxxx      "dale" diagonal (row - column)
 *     0  x  0  -1  -2  -3  -4
 *     1  x  1   0  -1  -2  -3
 *     2  x  2   1   0  -1  -2
 *     3  x  3   2   1   0  -1
 *     4  x  4   3   2   1   0
 *        xxxxxxxxxxxxxxxxxxxx
 */
class Solution {
    private int count;
    private Set<Integer> diagHill;
    private Set<Integer> diagDale;
    private int[][] board;
    
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        
        count = 0;
        diagHill = new HashSet<>();
        diagDale = new HashSet<>();
        board = new int[n][n];
        
        applyBacktrack(0);
        
        return count;
    }
    
    private void applyBacktrack(int row) {
        if (row == board.length) {
            count++;
            return;
        }
        
        for (int col=0; col < board.length; col++) {
            if (isValid(row, col)) {
                placeCandidate(row, col);
                applyBacktrack(row+1);
                removeCandidate(row, col);
            }
        }
    }
    
    private boolean isValid(int row, int col) {
        for (int i=0; i<board.length; i++) {
            if (board[row][i] == 1) {
                return false;
            }
            
            if (board[i][col] == 1) {
                return false;
            }
            
            
            if (diagHill.contains(row - col) || diagDale.contains(row + col)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void placeCandidate(int row, int col) {
        board[row][col] = 1;
        diagHill.add(row - col);
        diagDale.add(row + col);
    }
    
    private void removeCandidate(int row, int col) {
        board[row][col] = 0;
        diagHill.remove(row - col);
        diagDale.remove(row + col);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    
        System.out.println(solution.totalNQueens(4));
        System.out.println(solution.totalNQueens(1));
    }
}

