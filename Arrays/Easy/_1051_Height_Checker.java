/*
LeetCode Problem: https://leetcode.com/problems/height-checker/

Question: 1051. Height Checker

Problem Statement: A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.

You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).

Return the number of indices where heights[i] != expected[i].

Example 1:
Input: heights = [1,1,4,2,1,3]
Output: 3
Explanation: 
heights:  [1,1,4,2,1,3]
expected: [1,1,1,2,3,4]
Indices 2, 4, and 5 do not match.

Example 2:
Input: heights = [5,1,2,3,4]
Output: 5
Explanation:
heights:  [5,1,2,3,4]
expected: [1,2,3,4,5]
All indices do not match.

Example 3:
Input: heights = [1,2,3,4,5]
Output: 0
Explanation:
heights:  [1,2,3,4,5]
expected: [1,2,3,4,5]
All indices match.

Constraints:

1 <= heights.length <= 100
1 <= heights[i] <= 100
*/

/*
Approach: Counting Sort Comparison

Goal:
Count how many indices differ between the original heights array
and the array sorted in non-decreasing order.

Key Idea:
Instead of explicitly sorting the array, use Counting Sort because
height values are limited (1 to 100).

Algorithm:
1. Create a counting array of size 101 to store frequency of each height.
2. Traverse heights and populate the counting array.
3. Iterate through the original heights array again:
   - Move an index pointer through the counting array to find the next
     expected (sorted) height.
   - Compare the current height with the expected height.
   - If they differ, increment the difference counter.
   - Decrement the frequency of the expected height.
4. Return the total count of mismatches.

Why It Works:
- Counting sort reconstructs the sorted order without extra arrays.
- Comparing on the fly avoids building a separate sorted array.

Time Complexity: O(n + k)
- n = number of students
- k = range of heights (constant, 100)

Space Complexity: O(k) ≈ O(1)
*/

package Arrays.Easy;

public class _1051_Height_Checker {
    // Method to find the number of indices where heights[i] != expected[i]
    public static int heightChecker(int[] heights) {
        // Initialize the array of size 101
        int[] counting = new int[101];

        // Initialize the difference variable to keep track of the differences
        int difference = 0;

        // Fill the counting array
        for (int height : heights) {
            counting[height]++;
        }

        // Initialize the index varaible for the counting array
        int index = 0;

        // Compare the values of the to the expected values
        for (int height : heights) {
            // Get the first height from the counting array
            while (counting[index] == 0) {
                // Increment the index
                index++;
            }

            // If value miss match then increment the difference variable
            if (height != index) {
                difference++;
            }

            // Decrement the counting array index value
            counting[index]--;
        }

        // Return difference
        return difference;
    }

    // Main method to test heightChecker
    public static void main(String[] args) {
        int[] heights = new int[] { 1, 1, 4, 2, 1, 3 };

        int result = heightChecker(heights);

        System.out.println("The number of indices where heights[i] != expected[i] is : " + result);
    }
}
