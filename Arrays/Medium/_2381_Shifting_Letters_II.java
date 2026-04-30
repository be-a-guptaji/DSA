/*
LeetCode Problem: https://leetcode.com/problems/shifting-letters-ii/

Question: 2381. Shifting Letters II

Problem Statement: You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.

Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').

Return the final string after all such shifts to s are applied.

Example 1:
Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
Output: "ace"
Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".

Example 2:
Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
Output: "catz"
Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".

Constraints:
1 <= s.length, shifts.length <= 5 * 10^4
shifts[i].length == 3
0 <= starti <= endi < s.length
0 <= directioni <= 1
s consists of lowercase English letters.
*/

/*
Approach: Difference Array + Prefix Sum

Goal:
- Apply multiple range shift operations efficiently on string s.

Core Idea:
- Each shift affects a range [start, end].
- Use a difference array to record range updates in O(1).
- Convert difference array to prefix sum to get net shift at each index.
- Apply shifts modulo 26 to wrap around alphabet.

Algorithm Steps:
1. Convert string to integer array (0 → 25).
2. Initialize difference array of size n+1.
3. For each shift:
   - If direction == 1 → +1
   - If direction == 0 → -1
   - Apply:
       diff[start] += val
       diff[end+1] -= val
4. Build prefix sum:
   - cumulative shift at each index.
5. Apply shift:
   - newChar = (oldChar + shift) mod 26
6. Convert back to characters and return string.

Time Complexity:
- O(n + m)
  n = length of string, m = number of shifts

Space Complexity:
- O(n)

Result:
- Returns the final shifted string after all operations.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the final string after all such shifts to s are applied
  public String shiftingLetters(String s, int[][] shifts) {
    // Initialize the result variable
    int[] result = new int[s.length()];

    // Fill the result vairable
    for (int i = 0; i < result.length; i++) {
      result[i] = s.charAt(i) - 'a';
    }

    // Initialize the prefix array one more then the length of string s
    int[] prefix = new int[result.length + 1];

    // Iterate over the shifts array to fill the prefix array
    for (int i = 0; i < shifts.length; i++) {
      // Get the start, end and direction of the shifts
      int start = shifts[i][0];
      int end = shifts[i][1] + 1;
      int direction = shifts[i][2];

      // Get the value according to the direction
      int val = direction == 0 ? -1 : 1;

      // Update the prefix array
      prefix[start] += val;
      prefix[end] -= val;
    }

    // Initialize the difference variable
    int difference = 0;

    // Update the result array
    for (int i = 0; i < result.length; i++) {
      // Update the diffrence variable
      difference += prefix[i];

      // Update the result variable
      result[i] = ((result[i] + difference) % 26 + 26) % 26;
    }

    // Initialize the string builder to form the result
    StringBuilder sb = new StringBuilder();

    // Append the characters to the string builder
    for (int i = 0; i < result.length; i++) {
      sb.append((char) (result[i] + 'a'));
    }

    // Return sb before converting into string
    return sb.toString();
  }
}

public class _2381_Shifting_Letters_II {
  // Main method to test shiftingLetters
  public static void main(String[] args) {
    String s = "abc";
    int[][] shifts = new int[][] { { 0, 1, 0 }, { 1, 2, 1 }, { 0, 2, 1 } };

    String result = new Solution().shiftingLetters(s, shifts);

    System.out.println("The final string after all such shifts to s are applied is : " + result);
  }
}
