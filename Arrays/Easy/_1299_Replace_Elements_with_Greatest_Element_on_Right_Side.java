/*
LeetCode Problem: https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/

Question: 1299. Replace Elements with Greatest Element on Right Side

Problem Statement: Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.

Example 1:
Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
Explanation: 
- index 0 --> the greatest element to the right of index 0 is index 1 (18).
- index 1 --> the greatest element to the right of index 1 is index 4 (6).
- index 2 --> the greatest element to the right of index 2 is index 4 (6).
- index 3 --> the greatest element to the right of index 3 is index 4 (6).
- index 4 --> the greatest element to the right of index 4 is index 5 (1).
- index 5 --> there are no elements to the right of index 5, so we put -1.

Example 2:
Input: arr = [400]
Output: [-1]
Explanation: There are no elements to the right of index 0.

Constraints:

1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5
 */

/*
Approach: Right-to-Left Maximum Tracking

Goal:
Replace every element in the array with the greatest element among the elements
to its right. Replace the last element with -1.

Algorithm:
1. Initialize maxRight = -1.
2. Traverse the array from right to left:
   - Store the current element temporarily.
   - Replace the current element with maxRight.
   - Update maxRight = max(maxRight, stored value).
3. Continue until the beginning of the array.

Why It Works:
- Moving from right to left ensures maxRight always holds the maximum element
  seen so far to the right.
- In-place update avoids extra space.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

import java.util.Arrays;

public class _1299_Replace_Elements_with_Greatest_Element_on_Right_Side {
    // Method to find the maximum right element
    public static int[] replaceElements(int[] arr) {
        // Get the length of the array
        int length = arr.length;

        // Initialize the minimum variable
        int maximum = -1;

        // Iterate over the arr array to find the maximum right element
        for (int i = length - 1; i > -1; i--) {
            // Get the current index number
            int num = arr[i];

            // Update the arr[i] value
            arr[i] = maximum;

            // Update the maximum array
            maximum = Math.max(maximum, num);
        }

        // Return the arr array
        return arr;
    }

    // Main method to test replaceElements
    public static void main(String[] args) {
        int[] arr = new int[] { 17, 18, 5, 4, 6, 1 };

        int[] result = replaceElements(arr);

        System.out.println("The maximum right element is : " + Arrays.toString(result));
    }
}
