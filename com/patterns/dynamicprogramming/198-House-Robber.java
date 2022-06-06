package com.patterns.dynamicprogramming;

/*
 * LeetCode: https://leetcode.com/problems/house-robber/
 * Approach:

 */
 
class Solution198 {
    private int[] cache;
    public int robTopDownMemoization(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        cache = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            cache[i] = Integer.MIN_VALUE;
        }
        
        return topDownHelper(nums.length-1, nums);
    }

    private int topDownHelper(int i, int[] nums) {
        if (i == 0) {
            return nums[0];
        } else if (i == 1) {
            return Math.max(nums[0], nums[1]);
        }

        if (cache[i] != Integer.MIN_VALUE) {
            return cache[i];
        }

        int result = Math.max(  topDownHelper(i-1, nums),
                                topDownHelper(i-2, nums) + nums[i]);

        cache[i] = result;
        return result;
    }

    public int robBottomUpTabulation(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        
        int dpMinusTwo = nums[0];
        int dpMinusOne = Math.max(nums[1], dpMinusTwo);

        for (int i=2; i<nums.length; i++) {
            int temp = Math.max(    dpMinusTwo + nums[i],
                                    dpMinusOne);
            dpMinusTwo = dpMinusOne;
            dpMinusOne = temp;
        }

        return dpMinusOne;
    }

    public static void main(String[] args) {
        Solution198 solution = new Solution198();

        System.out.println(
            solution.robTopDownMemoization(new int[] {1,2,3,1}) // 4
        );
        System.out.println(
            solution.robBottomUpTabulation(new int[] {1,2,3,1}) // 4
        );

        System.out.println(
            solution.robTopDownMemoization(new int[] {2,7,9,3,1}) // 12
        );
        System.out.println(
            solution.robBottomUpTabulation(new int[] {2,7,9,3,1}) // 12
        );

        System.out.println(
            solution.robTopDownMemoization(new int[] {2,1}) // 2
        );
        System.out.println(
            solution.robBottomUpTabulation(new int[] {2,1}) // 2
        );

        System.out.println(
            solution.robTopDownMemoization(new int[] {5}) // 5
        );
        System.out.println(
            solution.robBottomUpTabulation(new int[] {5}) // 5
        );
    }
}