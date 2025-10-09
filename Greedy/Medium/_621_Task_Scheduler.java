/*
LeetCode Problem: https://leetcode.com/problems/task-scheduler/

Question: 621. Task Scheduler

Problem Statement: You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.

Return the minimum number of CPU intervals required to complete all tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
After completing task A, you must wait two intervals before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.

Example 2:
Input: tasks = ["A","C","A","B","D","B"], n = 1
Output: 6
Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:
Input: tasks = ["A","A","A", "B","B","B"], n = 3
Output: 10
Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.

Constraints:

1 <= tasks.length <= 10^4
tasks[i] is an uppercase English letter.
0 <= n <= 100
*/

/*
Approach:
This problem is solved using a Greedy strategy with the help of a Max Heap and a Queue.

- Each task is a capital letter from A to Z. We may have to execute the same task multiple times.
- After executing a task, it must wait for 'n' intervals before it can be executed again.
- To minimize the total time (including idle time), we always want to execute the task with the highest frequency first.

Steps:
1. Count the frequency of each task using an array of size 26 (for each letter A-Z).
2. Use a Max Heap (PriorityQueue in reverse order) to always fetch the task with the highest remaining frequency.
3. Use a Queue to manage cooldowns. Each item in the queue is an array: [remaining count, time it becomes available again].
4. Use a 'clock' variable to simulate each unit of time:
   - At each time unit, increment the clock.
   - If the heap is not empty, execute the most frequent task and decrement its count.
     - If the task still has remaining executions, put it into the cooldown queue with its next available time (current time + n).
   - Check the front of the cooldown queue. If the cooldown period is over (i.e., current time equals available time),
     move the task back into the heap so it can be scheduled again.
5. Repeat until both the heap and the cooldown queue are empty.

The clock value at the end represents the minimum time required to execute all tasks with the given cooldown.

Time Complexity: O(n log n), where n is the number of tasks (due to heap operations).
Space Complexity: O(1), since the task types are fixed (only 26 possible letters), though extra space is used for the heap and queue.
*/

package Greedy.Medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class _621_Task_Scheduler {
    // Method to find the minimum number of CPU interval to complete all tasks
    public static int leastInterval(char[] tasks, int n) {
        // If cooldown is zero then return the task length
        if (n == 0) {
            return tasks.length;
        }

        // Make a frequency array for the frequency count of the tasks
        int[] frequency = new int[26];

        // Fill the frequency array with the value of the character frequency
        for (char ch : tasks) {
            frequency[ch - 'A']++;
        }

        // Initialize a max heap for the storing the frequency
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Fill the maxHeap with the frequency of the characters
        for (int freq : frequency) {
            // Store the frequency in the max heap if it is not zero
            if (freq != 0) {
                maxHeap.offer(freq);
            }
        }

        // Initialize a clock for keeping track of the time
        int clock = 0;

        // Initialize a queue for the task scheduling
        Queue<int[]> queue = new LinkedList<>();

        // Iterate over the map untill the value set is empty
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            // Increment the clock time
            clock++;

            // If max heap is not empty then pop the value form the max heap
            if (!maxHeap.isEmpty()) {
                int count = maxHeap.poll() - 1;
                // If the count is not zero then add it to the queue
                if (count != 0) {
                    queue.add(new int[] { count, clock + n });
                }
            }

            // Get the queue first value to check if the value time to proccess a task has
            // been cool down or not
            int[] coolDown = queue.peek();

            // If cool down time is equal to clock time than add it to the max heap again
            // and remove it from the queue also
            if (coolDown != null && coolDown[1] == clock) {
                queue.poll();
                maxHeap.offer(coolDown[0]);
            }
        }

        // Return the clock time
        return clock;
    }

    // Main method to test leastInterval
    public static void main(String[] args) {
        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
        int n = 3;

        int result = leastInterval(tasks, n);

        System.out.println("The minimum number of CPU interval to complete all tasks is : " + result);
    }
}
