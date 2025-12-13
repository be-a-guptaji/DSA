/*
LeetCode Problem: https://leetcode.com/problems/kth-largest-element-in-a-stream/

Question: 703. Kth Largest Element in a Stream

Problem Statement: You are part of a university admissions office and need to keep track of the kth highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.

You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously returns the kth highest test score after a new score has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.

Implement the KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest element in the pool of test scores so far.

Example 1:
Input:
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output: [null, 4, 5, 5, 8, 8]
Explanation:
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3); // return 4
kthLargest.add(5); // return 5
kthLargest.add(10); // return 5
kthLargest.add(9); // return 8
kthLargest.add(4); // return 8

Example 2:
Input:
["KthLargest", "add", "add", "add", "add"]
[[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]
Output: [null, 7, 7, 7, 8]
Explanation:
KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
kthLargest.add(2); // return 7
kthLargest.add(10); // return 7
kthLargest.add(9); // return 7
kthLargest.add(9); // return 8

Constraints:

0 <= nums.length <= 10^4
1 <= k <= nums.length + 1
-10^4 <= nums[i] <= 10^4
-10^4 <= val <= 10^4
At most 10^4 calls will be made to add.
 */

/*
Approach: Min Heap of Size K

To continuously track the k-th largest element in a stream, we maintain a min heap
that stores only the k largest elements seen so far.

Data Structure:
- A min heap (PriorityQueue) of fixed size k.
- The smallest element in the heap represents the k-th largest element overall.

Initialization:
- Insert each number from the initial array using the add() method.
- The heap never grows beyond size k.

Add Operation:
- If heap size < k → add the value directly.
- Else:
  - Compare the value with the heap’s minimum (peek).
  - If value is larger → remove the minimum and insert the value.
  - If value is smaller → ignore it.
- The heap top always holds the k-th largest element.

Why It Works:
- The heap contains exactly the k largest elements.
- The smallest among them is the k-th largest overall.

Time Complexity:
- add(): O(log k)
- constructor: O(n log k)
Space Complexity: O(k)
*/

package StacksAndQueues.Easy;

import java.util.PriorityQueue;

public class _703_Kth_Largest_Element_in_a_Stream {

    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */

    // Class to make the KthLargest
    private static class KthLargest {
        // Initialize the min heap for the kth largest elements
        private final PriorityQueue<Integer> minHeap;

        // Initialize the variable to hold the size of the k
        private final int size;

        public KthLargest(int k, int[] nums) {
            // Initialize the min heap
            this.minHeap = new PriorityQueue<>();

            // Initialize the size
            this.size = k;

            // Add the numbers to the min heap
            for (int n : nums) {
                this.add(n);
            }
        }

        public final int add(int val) {
            // If the size of the min heap is less then the size variable then add the val
            // to the min heap
            if (this.size > this.minHeap.size()) {
                this.minHeap.add(val);
            } else {
                // Get the smallest number from the min heap
                int minimumElement = this.minHeap.peek();

                // If minimumElement is less then the val then remove the
                // minimumElement from the min heap and add the current number
                if (minimumElement < val) {
                    // Remove the current samllest number from the min heap
                    this.minHeap.poll();

                    // Add the val to the min heap
                    this.minHeap.add(val);
                }
            }
            // Return the top element from the min heap
            return this.minHeap.peek();
        }
    }

    // Main method to test KthLargest
    public static void main(String[] args) {
        String[] operations = { "KthLargest", "add", "add", "add", "add", "add" };
        Object[] values = {
                new Object[] { 1, new int[0] },
                new Object[] { -3 },
                new Object[] { -2 },
                new Object[] { -4 },
                new Object[] { 0 },
                new Object[] { 4 }
        };

        KthLargest kthLargest = new KthLargest(1, new int[0]);

        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            if (op.equals("KthLargest")) {
                int k = (int) ((Object[]) values[i])[0];
                int[] nums = (int[]) ((Object[]) values[i])[1];
                kthLargest = new KthLargest(k, nums);
                System.out.println("null");
            } else if (op.equals("add")) {
                int val = (int) ((Object[]) values[i])[0];
                System.out.println(kthLargest.add(val));
            }
        }
    }
}
