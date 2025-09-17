/*
LeetCode Problem: https://leetcode.com/problems/course-schedule/

Question: 207. Course Schedule

Problem Statement: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */

/*
Approach:
1. Build an adjacency list to represent the graph from prerequisites.
   - Each edge [a, b] means: to take course a, you must first take course b.
2. Compute the indegree (number of prerequisites) for each course.
3. Initialize a queue with all courses having indegree = 0 (no prerequisites).
4. Perform BFS (Kahn’s Algorithm):
   - Repeatedly remove nodes from the queue.
   - Add them to the topological sort list.
   - Decrease the indegree of their neighbors.
   - If a neighbor’s indegree becomes 0, add it to the queue.
5. At the end, if the number of nodes in the topological order equals the number of courses, 
   return true (all courses can be finished). Otherwise, return false (cycle exists).

Time Complexity: O(V + E),  
- V = number of courses, E = number of prerequisite pairs  
- Each node and edge is processed once.  
Space Complexity: O(V + E),  
- Adjacency list + indegree array + queue storage.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _207_Course_Schedule {
    // Method to find the number of island in the grid
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Initialize adjacency list for the graph
        ArrayList<ArrayList<Integer>> adjancencyList = new ArrayList<>();

        // Add number of list equal to the number of courses
        for (int i = 0; i < numCourses; i++) {
            adjancencyList.add(new ArrayList<>());
        }

        // Get the prerequisites length
        int len = prerequisites.length;

        // Make the adjacency list according to the first value of prerequisites
        for (int i = 0; i < len; i++) {
            adjancencyList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        // Initialize a in degree for the for the graph node
        int indegree[] = new int[numCourses];

        // Fill the total indegree of each node
        for (int i = 0; i < numCourses; i++) {
            for (int node : adjancencyList.get(i)) {
                indegree[node]++;
            }
        }

        // Initialize queue for the BSF Traversal of the graph
        Queue<Integer> queue = new LinkedList<>();

        // Add all nodes to the queue
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Initialize a new list for the topological sort of the graph
        ArrayList<Integer> topologicalSort = new ArrayList<>();

        // Do the BSF Traverse of the graph untill the queue is empty
        while (!queue.isEmpty()) {
            // Retrieve the first number of the queue
            int node = queue.remove();

            // Add the node in the topological sort list
            topologicalSort.add(node);

            // Remove the indegree of the node as it is in our topological sort
            for (int val : adjancencyList.get(node)) {
                indegree[val]--;
                if (indegree[val] == 0) {
                    queue.add(val);
                }
            }
        }

        // Retrun the boolean true for the topological sort is equal to the number of
        // courses else return false
        return topologicalSort.size() == numCourses;
    }

    // Main method to test canFinish
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = { { 1, 0 } };

        boolean result = canFinish(numCourses, prerequisites);

        if (result) {
            System.out.print("The course can be finished.");
        } else {
            System.out.print("The course can not be finished.");
        }
    }
}
