/*
LeetCode Problem: https://leetcode.com/problems/sequential-digits/

Question: 1291. Sequential Digits

Problem Statement: An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:
Input: low = 100, high = 300
Output: [123,234]

Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]

Constraints:
10 <= low <= high <= 10^9
*/

/*
Approach: Sequential Number Generation

Goal:
- Generate all integers within [low, high]
  whose digits are sequential increasing digits.

Core Idea:
- Sequential numbers are formed by:
    1, 2, 3, ...
- Generate numbers digit-by-digit starting
  from every possible starting digit.
- Only consider lengths between:
    digits(low) and digits(high)

Algorithm Steps:
1. Compute:
   - lowDigit = number of digits in low
   - highDigit = number of digits in high
2. For each possible digit length:
   - Try starting digits from 1 to 9.
3. Construct sequential number:
   - Append increasing next digits.
4. If generated number lies within range:
   - Add to result.
5. Return result list.

Time Complexity:
- O(1)
  since at most a few sequential numbers exist

Space Complexity:
- O(1)
  excluding output list

Result:
- Returns all sequential digit numbers in sorted order.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
  // Method to find a sorted list of all the integers in the range [low, high]
  // inclusive that have sequential digits
  public List<Integer> sequentialDigits(int low, int high) {
    // Initialize the list of integer for the result
    ArrayList<Integer> result = new ArrayList<>();

    // Initialize the lowDigit and highDigit
    int lowDigit = String.valueOf(low).length();
    int highDigit = String.valueOf(high).length();

    // Iterate over the range of the lowDigit and highDigit
    for (int digits = lowDigit; digits <= highDigit; digits++) {
      // Iterate over the range 1 to 10
      for (int start = 1; start < 10; start++) {
        // If start + digits are more than the 10 then break out of the loop
        if (start + digits > 10) {
          break;
        }

        // Initialize the number and previous variable
        int number = start;
        int previous = start;

        // Form the number
        for (int i = 1; i < digits; i++) {
          number = number * 10 + (++previous);
        }

        // In number is in the range low and high then add it to the result
        if (number >= low && number <= high) {
          result.add(number);
        }
      }
    }

    // Return the result variable
    return result;
  }
}

public class _1291_Sequential_Digits {
  // Main method to test sequentialDigits
  public static void main(String[] args) {
    int low = 1000;
    int high = 13000;

    List<Integer> result = new Solution().sequentialDigits(low, high);

    System.out.println(
        "A sorted list of all the integers in the range [" + low
            + ", " + high + "] inclusive that have sequential digits are : "
            + result);
  }
}
