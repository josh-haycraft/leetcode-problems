package com.patterns.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* Problem https://leetcode.com/problems/course-schedule-ii/ 
   Approach: Kahn's Algorithm for Topological Sorting 
    (aka BFS on directed graph, where nodes with "in-degree" of 0 are added to BFS queue on each iteration)

    (1) create adjacency list
    (2) populate adjacency list, and map of vertices to their in-degrees
    (3) add all vertices with in-degree of 0 to queue
    (4) while queue is not-empty, pop from queue, add to ordered list
        get the adjacency list for popped vertex
        subtract 1 from neighbor in-degrees, add to queue if 0
    (5) check for remaining cycles; if they exist, no solution can be found
 */ 
class Solution210 {

    public  int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegrees = new HashMap<>();
        int[] sorted = new int[numCourses];
        int sortedIndex = 0;

        // (1) convert edges to adjacency list
        for (int i=0; i<numCourses; i++) {
            graph.put(i, new ArrayList<>());
            inDegrees.put(i, 0);
        }

        // (2) populate adjacency list, and map of vertices to their in-degrees
        for (int[] prereq : prerequisites) {
            // [0, 1] means 1 is a prereq of 0
            int parent = prereq[1];
            int child = prereq[0];

            graph.get(parent).add(child);
            inDegrees.put(child, inDegrees.get(child) + 1);
        }

        // (3) add all vertices with in-degree of 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer,Integer> kv : inDegrees.entrySet()) {
            if (kv.getValue() == 0) {
                q.offer(kv.getKey());
            }
        }

        // (4) while queue is not-empty, pop from queue, add to sorted result
        while(!q.isEmpty()) {
            int vertex = q.poll();
            sorted[sortedIndex++] = vertex;

            // get the adjacency list for popped vertex
            for (int neighbor : graph.get(vertex)) {
                // subtract 1 from neighbor in-degrees, add to queue if 0
                inDegrees.put(neighbor, inDegrees.get(neighbor) - 1);
                if (inDegrees.get(neighbor) == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // (5) check for remaining cycles; if they exist, no solution can be found
        return sortedIndex == numCourses ? sorted : new int [] {};
    }

    public static void main(String[] args) {
        Solution210 solution = new Solution210();

        System.out.println(Arrays.toString(
            solution.findOrder(2, new int[][] {{1,0}}) // [0,1]
        ));

        System.out.println(Arrays.toString(
            solution.findOrder(4, new int[][] {{1,0}, {2,0}, {3,1}, {3,2}}) // [0,2,1,3] or [0,1,2,3]
        ));

        System.out.println(Arrays.toString(
            solution.findOrder(2, new int[][] {{0,1}, {1,0}}) // [] -- cycle!
        ));
    }
}
