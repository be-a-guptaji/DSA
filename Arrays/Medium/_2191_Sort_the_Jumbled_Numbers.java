/*
LeetCode Problem: https://leetcode.com/problems/sort-the-jumbled-numbers/

Question: 2191. Sort the Jumbled Numbers

Problem Statement: You are given a 0-indexed integer array mapping which represents the mapping rule of a shuffled decimal system. mapping[i] = j means digit i should be mapped to digit j in this system.

The mapped value of an integer is the new integer obtained by replacing each occurrence of digit i in the integer with mapping[i] for all 0 <= i <= 9.

You are also given another integer array nums. Return the array nums sorted in non-decreasing order based on the mapped values of its elements.

Notes:

Elements with the same mapped values should appear in the same relative order as in the input.
The elements of nums should only be sorted based on their mapped values and not be replaced by them.

Example 1:
Input: mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
Output: [338,38,991]
Explanation: 
Map the number 991 as follows:
1. mapping[9] = 6, so all occurrences of the digit 9 will become 6.
2. mapping[1] = 9, so all occurrences of the digit 1 will become 9.
Therefore, the mapped value of 991 is 669.
338 maps to 007, or 7 after removing the leading zeros.
38 maps to 07, which is also 7 after removing leading zeros.
Since 338 and 38 share the same mapped value, they should remain in the same relative order, so 338 comes before 38.
Thus, the sorted array is [338,38,991].

Example 2:
Input: mapping = [0,1,2,3,4,5,6,7,8,9], nums = [789,456,123]
Output: [123,456,789]
Explanation: 789 maps to 789, 456 maps to 456, and 123 maps to 123. Thus, the sorted array is [123,456,789].

Constraints:
mapping.length == 10
0 <= mapping[i] <= 9
All the values of mapping[i] are unique.
1 <= nums.length <= 3 * 10^4
0 <= nums[i] < 10^9
*/

/*
Approach: Digit Mapping + Stable Sorting

Goal:
- Sort nums according to their mapped digit values.

Core Idea:
- Transform each number using the given digit mapping:
    mappedDigit = mapping[originalDigit]
- Preserve original order for equal mapped values
  using stable sorting behavior.
- Store:
    [mappedValue, originalIndex]
  to reconstruct final order.

Algorithm Steps:
1. Traverse nums array:
   - Convert each number into mapped value digit-by-digit.
   - Handle special case for 0.
   - Store:
       pairs[i] = [mappedValue, originalIndex]
2. Sort pairs by mappedValue.
3. Build result array using stored original indices.
4. Return result array.

Time Complexity:
- O(n * d + n log n)
  where d = number of digits

Space Complexity:
- O(n)

Result:
- Returns nums sorted according to mapped digit values.
*/

package Arrays.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the array nums sorted in non-decreasing order based on the
    // mapped values of its elements
    public int[] sortJumbled(int[] mapping, int[] nums) {
        // Get the length of the nums array
        int length = nums.length;

        // Initialize the pairs matrix of size length X 2
        int[][] pairs = new int[length][2];

        // Iterate over the nums array
        for (int index = 0; index < length; index++) {
            // Initialize the mappedNumber, base and num variable
            int mappedNumber = 0;
            int base = 1;
            int num = nums[index];

            // Update the mappedNumber variable
            if (num == 0) {
                mappedNumber = mapping[0];
            } else {
                // Make the new mappedNumber
                while (num != 0) {
                    int digit = num % 10;
                    num /= 10;
                    mappedNumber += base * mapping[digit];
                    base *= 10;
                }
            }

            // Update the pairs variable
            pairs[index][0] = mappedNumber;
            pairs[index][1] = index;
        }

        // Sort the pairs vairable
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // Initialize the new result array
        int[] result = new int[length];

        // Update the result variable
        for (int index = 0; index < length; index++) {
            result[index] = nums[pairs[index][1]];
        }

        // Return the result
        return result;
    }
}

public class _2191_Sort_the_Jumbled_Numbers {
    // Main method to test sortJumbled
    public static void main(String[] args) {
        int[] mapping = new int[] { 8, 9, 4, 0, 2, 1, 3, 5, 7, 6 };
        int[] nums = new int[] { 991, 338, 38 };

        int[] result = new Solution().sortJumbled(mapping, nums);

        System.out.println(
                "The array nums sorted in non-decreasing order based on the mapped values of its elements is : "
                        + Arrays.toString(result));
    }
}
