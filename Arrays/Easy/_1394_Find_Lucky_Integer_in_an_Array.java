/*
LeetCode Problem: https://leetcode.com/problems/find-lucky-integer-in-an-array/

Question: 1394. Find Lucky Integer in an Array

Problem Statement: Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.

Return the largest lucky integer in the array. If there is no lucky integer return -1.

Example 1:
Input: arr = [2,2,3,4]
Output: 2
Explanation: The only lucky number in the array is 2 because frequency[2] == 2.

Example 2:
Input: arr = [1,2,2,3,3,3]
Output: 3
Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.

Example 3:
Input: arr = [2,2,2,3,3]
Output: -1
Explanation: There are no lucky numbers in the array.

Constraints:

1 <= arr.length <= 500
1 <= arr[i] <= 500
 */

/*
Approach: Frequency Counting with Reverse Scan

Goal:
Find the largest “lucky” integer in the array.
A lucky integer is one whose value equals its frequency in the array.

Key Idea:
Count how many times each number appears, then check which numbers
satisfy value == frequency.

Algorithm:
1. Create a frequency array of size 501 (since values are ≤ 500).
2. Traverse the input array and increment frequency[num].
3. Traverse the frequency array from 500 down to 1:
   - If frequency[i] == i, return i immediately.
4. If no lucky integer is found, return −1.

Why It Works:
- Frequency counting enables constant-time checks.
- Reverse traversal ensures the largest lucky integer is returned.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1394_Find_Lucky_Integer_in_an_Array {
    // Method to find the lucky integer in an array
    public static int findLucky(int[] arr) {
        // Initialize the array of size 501
        int[] frequency = new int[501];

        // Fill the frequency array
        for (int num : arr) {
            frequency[num]++;
        }

        // Iterate over the frequency array for finding the lucky number
        for (int i = 500; i > 0; i--) {
            if (i == frequency[i]) {
                return i;
            }
        }

        // Return the lucky integer
        return -1;
    }

    // Main method to test findLucky
    public static void main(String[] args) {
        int[] arr = new int[] { 17, 18, 5, 4, 6, 1 };

        int result = findLucky(arr);

        System.out.println("The lucky integer in the array is : " + result);
    }
}
