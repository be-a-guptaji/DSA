/*
LeetCode Problem: https://leetcode.com/problems/my-calendar-i/

Question: 729. My Calendar I

Problem Statement: You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.

A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).

The event can be represented as a pair of integers startTime and endTime that represents a booking on the half-open interval [startTime, endTime), the range of real numbers x such that startTime <= x < endTime.

Implement the MyCalendar class:

MyCalendar() Initializes the calendar object.
boolean book(int startTime, int endTime) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Example 1:
Input
["MyCalendar", "book", "book", "book"]
[[], [10, 20], [15, 25], [20, 30]]
Output
[null, true, false, true]

Explanation
MyCalendar myCalendar = new MyCalendar();
myCalendar.book(10, 20); // return True
myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.

Constraints:

0 <= start < end <= 10^9
At most 1000 calls will be made to book.
 */

/*
Approach:
1. We are given booking requests where each booking is represented as an interval
   [startTime, endTime). We must accept the booking only if it does not overlap
   with any previously booked interval.

2. To achieve this, we maintain a Binary Search Tree (BST) where each node stores:
      • start  → start time of the interval
      • end    → end time of the interval
      • left   → all intervals that end before this interval starts
      • right  → all intervals that start after this interval ends

3. For each booking request [s, e]:
      • If s >= current.end:
           The interval lies completely after the current interval,
           so we move to the right subtree.
      • Else if e <= current.start:
           The interval lies completely before the current interval,
           so we move to the left subtree.
      • Else:
           The intervals overlap, so we cannot book this interval.

4. During traversal:
      • If we reach a null child node, it means the new interval fits
        without any conflict. We insert a new BST node here and return true.
      • If a conflict is found at any time, return false.

5. This BST structure ensures:
      • Non-overlapping intervals go either entirely to the left or right.
      • Overlapping intervals are immediately detected.

Time Complexity: O(n) in the worst case (skewed BST), O(log n) on average.

Space Complexity: O(n) for storing all booked intervals.
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.List;

public class _729_My_Calendar_I {
    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(startTime,endTime);
     */

    // Class to make the MyCalendar
    private static class MyCalendar {
        // Initialize the root node for the BST
        private BST root;

        public MyCalendar() {
            // Initialize the root node of BST with null
            this.root = null;
        }

        public boolean book(int startTime, int endTime) {
            // If root node is null then initialize the root node with the start and end
            // time and return true else
            if (this.root == null) {
                // Initialize the root node with the startTime and endTime
                this.root = new BST(startTime, endTime);

                // Return true
                return true;
            }

            // Call the BST insert method on the startTime and endTime and return the result
            return root.insert(startTime, endTime);
        }

        // Helper class to make a BST
        private class BST {
            // Initialize variable for the left and right node
            private BST left;
            private BST right;

            // Initialize variable for the start and end of the range
            private final int start;
            private final int end;

            // Constructor for the helper class
            public BST(int startTime, int endTime) {
                // Initialize the left and right node pointers
                this.left = null;
                this.right = null;

                // Initialize the start and end range of the interval
                this.start = startTime;
                this.end = endTime;
            }

            // Method to insert new node in the BST
            private boolean insert(int startTime, int endTime) {
                // Initialize the current variable for traversing
                BST current = this;

                // Iterate over the BST to find the position
                while (true) {
                    if (startTime >= current.end) { // If startTime is greater then the end of the current node then
                                                    // move the current pointer to the right node
                        // If current right pointer is null then add the node to the pointer and return
                        // true
                        if (current.right == null) {
                            current.right = new BST(startTime, endTime);
                            return true;
                        }
                        current = current.right;
                    } else if (endTime <= current.start) { // Else if end is less then the start of the current node then
                                                       // move the current pointer to the left node
                        // If current left pointer is null then add the node to the pointer and return
                        // true
                        if (current.left == null) {
                            current.left = new BST(startTime, endTime);
                            return true;
                        }
                        current = current.left;
                    } else { // Else if we find a conflict then return false
                        return false;
                    }
                }
            }
        }
    }

    // Main method to test MyCalendar
    public static void main(String[] args) {

        String[] operations = {
                "MyCalendar", "book", "book", "book"
        };

        List<int[]> values = new ArrayList<>();
        values.add(new int[] {});
        values.add(new int[] { 10, 20 });
        values.add(new int[] { 15, 25 });
        values.add(new int[] { 20, 30 });

        // Create an instance of MyCalendar
        MyCalendar myCalendar = new MyCalendar();

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("MyCalendar")) {
                myCalendar = new MyCalendar();
                System.out.println("null");
            }
            if (operation.equals("book")) {
                System.out.println(myCalendar.book(values.get(i)[0], values.get(i)[1]));
            }
        }
    }
}
