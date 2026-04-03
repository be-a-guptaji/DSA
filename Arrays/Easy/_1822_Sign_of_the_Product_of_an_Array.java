/*
LeetCode Problem: https://leetcode.com/problems/sign-of-the-product-of-an-array/

Question: 1822. Sign of the Product of an Array

Problem Statement: Implement a function signFunc(x) that returns:

1 if x is positive.
-1 if x is negative.
0 if x is equal to 0.
You are given an integer array nums. Let product be the product of all values in the array nums.

Return signFunc(product).

Example 1:
Input: nums = [-1,-2,-3,-4,3,2,1]
Output: 1
Explanation: The product of all values in the array is 144, and signFunc(144) = 1

Example 2:
Input: nums = [1,5,0,2,-3]
Output: 0
Explanation: The product of all values in the array is 0, and signFunc(0) = 0

Example 3:
Input: nums = [-1,1,-1,1,-1]
Output: -1
Explanation: The product of all values in the array is -1, and signFunc(-1) = -1

Constraints:
1 <= nums.length <= 1000
-100 <= nums[i] <= 100
 */

/*
Approach: Sign Tracking (Parity of Negatives)

Goal:
- Determine the sign of the product of all elements in the array
  without computing the actual product.

Core Idea:
- The sign depends on:
    1. Presence of zero → product is 0.
    2. Count of negative numbers:
       - Even → positive product.
       - Odd → negative product.
- Track sign using a boolean toggle.

Algorithm Steps:
1. Initialize isPositive = true.
2. Traverse the array:
   - If element == 0 → return 0.
   - If element < 0 → flip isPositive.
3. After traversal:
   - If isPositive → return 1.
   - Else → return -1.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns:
    1 → positive product
   -1 → negative product
    0 → if any element is zero
*/

package Arrays.Easy;

// Solution Class
class Solution {
    // Method to find the product of all values in the array nums
    public int arraySign(int[] nums) {
        // Initialize the boolean variable for the sign
        boolean isPositive = true;

        // Iterate over the nums array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                isPositive = !isPositive;
            } else if (nums[i] == 0) {
                return 0;
            }
        }

        // Return the condition in the end
        return isPositive ? 1 : -1;
    }
}

public class _1822_Sign_of_the_Product_of_an_Array {
    // Main method to test arraySign
    public static void main(String[] args) {
        int[] nums = new int[] { 180, 165, 170 };

        int result = new Solution().arraySign(nums);

        System.out.println("The product of all values in the array nums is : " + result);
    }
}
