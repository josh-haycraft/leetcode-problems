package com.patterns.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode: https://leetcode.com/problems/merge-intervals/
 * Approach:
 *  sort by start time
 *  iterate segments; if current.end >= next.start, merge
 * 
 *  Complexity:
 *      Time: O(N log N) + O(N) = O (N log N)
 *            [sort]   [single-pass]
 */
 
class Solution56 {
    public int[][] merge(int[][] intervals) {

        // sort by start time (first element in segment)
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        
        // list to hold results
        List<int[]> merged = new ArrayList<>();
        
        // set the 'curr' pointer to the first segment
        // this will either be added as-is or merged with 
        // the next segment
        int[] curr = intervals[0];
        for (int i=1; i<intervals.length; i++) {
            // if the end time of 'curr' is greater or equal to the start time of the 
            // next segment, then merge. set 'curr' end time to the maximum of the end
            // time of the next segment and the end time of 'curr' (in case 'curr' spans 
            // multiple segments)
            if (curr[1] > intervals[i][0]) {
                curr[1] = Math.max(intervals[i][1], curr[1]);
            } else {
                // if we did not need to expand 'curr', we add the latest 'curr' to
                // the answer, and update the reference
                merged.add(curr);
                curr = intervals[i];
            }
        }
        // add the final 'curr' reference
        merged.add(curr);
        
        // convert to output type
        int[][] result = new int[merged.size()][2];
        merged.toArray(result);
        
        return result;
    }

    public static void main(String[] args) {
        Solution56 solution = new Solution56();

        System.out.println(Arrays.deepToString(
            solution.merge(new int[][] {{1,3},{2,6},{8,10},{15,18}})
        ));

        System.out.println(Arrays.deepToString(
            solution.merge(new int[][] {{1,4},{4,5}})
        ));
    }
}