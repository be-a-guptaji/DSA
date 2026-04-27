/*
LeetCode Problem: https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/

Question: 1461. Check If a String Contains All Binary Codes of Size K

Problem Statement: Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

Example 1:
Input: s = "00110110", k = 2
Output: true
Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.

Example 2:
Input: s = "0110", k = 1
Output: true
Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 

Example 3:
Input: s = "0110", k = 2
Output: false
Explanation: The binary code "00" is of length 2 and does not exist in the array.

Constraints:
1 <= s.length <= 5 * 10^5
s[i] is either '0' or '1'.
1 <= k <= 20
*/

/*
Approach: Sliding Window + Bitmask (Rolling Hash)

Goal:
- Check if every binary code of length k appears as a substring.

Core Idea:
- Total possible binary codes = 2^k.
- Use a rolling bitmask to represent current k-length window.
- Track seen codes using a boolean array.
- Count how many unique codes are found.

Algorithm Steps:
1. If s.length < k → return false.
2. Initialize:
   - totalCodes = 2^k
   - boolean[] seen of size totalCodes
   - current = 0
3. Traverse string:
   - Shift left and add current bit:
       current = ((current << 1) & ((1 << k) - 1)) | bit
   - When i ≥ k - 1:
       - If not seen[current]:
           mark seen and increment count
4. Return count == totalCodes.

Time Complexity:
- O(n)

Space Complexity:
- O(2^k)

Result:
- Returns true if all binary codes of length k exist, else false.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to determine if every binary code of length k is a substring of s
  public boolean hasAllCodes(String s, int k) {
    // Initialize the totalNumbersOfCodes equal to 2^k
    int totalNumbersOfCodes = (1 << k);

    // Convert the string s to character array
    char[] binary = s.toCharArray();

    // If binary array length is smaller then totalNumbersOfCodes then return false
    if (binary.length < totalNumbersOfCodes) {
      return false;
    }

    // Initialize the boolean array of length totalNumbersOfCodes
    boolean[] codes = new boolean[totalNumbersOfCodes];

    // Initialize the totalCodes variable and current variable
    int totalCodes = 0, current = 0;

    // Iterate over the binary array to find all the codes
    for (int i = 0; i < binary.length; i++) {
      // Update the current varaible
      current = ((current << 1) & ((1 << k) - 1)) | binary[i] - '0';

      // If k is equal to or more than k and codes[current] is false then update the
      // codes array
      if (i >= k - 1 && !codes[current]) {
        // Increment the totalCodes variable
        totalCodes++;

        // Change the codes[current] to true
        codes[current] = true;
      }
    }

    // Return condition totalCodes == (1 << k)
    return totalCodes == totalNumbersOfCodes;
  }
}

public class _1461_Check_If_a_String_Contains_All_Binary_Codes_of_Size_K {
  // Main method to test hasAllCodes
  public static void main(String[] args) {
    String s = "00110110";
    int k = 2;

    boolean result = new Solution().hasAllCodes(s, k);

    System.out.println("The string" + s + (result ? " " : " does not ") + "cointains all code of length " + k + ".");
  }
}
