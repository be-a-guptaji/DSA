/*
LeetCode Problem: https://leetcode.com/problems/process-tasks-using-servers/

Question: 1882. Process Tasks Using Servers

Problem Statement: You are given two 0-indexed integer arrays servers and tasks of lengths n​​​​​​ and m​​​​​​ respectively. servers[i] is the weight of the i​​​​​​th​​​​ server, and tasks[j] is the time needed to process the j​​​​​​th​​​​ task in seconds.

Tasks are assigned to the servers using a task queue. Initially, all servers are free, and the queue is empty.

At second j, the jth task is inserted into the queue (starting with the 0th task being inserted at second 0). As long as there are free servers and the queue is not empty, the task in the front of the queue will be assigned to a free server with the smallest weight, and in case of a tie, it is assigned to a free server with the smallest index.

If there are no free servers and the queue is not empty, we wait until a server becomes free and immediately assign the next task. If multiple servers become free at the same time, then multiple tasks from the queue will be assigned in order of insertion following the weight and index priorities above.

A server that is assigned task j at second t will be free again at second t + tasks[j].

Build an array ans​​​​ of length m, where ans[j] is the index of the server the j​​​​​​th task will be assigned to.

Return the array ans​​​​.

Example 1:
Input: servers = [3,3,2], tasks = [1,2,3,2,1,2]
Output: [2,2,0,2,1,2]
Explanation: Events in chronological order go as follows:
- At second 0, task 0 is added and processed using server 2 until second 1.
- At second 1, server 2 becomes free. Task 1 is added and processed using server 2 until second 3.
- At second 2, task 2 is added and processed using server 0 until second 5.
- At second 3, server 2 becomes free. Task 3 is added and processed using server 2 until second 5.
- At second 4, task 4 is added and processed using server 1 until second 5.
- At second 5, all servers become free. Task 5 is added and processed using server 2 until second 7.

Example 2:
Input: servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
Output: [1,4,1,4,1,3,2]
Explanation: Events in chronological order go as follows: 
- At second 0, task 0 is added and processed using server 1 until second 2.
- At second 1, task 1 is added and processed using server 4 until second 2.
- At second 2, servers 1 and 4 become free. Task 2 is added and processed using server 1 until second 4. 
- At second 3, task 3 is added and processed using server 4 until second 7.
- At second 4, server 1 becomes free. Task 4 is added and processed using server 1 until second 9. 
- At second 5, task 5 is added and processed using server 3 until second 7.
- At second 6, task 6 is added and processed using server 2 until second 7.

Constraints:
    servers.length == n
    tasks.length == m
    1 <= n, m <= 2 * 10^5
    1 <= servers[i], tasks[j] <= 2 * 10^5
*/

/*
Approach: Dual Min-Heap Event-driven Server Scheduling
Goal:
- Assign each task i (arriving at second i) to the
  free server with the lowest weight (ties broken
  by smallest index), and return the assignment
  array.
Core Idea:
- Maintain two heaps:
  - available: free servers, ordered by (weight,
    index).
  - unavailable: busy servers, ordered by
    (free_time, weight, index), so the soonest-
    freeing server (with correct tie-breaking) is
    always at the top.
- At each task i, first release all servers whose
  free_time <= i from unavailable into available,
  then assign the best available server. If no
  server is free, fast-forward to the earliest
  free_time in unavailable, release those servers,
  then assign.
Algorithm Steps:
1. Push all servers into available as
   {weight, index}.
2. For each task i:
   a. Release servers from unavailable whose
      free_time <= i: move them to available.
   b. If available is empty (no server free by
      time i), fast-forward to unavailable.peek()
      free_time and release all servers freeing
      at that exact time.
   c. Poll the best server from available.
   d. Record result[i] = server index.
   e. Push the server into unavailable with
      free_time = max(current_time, i) + tasks[i],
      where current_time is the fast-forwarded
      time if applicable (tracked implicitly via
      the free_time stored with the server).
3. Return result.
Why It Works:
- Separating available and unavailable heaps avoids
  scanning all servers for each task.
- The unavailable heap's (free_time, weight, index)
  ordering ensures that when multiple servers free
  simultaneously, they are released in the correct
  priority order into available.
- Fast-forwarding to the earliest free_time rather
  than simulating idle seconds one-by-one keeps the
  algorithm O(m log n) rather than O(T log n) where
  T is the total time span.
Time Complexity:
- O((n + m) log n)
where n is the number of servers and m is the
number of tasks. Each server is pushed and popped
from both heaps at most m times total.
Space Complexity:
- O(n)
for the two heaps holding at most n servers total
at any point.
Result:
- Returns the array of server indices assigned to
  each task in order.
*/

package StacksQueuesAndHeaps.Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the array ans
  public int[] assignTasks(int[] servers, int[] tasks) {
    // Initialize the length of tasks and servers
    int m = tasks.length;
    int n = servers.length;

    // Initialize the result array
    int[] result = new int[m];

    // Initialize the avaliable heap
    PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> compare(a, b));

    // Initialize the unavaliable heap
    PriorityQueue<int[]> unavailable = new PriorityQueue<>((a, b) -> compare(a, b));

    // Add the servers to available heap
    for (int i = 0; i < n; i++) {
      available.offer(new int[] { servers[i], i, 0 });
    }

    // Iterate over the tasks array
    for (int i = 0; i < m; i++) {
      // Get the server for unavailable heap
      while ((!unavailable.isEmpty() && unavailable.peek()[0] <= i) || available.isEmpty()) {
        // Get the server form the unavailable heap
        int[] server = unavailable.poll();

        // Add the server to the available heap
        available.offer(new int[] { server[1], server[2], server[0] });
      }

      // Get the server form the available server
      int[] server = available.poll();

      // Update the result array
      result[i] = server[1];

      // Add the server to the unavailable server
      unavailable.offer(new int[] { Math.max(server[2], i) + tasks[i], server[0], server[1] });
    }

    // Return the result array
    return result;
  }

  // Helper method for the comparision
  private int compare(int[] a, int[] b) {
    if (a[0] != b[0]) {
      return Integer.compare(a[0], b[0]);
    }

    if (a[1] != b[1]) {
      return Integer.compare(a[1], b[1]);
    }

    return Integer.compare(a[2], b[2]);
  }
}

// Main Class
public class _1882_Process_Tasks_Using_Servers {
  // Main method to test assignTasks
  public static void main(String[] args) {
    int[] servers = new int[] { 5, 1, 4, 3, 2 };
    int[] tasks = new int[] { 2, 1, 2, 4, 5, 2, 1 };

    int[] result = new Solution().assignTasks(servers, tasks);

    System.out.println("The array ans is : " + Arrays.toString(result));
  }
}
