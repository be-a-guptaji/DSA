/*
LeetCode Problem: https://leetcode.com/problems/remove-invalid-parentheses/

Question: 301. Remove Invalid Parentheses

Problem Statement: Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Input: s = ")("
Output: [""]

Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
 */

/*
Approach:
1. We are given a string `s` that may contain invalid parentheses.  
   The goal is to remove the *minimum number* of parentheses so that all resulting strings are valid.

2. This problem is solved using **Breadth-First Search (BFS)** instead of backtracking:
   - BFS explores all possible strings by removing one parenthesis at each step.
   - The moment we find valid strings at a level, we stop going deeper because
     BFS guarantees that this level corresponds to the *minimum removals*.

3. BFS Algorithm:
   • Push the original string into a queue and mark it as visited.  
   • While queue is not empty:
       - Pop one string and check if it is valid.
       - If valid:
           → Add it to the result list.
           → Mark that we found a valid level and stop processing deeper levels.
       - If not valid and no solution found yet:
           → Generate all strings obtained by removing exactly one '(' or ')'.
           → Add these new strings into the queue (if not visited before).

4. Validity check:
   • Traverse the string and maintain a counter.
   • Increment on '(' and decrement on ')'.
   • If counter becomes negative → invalid.
   • At end, counter must be zero for valid string.

5. BFS guarantees:
   - The first valid level gives all strings with minimum removals.
   - No duplicates because we track visited strings.
   - No need for global variables or recursion.

Time Complexity: O(2^N) in worst case due to BFS expansion.  
Space Complexity: O(2^N) for queue + visited set.
*/

package Backtracking.Hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _301_Remove_Invalid_Parentheses {
    // Method to find the list of unique strings that are valid with the minimum
    // number of removals
    public static List<String> removeInvalidParentheses(String s) {
        // Initialize the list to store all valid strings
        List<String> result = new ArrayList<>();

        // Base case: if the string is null, return an empty list
        if (s == null) {
            return result;
        }

        // Initialize the visited set to avoid duplicate processing
        Set<String> visited = new HashSet<>();

        // Initialize the queue for BFS traversal
        Queue<String> queue = new LinkedList<>();

        // Add the original string to the queue and mark as visited
        queue.add(s);
        visited.add(s);

        // Boolean to indicate when a valid level is found
        boolean found = false;

        // Start the BFS traversal
        while (!queue.isEmpty()) {
            String current = queue.poll();

            // If the current string is valid, add it to the result list
            if (isValid(current)) {
                result.add(current);
                found = true;
            }

            // If a valid string is found, do not process further levels
            if (found) {
                continue;
            }

            // Generate all possible strings by removing one parenthesis
            for (int i = 0; i < current.length(); i++) {
                char ch = current.charAt(i);

                // Skip non-parenthesis characters
                if (ch != '(' && ch != ')') {
                    continue;
                }

                // Form a new string by removing the current parenthesis
                String next = current.substring(0, i) + current.substring(i + 1);

                // Add the new string to the queue if not already visited
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }
            }
        }

        // If no valid string is found, return an empty string
        if (result.isEmpty()) {
            result.add("");
        }

        // Return the final list of valid strings
        return result;
    }

    // Helper method to check if the string is valid
    private static boolean isValid(String s) {
        // Initialize the counter to track balance
        int count = 0;

        // Traverse the entire string
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                // If there are more closing brackets than opening, return false
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }

        // Return true only if all parentheses are balanced
        return count == 0;
    }

    // Main method to test removeInvalidParentheses
    public static void main(String[] args) {
        String s = "()";

        List<String> result = removeInvalidParentheses(s);

        System.out.println(
                "The list of unique strings that are valid with the minimum number of removals of parentheses is : "
                        + result);
    }
}
