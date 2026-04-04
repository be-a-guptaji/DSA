/*
LeetCode Problem: https://leetcode.com/problems/find-the-difference-of-two-arrays/

Question: 2215. Find the Difference of Two Arrays

Problem Statement: Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:

answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.

Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
Explanation:
For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums1. Therefore, answer[1] = [4,6].

Example 2:
Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
Output: [[3],[]]
Explanation:
For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
Every integer in nums2 is present in nums1. Therefore, answer[1] = [].

Constraints:
1 <= nums1.length, nums2.length <= 1000
-1000 <= nums1[i], nums2[i] <= 1000
 */

/*
Approach: Boolean Array as Hash Set (Set Difference)

Goal:
- Find distinct elements present in nums1 but not in nums2,
  and vice versa.

Core Idea:
- Use fixed-size boolean arrays to simulate sets
  (since values are in range [-1000, 1000]).
- Mark presence of elements.
- Remove common elements by resetting flags.
- Collect remaining unique elements.

Algorithm Steps:
1. Initialize two boolean arrays (set1, set2).
2. Mark elements of nums1 in set1.
3. Mark elements of nums2 in set2.
4. Remove common elements:
   - For each element in nums2 → set1[value] = false.
   - For each element in nums1 → set2[value] = false.
5. Collect unique elements:
   - Traverse nums1 → add values still true in set1.
   - Traverse nums2 → add values still true in set2.
   - Reset flags after adding to avoid duplicates.
6. Return both lists.

Time Complexity:
- O(n + m)
  n = size of nums1, m = size of nums2

Space Complexity:
- O(1) (constant size arrays)

Result:
- Returns two lists:
  1. Elements in nums1 but not in nums2.
  2. Elements in nums2 but not in nums1.
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
    // Method to find the product of all values in the array nums
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // Initialize the list of list of integer
        List<List<Integer>> result = new ArrayList<>();

        // Initialize the two boolean array for the set
        boolean[] set1 = new boolean[2001];
        boolean[] set2 = new boolean[2001];

        // Fill the set1 for nums1
        for (int i = 0; i < nums1.length; i++) {
            set1[nums1[i] + 1000] = true;
        }

        // Fill the set2 for nums2
        for (int i = 0; i < nums2.length; i++) {
            set2[nums2[i] + 1000] = true;
        }

        // Swap the value of the set1 if the element is in nums2
        for (int i = 0; i < nums2.length; i++) {
            set1[nums2[i] + 1000] = false;
        }

        // Swap the value of the set2 if the element is in nums1
        for (int i = 0; i < nums1.length; i++) {
            set2[nums1[i] + 1000] = false;
        }

        // Initialize the list of difference1
        ArrayList<Integer> difference1 = new ArrayList<>();

        // Add element from the set1 if they are set to true
        for (int i = 0; i < nums1.length; i++) {
            if (set1[nums1[i] + 1000]) {
                difference1.add(nums1[i]);
                set1[nums1[i] + 1000] = false;
            }
        }

        // Add the difference1 to the result list
        result.add(difference1);

        // Initialize the list of difference2
        ArrayList<Integer> difference2 = new ArrayList<>();

        // Add element from the set2 if they are set to true
        for (int i = 0; i < nums2.length; i++) {
            if (set2[nums2[i] + 1000]) {
                difference2.add(nums2[i]);
                set2[nums2[i] + 1000] = false;
            }
        }

        // Add the difference2 to the result list
        result.add(difference2);

        // Return the result list
        return result;
    }
}

public class _2215_Find_the_Difference_of_Two_Arrays {
    // Main method to test findDifference
    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 2, 3 };
        int[] nums2 = new int[] { 2, 4, 6 };

        List<List<Integer>> result = new Solution().findDifference(nums1, nums2);

        System.out.println("The product of all values in the array nums is : " + result);
    }
}
