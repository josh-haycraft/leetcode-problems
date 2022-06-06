package com.patterns.dynamicprogramming;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/*
 * LeetCode: https://leetcode.com/problems/house-robber-ii/
 */
 
class Solution213 {
    // using AbstractMap.SimpleEntry locally rather than Pair, to avoid external dependencies.
    // change this to javafx.util.Pair in LeetCode itself.
    //
    // unable to use arrays (e.g. int[]) because new int[]{1,2} != new int[] {1,2}... these
    // are different objects, and won't match on the Map.contains(...) method.
    private Map<AbstractMap.SimpleEntry<Integer,Integer>, Integer> cache;
    public int robTopDownMemoization(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        cache = new HashMap<>();
        return Math.max(topDownHelper(0, nums.length-2, nums),
                        topDownHelper(1, nums.length-1, nums));
    }

    private int topDownHelper(int start, int end, int[] nums) {
        if (end == start) {
            return nums[start];
        } else if (end == start + 1) {
            return Math.max(nums[start], nums[start+1]);
        }

        AbstractMap.SimpleEntry<Integer,Integer> pair = new AbstractMap.SimpleEntry<>(start, end);

        if (cache.containsKey(pair)) {
            return cache.get(pair);
        }

        int result = Math.max(  topDownHelper(start, end-1, nums),
                                topDownHelper(start, end-2, nums) + nums[end]);

        cache.put(pair, result);
        return result;
    }

    public int robBottomUpTabulation(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(bottomUpHelper(0, nums.length-2, nums),
                        bottomUpHelper(1, nums.length-1, nums));
    }

    
    /*
     * In a previous attempt, I tried to set the base cases to the first two elements.
     * 
     *  int dpMinusTwo = nums[start];
     *  int dpMinusOne = Math.max(nums[start+1], dpMinusTwo);
     *
     * And loop from the third onward:
     * 
     *  for (int i=start+2; i<nums.length; i++) {...}
     * 
     * This does work, but you have an additional edge case to watch for when using relative starts/ends: 
     * an array of 2 and a start of 1 will have an index out of bounds error on int i=start+2. 
     *
     * It is far simpler to consider dpMinusOne and dpMinusTwo as virtual nums[-1] and nums[-2], each of value 0. 
     */
    private int bottomUpHelper(int start, int end, int[] nums) {
        int dpMinusTwo = 0;
        int dpMinusOne = 0;

        for (int i=start; i<=end; i++) {
            int temp = Math.max(    dpMinusTwo + nums[i],
                                    dpMinusOne);
            dpMinusTwo = dpMinusOne;
            dpMinusOne = temp;
        }

        return dpMinusOne;
    }

    public static void main(String[] args) {
        Solution213 solution = new Solution213();

        System.out.println(
            solution.robTopDownMemoization(new int[] {2,3,2}) // 3
        );
        System.out.println(
            solution.robBottomUpTabulation(new int[] {2,3,2}) // 3
        );

        System.out.println(
            solution.robTopDownMemoization(new int[] {1,2,3,1}) // 4
        );
        System.out.println(
            solution.robBottomUpTabulation(new int[] {1,2,3,1}) // 4
        );

        System.out.println(
            solution.robTopDownMemoization(new int[] {5}) // 5
        );
        System.out.println(
            solution.robBottomUpTabulation(new int[] {5}) // 5
        );
    }
}