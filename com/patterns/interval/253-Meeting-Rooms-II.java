package com.patterns.interval;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * LeetCode: https://leetcode.com/problems/meeting-rooms-ii/
 * Approach:
 *  sort array by start time
 *  use min-heap sorted by end time for tracking rooms needed
 *  iterate segments
 *      if current.start >= heap.top.end, free the room in the heap
 *      add current to heap
 *      set max to maximum of max and heap.size
 * 
 *  Complexity:
 * 
 *      Time: O(N log N)   + O(N          *   log N)          = O (N log N)
 *            [array sort]  [single-pass]   [ pop + heapify]
 * 
 *      Space: O(N)
 *          in the worst-case, everything conflicts and we have N items on the heap
 */

class Solution253 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
			return 0;
		}
        
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[1] - b[1]);

		int max = 0;

		for (int[] interval : intervals) {
			while (!minHeap.isEmpty() && interval[0] >= minHeap.peek()[1]) {
				minHeap.poll();
			}
			minHeap.offer(interval);
			max = Math.max(max, minHeap.size());
		}
		return max;
    }

    public static void main(String[] args) {
        Solution253 solution = new Solution253();

        System.out.println(
            solution.minMeetingRooms(new int[][] {{0,30},{5,10},{15,20}})
        );

        System.out.println(
            solution.minMeetingRooms(new int[][] {{7,10},{2,4}})
        );

        System.out.println(
            solution.minMeetingRooms(new int[][] {{13,15},{1,13}})
        );
    }
}