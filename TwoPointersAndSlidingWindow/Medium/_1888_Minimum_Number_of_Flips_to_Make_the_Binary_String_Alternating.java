/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/

Question: 1888. Minimum Number of Flips to Make the Binary String Alternating

Problem Statement: You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Example 1:
Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".

Example 2:
Input: s = "010"
Output: 0
Explanation: The string is already alternating.

Example 3:
Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".

Constraints:
1 <= s.length <= 10^5
s[i] is either '0' or '1'.
*/

/*
Approach: Circular Sliding Window + Pattern Matching

Goal:
- Find the minimum number of Type-2 flips needed
  to make a binary string alternating after any
  number of rotations.

Core Idea:
- A rotation can be represented by taking a window
  of length n inside:
      s + s
- Compare every possible rotation against the two
  valid alternating patterns:

      Pattern 1: 010101...
      Pattern 2: 101010...

- For each window of length n:
   - Count mismatches with Pattern 1.
   - Count mismatches with Pattern 2.
- Minimum mismatches among all windows is the answer.

Algorithm Steps:
1. Build:
      str = s + s
2. Build alternating patterns of length 2n:
      alternate1 = 010101...
      alternate2 = 101010...
3. Use a sliding window of size n:
   - Track mismatch counts:
       difference1
       difference2
4. Expand right pointer:
   - Add mismatch contributions.
5. If window exceeds n:
   - Remove left-side mismatch contributions.
6. When window size equals n:
   - Update answer with:
         min(difference1, difference2)
7. Return minimum answer.

Why It Works:
- Every rotation of s appears exactly once as a
  length-n window in s + s.
- An alternating string must match one of the two
  possible alternating patterns.
- Mismatch count equals the number of flips required.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns the minimum number of flips required to
  make some rotation of s alternating.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the minimum number of type-2 operations you need to perform
  // such that s becomes alternating
  public int minFlips(String s) {
    // Get the length of the string
    int length = s.length();

    // Get the new length
    int newLength = length << 1;

    // Initialize the character array of size 2 times the s
    char[] str = new char[newLength];

    // Initialize the character array variable
    char[] aternate1 = new char[newLength];
    char[] aternate2 = new char[newLength];

    // Fill the str array and make the alternating string
    for (int i = 0; i < length; i++) {
      // Fill the str array
      str[i] = str[i + length] = s.charAt(i);

      // Get the new index by multipying it by 2
      int index = i << 1;

      // Make both alternating string
      aternate1[index] = '0';
      aternate2[index] = '1';
      aternate1[index + 1] = '1';
      aternate2[index + 1] = '0';
    }

    // Initialize the result variable to length
    int result = length;

    // Initialize the difference variable
    int difference1 = 0;
    int difference2 = 0;

    // Iterate over the string s
    for (int left = 0, right = 0; right < newLength; right++) {
      // Update the difference variable
      if (str[right] != aternate1[right]) {
        difference1++;
      }

      if (str[right] != aternate2[right]) {
        difference2++;
      }

      // If window is greater than length then shrink the window
      if ((right - left + 1) > length) {
        // Update the difference variable
        if (str[left] != aternate1[left]) {
          difference1--;
        }

        if (str[left] != aternate2[left]) {
          difference2--;
        }

        // Increment the left varible
        left++;
      }

      // If window is equal to the length of the s then update the result varible
      if ((right - left + 1) == length) {
        result = Math.min(result, Math.min(difference1, difference2));
      }
    }

    // Return the result
    return result;
  }
}

public class _1888_Minimum_Number_of_Flips_to_Make_the_Binary_String_Alternating {
  // Main method to test minFlips
  public static void main(String[] args) {
    String s = "111000";

    int result = new Solution().minFlips(s);

    System.out.println(
        "The minimum number of type-2 operations you need to perform such that s becomes alternating is : " + result);
  }
}
