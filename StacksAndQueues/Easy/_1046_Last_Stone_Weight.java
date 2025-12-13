/*
LeetCode Problem: https://leetcode.com/problems/last-stone-weight/

Question: 1046. Last Stone Weight

Problem Statement: You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.

Example 1:
Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.

Example 2:
Input: stones = [1]
Output: 1 

Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 1000
 */

/*
Approach: Max Heap Simulation

We simulate the process of repeatedly smashing the two heaviest stones until
at most one stone remains.

Data Structure:
- A max heap (PriorityQueue with reverse order) to always access the heaviest stones.

Algorithm:
- Insert all stone weights into the max heap.
- While the heap contains at least two stones:
  - Remove the heaviest stone.
  - Remove the second heaviest stone.
  - If their weights are different, insert the difference back into the heap.
- When the process ends:
  - If the heap is empty → return 0.
  - Otherwise → return the remaining stone’s weight.

Why It Works:
- The max heap guarantees efficient access to the two largest stones at each step.

Time Complexity: O(n log n)  
Space Complexity: O(n)
*/

package StacksAndQueues.Easy;

import java.util.Collections;
import java.util.PriorityQueue;

public class _1046_Last_Stone_Weight {
    // Method to find the weight of the last remaining stone
    public static int lastStoneWeight(int[] stones) {
        // Initialize the max heap to get the heaviest stone
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all the stones to the max heap
        for (int stone : stones) {
            maxHeap.offer(stone); // Add stone to the max heap
        }

        // Destroy the stones untill the max heap size is less than or equal to one
        while (maxHeap.size() >= 1) {
            // Get the top stone from the max heap
            int heaviestStone = maxHeap.poll();

            // If max heap is empty then break the loop
            if (maxHeap.isEmpty()) {
                maxHeap.offer(heaviestStone); // Retrun the heaviest stone to the max heap
                break;
            }

            // Get the second heaviest stone from the max heap
            int secondHeaviestStone = maxHeap.poll();

            // Get the difference of the two stones
            int difference = heaviestStone - secondHeaviestStone;

            // Add the difference of the stone if it is not zero
            if (difference != 0) {
                maxHeap.offer(difference); // Retrun the difference of the stone to the max heap
            }
        }

        // Return zero if the max heap is empty else return the top element to of the
        // max heap
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    // Main method to test lastStoneWeight
    public static void main(String[] args) {
        int[] stones = new int[] { 2, 7, 4, 1, 8, 1 };

        int result = lastStoneWeight(stones);

        System.out.println("The weight of the last remaining stone is : " + result);
    }
}
