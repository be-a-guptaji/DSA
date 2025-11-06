/*
LeetCode Problem: https://leetcode.com/problems/letter-combinations-of-a-phone-number/

Question: 17. Letter Combinations of a Phone Number

Problem Statement: Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

Constraints:

The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 */

/*
Approach:
1. Each digit (from '2' to '9') maps to a set of letters (like on a phone keypad).
2. Use backtracking (DFS) to explore all possible combinations:
   - Start from the first digit.
   - For each letter corresponding to that digit, append it to the current combination.
   - Move to the next digit and repeat the process.
   - Once all digits are processed, add the complete combination to the result list.
3. Use a StringBuilder for efficient string concatenation and backtracking.
4. Return the list of all possible letter combinations.

Time Complexity: O(3^N * 4^M), where N is the count of digits mapping to 3 letters (2–6,8),
and M is the count of digits mapping to 4 letters (7,9).
Space Complexity: O(N) for recursion stack and current combination.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class _17_Letter_Combinations_of_a_Phone_Number {
    // Map digits to ASCII codes of letters
    private static final int[][] digitsMap = {
            { 97, 98, 99 }, // 2 -> a, b, c
            { 100, 101, 102 }, // 3 -> d, e, f
            { 103, 104, 105 }, // 4 -> g, h, i
            { 106, 107, 108 }, // 5 -> j, k, l
            { 109, 110, 111 }, // 6 -> m, n, o
            { 112, 113, 114, 115 }, // 7 -> p, q, r, s
            { 116, 117, 118 }, // 8 -> t, u, v
            { 119, 120, 121, 122 } // 9 -> w, x, y, z
    };

    // Method to find the letter combinations of digits
    public static List<String> letterCombinations(String digits) {
        // Convert the String into character array
        char[] digit = digits.toCharArray();

        // Get the length of the digits
        int length = digit.length;

        // Initialize the list of strings for the result
        List<String> result = new ArrayList<>();

        // Make the dfs call to make the letter combinations list
        dfs(digit, result, 0, length, new StringBuilder());

        // Retrun the result list
        return result;
    }

    // Helper method to make the letter combinations list
    private static void dfs(char[] digit, List<String> list, int index, int length, StringBuilder value) {
        // If index is equal to the length then add it to the list and return
        if (index == length) {
            list.add(value.toString());
            return;
        }

        // Get the num value of the digit
        char num = digit[index];

        // Get the keys array
        int[] keys = digitsMap[num - '2'];

        // Get the length of the num key map
        int keysLenght = keys.length;

        // Iterate over the keys of the num
        for (int j = 0; j < keysLenght; j++) {
            // Make a recursive dfs call
            dfs(digit, list, index + 1, length, value.append((char) keys[j]));

            // Remove the last character
            value.deleteCharAt(value.length() - 1); // Backtrack to the last charcter
        }
    }

    // Main method to test letterCombinations
    public static void main(String[] args) {
        String digits = "23";

        List<String> result = letterCombinations(digits);

        System.out.print("The letter combinations of digits " + digits + " is : " + result);
    }
}
