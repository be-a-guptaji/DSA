/*
LeetCode Problem: https://leetcode.com/problems/course-schedule-ii/

Question: 210. Course Schedule II

Problem Statement: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */

/*
Approach:
1. Build an adjacency list to represent the course graph:
   - Each prerequisite pair [a, b] means: to take course a, you must first take course b.
   - So add an edge b → a.
2. Compute the indegree (number of prerequisites) for each course.
3. Initialize a queue with all courses having indegree = 0 (no prerequisites).
4. Perform BFS (Kahn’s Algorithm):
   - Repeatedly pop a node from the queue.
   - Add it to the topological order result list.
   - For each neighbor (dependent course), decrement its indegree.
   - If indegree becomes 0, push it into the queue.
5. After BFS:
   - If the size of the result list == numCourses, return the result as an array.
   - Otherwise, return an empty array (cycle detected, not all courses can be finished).

Time Complexity: O(V + E)  
- V = number of courses, E = number of prerequisites.  
- Each course and prerequisite edge is processed once.
Space Complexity: O(V + E)  
- Adjacency list + indegree array + queue storage.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _210_Course_Schedule_II {
    // Method to find in which order we can finish the courses
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
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
            adjancencyList.get(prerequisites[i][1]).add(prerequisites[i][0]);
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

        // Initialize the size of the array
        int size = topologicalSort.size();

        // If topological sort does not exist then return the integer of 0
        if (size != numCourses) {
            return new int[0];
        }

        // Initialize the variable for the result array
        int[] result = new int[size];

        // Fill the result array
        for (int i = 0; i < size; i++) {
            result[i] = topologicalSort.get(i);
        }

        // Return the topological sort of the values
        return result;
    }

    // Main method to test findOrder
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };

        int[] result = findOrder(numCourses, prerequisites);

        System.out.print("The order in which we can do the courses are : ");

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
