/*
LeetCode Problem: https://leetcode.com/problems/single-element-in-a-sorted-array/

Question: 540. Single Element in a Sorted Array

Problem Statement: You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Example 1:
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:
Input: nums = [3,3,7,7,10,11,11]
Output: 10

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5
 */

/*
Approach: Binary Search using Index Parity

Goal:
- Find the single element in a sorted array where every other element
  appears exactly twice.

Core Idea:
- In a properly paired array:
  - First index of each pair appears at an even index.
  - Second index appears at the next odd index.
- Once the single element appears, this pairing pattern breaks.
- Use binary search and index parity to detect the side containing the single element.

Algorithm Steps:
1. Initialize left = 0 and right = n - 1.
2. Compute mid = (left + right) / 2.
3. Check index parity of mid:
   - If mid is even:
       - If nums[mid] == nums[mid + 1] → single element is on right side.
       - Else → single element is on left side.
   - If mid is odd:
       - If nums[mid] == nums[mid + 1] → single element is on left side.
       - Else → single element is on right side.
4. Adjust search bounds accordingly.
5. When search ends, nums[left] is the single element.

Time Complexity:
- O(log n)

Space Complexity:
- O(1)

Result:
- Returns the element that appears exactly once.
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
  // Method to find the single element that appears only once
  public int singleNonDuplicate(int[] nums) {
    // Initialize the left and right pointer
    int left = 0, right = nums.length - 1;

    // Iterate over the nums array
    while (left <= right) {
      // Get the middle element
      int mid = (left + right) >> 1;

      if ((mid & 1) == 0) {
        if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      } else {
        if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
    }

    // Return the left index of the nums
    return nums[left];
  }
}

public class _540_Single_Element_in_a_Sorted_Array {
  // Main method to test singleNonDuplicate
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 };

    int result = new Solution().singleNonDuplicate(nums);

    System.out.println("The single element that appears only once is : " + result);
  }
}
