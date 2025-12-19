/*
LeetCode Problem: https://leetcode.com/problems/hand-of-straights/

Question: 846. Hand of Straights

Problem Statement: Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.

Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.

Example 1:
Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]

Example 2:
Input: hand = [1,2,3,4,5], groupSize = 4
Output: false
Explanation: Alice's hand can not be rearranged into groups of 4.

Constraints:

1 <= hand.length <= 10^4
0 <= hand[i] <= 10^9
1 <= groupSize <= hand.length
*/

/*
Approach: Greedy + Map + Min Heap

Goal:
Determine if array ‘hand’ can be rearranged into groups of consecutive values,
each of size groupSize.

Key Observations:
- If groups are possible, we must always start forming a group from the smallest
  available card.
- For each smallest card x, we must check x, x+1, ..., x+groupSize−1 exist.

Steps:

1. If total cards is not divisible by groupSize → impossible → return false.

2. Build a frequency map (count) of all card values.

3. Create a min heap of the distinct card values.
   - This allows always extracting the current smallest available value.

4. While heap not empty:
   - Let first = heap.peek() be the start of the next group.
   - For each value i from first to first + groupSize − 1:
       • If i missing in count → cannot form required group.
       • Decrement count[i].
       • If count[i] drops to zero:
           ◦ i must match heap.peek(), otherwise the heap still contains
             smaller value(s) violating ordering.
           ◦ Remove i from heap.

5. If all heap entries processed without failure → grouping possible.

Why It Works:
- Maintaining sorted order via heap ensures greedy formation from smallest available card.
- Frequency tracking ensures consecutive existence for required group lengths.

Time Complexity:
- Frequency map + heap build: O(n log n)
- Processing groups: O(n log n)
Space Complexity: O(n)
*/

package Greedy.Medium;

import java.util.HashMap;
import java.util.PriorityQueue;

public class _846_Hand_of_Straights {
    // Method to find if Alice can rearrange the cards into groups so that each
    // group is of size groupSize
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        // If hand can not be divided into groups of groupSize then return false
        if (hand.length % groupSize != 0) {
            return false;
        }

        // Initialize the hash map to store the frequency of the numbers
        HashMap<Integer, Integer> count = new HashMap<>();

        // Fill the hash map with the hand number frequecy
        for (int card : hand) {
            count.put(card, count.getOrDefault(card, 0) + 1);
        }

        // Initialize the min heap to get the minimum value of the card
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(count.keySet());

        // Iterate over the min heap until min heap is empty
        while (!minHeap.isEmpty()) {
            // Get the top element of the min heap
            int first = minHeap.peek();

            // Check if all the values first + groupSize are present in the count hash map
            for (int i = first; i < first + groupSize; i++) {
                // If the number is not present in the count hash map then return false
                if (!count.containsKey(i)) {
                    return false;
                }

                // Decrement the frequency in the count hash map
                count.put(i, count.get(i) - 1);

                // If frequency of i become zero then pop the element from the min heap
                if (count.get(i) == 0) {
                    // If the i is not equal to the min heap top value then return false
                    if (i != minHeap.peek()) {
                        return false;
                    }

                    // Remove the element from the min heap
                    minHeap.poll();
                }
            }
        }

        // Return true if every thing goes well
        return true;
    }

    // Main method to test isNStraightHand
    public static void main(String[] args) {
        int[] hand = new int[] { 1, 2, 3, 6, 2, 3, 4, 7, 8 };
        int groupSize = 3;

        boolean result = isNStraightHand(hand, groupSize);

        System.out.println("Alice can " + (result ? "" : "not ")
                + "rearrange the cards into groups so that each group is of size groupSize.");
    }
}
