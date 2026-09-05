/*
LeetCode Problem: https://leetcode.com/problems/single-threaded-cpu/

Question: 1834. Single-Threaded CPU

Problem Statement: You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

    If the CPU is idle and there are no available tasks to process, the CPU remains idle.
    If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
    Once a task is started, the CPU will process the entire task without stopping.
    The CPU can finish a task then start a new one instantly.

Return the order in which the CPU will process the tasks.

Example 1:
Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows: 
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.

Example 2:
Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
Output: [4,3,2,0,1]
Explanation: The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.

Constraints:​​​​​​​
    1 <= tasks.length <= 10^5
    tasks[i] = [enqueueTimei, processingTimei]
    1 <= enqueueTimei, processingTimei <= 10^9
*/

/*
Approach: Event-driven Simulation with Dual Sort and Min-Heap Scheduling
Goal:
- Determine the order in which a single CPU
  processes tasks, where the CPU always picks the
  available task with the shortest processing time
  (ties broken by smaller index), and jumps forward
  in time if no task is available.
Core Idea:
- Tasks become available at their enqueue time;
  the CPU can only start a task after it becomes
  available.
- Sort tasks by enqueue time to efficiently find
  which tasks have become available by the current
  time.
- Use a min-heap ordered by processing time (then
  index) to always select the optimal next task
  among all currently available ones.
- If the heap is empty when the CPU is free, jump
  time forward to the next task's enqueue time
  rather than simulating idle cycles one by one.
Algorithm Steps:
1. Create indices[] = [0..n-1] and sort by
   tasks[i][0] (enqueue time), ties broken by
   index.
2. Initialize a min-heap ordered by tasks[i][1]
   (processing time), ties broken by index.
3. Initialize time = 0, index = 0 (pointer into
   sorted indices), resultIndex = 0.
4. While the heap is non-empty or index < length:
   a. Enqueue all tasks whose enqueue time <=
      current time: push indices[index] onto the
      heap and advance index.
   b. If heap is empty (CPU is idle), jump time
      forward to tasks[indices[index]][0] (the
      next available task's enqueue time) and
      repeat step 4a.
   c. Otherwise, poll the heap to get the best
      available task nextIndex:
      - Add tasks[nextIndex][1] to time (task
        completes at this new time).
      - Record nextIndex in result[resultIndex++].
5. Return result.
Why It Works:
- Sorting by enqueue time allows a pointer sweep
  to add tasks to the heap in O(log n) per task
  exactly when they become available, avoiding
  repeated full scans.
- The min-heap invariant always surfaces the
  shortest available task (with index tie-breaking),
  matching the CPU's scheduling rule exactly.
- Jumping time forward on idle avoids O(t) idle
  simulation, reducing the time complexity to
  depend on n rather than the total time span.
Time Complexity:
- O(n log n)
for sorting indices and for n heap insertions and
extractions, each O(log n).
Space Complexity:
- O(n)
for the indices array, min-heap, and result array.
Result:
- Returns the array of task indices in the order
  the CPU processes them.
*/

package StacksQueuesAndHeaps.Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the order in which the CPU will process the tasks
  public int[] getOrder(int[][] tasks) {
    // Initialize the length variable
    int length = tasks.length;

    // Initialize the result array
    int[] result = new int[length];

    // Initialize the indices
    Integer[] indices = new Integer[length];

    // Fill the info matrix
    for (int i = 0; i < length; i++) {
      indices[i] = i;
    }

    // Sort the tasks array
    Arrays.sort(indices, (a, b) -> tasks[a][0] != tasks[b][0] ? tasks[a][0] - tasks[b][0] : a - b);

    // Initailize the min heap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(
        (a, b) -> tasks[a][1] != tasks[b][1] ? tasks[a][1] - tasks[b][1] : a - b);

    // Initialize the time
    long time = 0;

    // Initialize the index and resultIndex
    int index = 0;
    int resultIndex = 0;

    // Iterate over the minHeap
    while (!minHeap.isEmpty() || index < length) {
      // Add the index untill the length is less than length and time is less than
      // time
      while (index < length && tasks[indices[index]][0] <= time) {
        minHeap.offer(indices[index]);
        index++;
      }

      // Update the result array
      if (minHeap.isEmpty()) {
        time = tasks[indices[index]][0];
      } else {
        int nextIndex = minHeap.poll();
        time += tasks[nextIndex][1];
        result[resultIndex++] = nextIndex;
      }
    }

    // Return the result array
    return result;
  }
}

// Main Class
public class _1834_Single_Threaded_CPU {
  // Main method to test getOrder
  public static void main(String[] args) {
    int[][] tasks = new int[][] {};

    int[] result = new Solution().getOrder(tasks);

    System.out.println("The order in which the CPU will process the tasks is : " + Arrays.toString(result));
  }
}
