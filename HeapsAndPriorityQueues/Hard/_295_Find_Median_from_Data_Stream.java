/*
LeetCode Problem: https://leetcode.com/problems/find-median-from-data-stream/

Question: 295. Find Median from Data Stream

Problem Statement: The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

Constraints:

-10^5 <= num <= 10^5
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.

Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
*/

/*
Approach:
We maintain two heaps — a max heap to store the smaller half of the numbers and a min heap to store the larger half.
When a new number arrives, we decide which heap it belongs to based on comparison with the max heap's top.
After inserting, we rebalance the heaps so that their sizes differ by at most one.
If both heaps are balanced (same size), the median is the average of the top elements of both heaps.
Otherwise, the median is the top element of the max heap, which always holds the extra element when the total count is odd.

Time Complexity: O(log n) for each insertion due to heap operations.
Space Complexity: O(n) to store all the numbers in the heaps.
*/

package HeapsAndPriorityQueues.Hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class _295_Find_Median_from_Data_Stream {
    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    // Class to implement the MedianFinder methods
    static class MedianFinder {
        // Initialize a min and max heap for the storing the value
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            // Initialize the min and max heaps
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // Add to the max heap if it is empty or the top value of max heap is bigger
            // than the number else add to the min heap
            if (maxHeap.isEmpty() || num < maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // Balancing both the heaps according to size
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

        }

        public double findMedian() {
            // If both heaps has the same size than return the peek of the both heap divided
            // by 2.0 else the max heap peek
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                return maxHeap.peek();
            }
        }
    }

    // Main method to test the class MedianFinder
    public static void main(String[] args) {
        MedianFinder result = new MedianFinder();

        String[] operations = { "MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian" };
        int[][] number = { {}, { 1 }, { 2 }, {}, { 3 }, {} };
        String[] answer = new String[operations.length];
        int index = 0;

        operations = Arrays.copyOfRange(operations, 1, operations.length);
        answer[index++] = null;

        for (String operation : operations) {
            if (operation.equals("addNum")) {
                result.addNum(number[index][0]);
                answer[index++] = null;
            } else {
                answer[index++] = String.valueOf(result.findMedian());
            }
        }

        System.out.println("The answer list of all the operations are : " + Arrays.toString(answer));
    }
}
