/*
LeetCode Problem: https://leetcode.com/problems/contains-duplicate/

Question: 217. Contains Duplicate

Problem Statement: Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.



Example 1:

Input: nums = [1,2,3,1]

Output: true

Explanation:

The element 1 occurs at the indices 0 and 3.

Example 2:

Input: nums = [1,2,3,4]

Output: false

Explanation:

All elements are distinct.

Example 3:

Input: nums = [1,1,1,3,3,4,3,2,4,2]

Output: true



Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */

/*
Approach: We use a HashSet to track elements we've seen while iterating through the array. If we encounter a number already in the set, we return true (duplicate found). If not, we add it to the set. After checking all elements, we return false if no duplicates are found.

Time Complexity: O(n), One pass through nums, and HashSet.contains & add are O(1)
Space Complexity: O(n), In the worst case (no duplicates), we store all n elements in the set
 */

package Arrays.Easy;

public class _217_Contains_Duplicate {
    // Method to find duplicates
    public static boolean containsDuplicate(int[] nums) {
        // Use a hashset to store number
        java.util.Set<Integer> map = new java.util.HashSet<>();

        // Iterate over the array to find the duplicate
        for (int i = 0; i < nums.length; i++) {
            // If the map contain the key return true
            if (map.contains(nums[i])) {
                return true;
            }
            // If the map does not contain the key then add the number to the hashset
            else {
                map.add(nums[i]);
            }
        }

        // If the end of array has been reached then return false
        return false;
    }

    // Main method to test containsDuplicate
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 1 };

        boolean result = containsDuplicate(nums);

        if (result) {
            System.out.println("The array nums contain duplicate.");
        } else {
            System.out.println("The array nums does not contain duplicate.");
        }
    }
}
