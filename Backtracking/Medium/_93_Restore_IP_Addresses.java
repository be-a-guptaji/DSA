/*
LeetCode Problem: https://leetcode.com/problems/restore-ip-addresses/

Question: 93. Restore IP Addresses

Problem Statement: A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

Example 1:
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

Example 2:
Input: s = "0000"
Output: ["0.0.0.0"]

Example 3:
Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

Constraints:

1 <= s.length <= 20
s consists of digits only.
 */

/*
Approach:
1. We are given a numeric string `s`, and the task is to return all possible valid IP addresses that can be formed
   by inserting three dots into the string.

2. A valid IP address consists of exactly **four segments**, each satisfying:
   • Must contain only digits.
   • Value must be between 0 and 255 (inclusive).
   • Cannot have leading zeros unless the segment is exactly "0".

3. The solution uses a **backtracking approach**:
   - We recursively try to partition the string into valid segments.
   - At each step:
       • Extract a substring (1 to 3 characters long) representing a potential segment.
       • Validate the segment:
           - Reject if it starts with '0' but is longer than 1.
           - Reject if its integer value > 255.
       • If valid, add it to the current path and continue exploring the next index.
   - When we reach the end of the string (`index == s.length()`) and have exactly 4 segments,
     we join them with '.' and add to the result list.

4. During recursion:
   - We maintain a `StringBuilder` to build the IP incrementally.
   - After exploring one segment, we backtrack by removing it and trying the next possible partition.

5. To prune unnecessary branches:
   - Stop recursion if the segment count exceeds 4.
   - Stop if the remaining string is too long to form valid segments.

Time Complexity: O(3^4) = O(81), Each of the 4 segments can have up to 3 possible lengths., For each, we perform constant-time checks.

Space Complexity: O(4) = O(1), Depth of recursion is at most 4, The result list stores valid IP addresses.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class _93_Restore_IP_Addresses {
   // Method to find all possible valid IP addresses that can be formed by
   // inserting dots into s
   public static List<String> restoreIpAddresses(String s) {
      // Get the length of the string
      int length = s.length();

      // Edge case if the string length is greater than 12, no valid IP can be formed
      if (length > 12) {
         return new ArrayList<>();
      }

      // Initialize the list of strings to store and return the valid IP addresses
      List<String> result = new ArrayList<>();

      // Start the recursive backtracking process to form valid IP addresses
      backtrack(0, 0, s, new StringBuilder(), result);

      // Return the final list of valid IP addresses
      return result;
   }

   // Helper method to generate all valid IP addresses using backtracking
   private static void backtrack(int index, int segment, String s, StringBuilder sb, List<String> list) {
      // Base case: if index reaches the end of the string and 4 segments are formed,
      // add to result
      if (index == s.length() && segment == 4) {
         list.add(sb.toString());
         return;
      }

      // If segments exceed 4 or index goes beyond the string, stop the recursion
      if (segment >= 4) {
         return;
      }

      // Iterate through the string to generate each segment of the IP
      for (int i = index; i < s.length(); i++) {
         // If the segment size exceeds 3 digits, return as IP parts can't be longer than
         // 3 digits
         if (i - index > 2) {
            return;
         }

         // Extract the current substring representing a segment
         String segmentStr = s.substring(index, i + 1);

         // If the segment starts with '0' and length > 1, it's invalid (no leading zeros
         // allowed)
         if (segmentStr.charAt(0) == '0' && segmentStr.length() > 1) {
            return;
         }

         // Convert the substring to integer to check the valid range (0 - 255)
         int num = Integer.parseInt(segmentStr);

         // If the number is greater than 255, it's invalid, stop exploring further
         if (num > 255) {
            return;
         }

         // Store the current length of StringBuilder before adding new segment
         int beforeAppendLength = sb.length();

         // Add the segment to the StringBuilder
         sb.append(segmentStr);

         // Add a dot if it's not the last segment
         if (segment < 3) {
            sb.append('.');
         }

         // Recursive call to form the next segment
         backtrack(i + 1, segment + 1, s, sb, list);

         // Remove the last added segment and dot to backtrack
         sb.setLength(beforeAppendLength);
      }
   }

   // Main method to test restoreIpAddresses
   public static void main(String[] args) {
      String s = "25525511135";

      List<String> result = restoreIpAddresses(s);

      System.out.print(
            "The all possible valid IP addresses that can be formed by inserting dots into " + s + " is : " + result);
   }
}
