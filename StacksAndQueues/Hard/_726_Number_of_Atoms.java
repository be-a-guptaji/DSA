/*
LeetCode Problem: https://leetcode.com/problems/number-of-atoms/description/

Question: 726. Number of Atoms

Problem Statement: Given a string formula representing a chemical formula, return the count of each atom.

The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.

For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
Two formulas are concatenated together to produce another formula.

For example, "H2O2He3Mg4" is also a formula.
A formula placed in parentheses, and a count (optionally added) is also a formula.

For example, "(H2O2)" and "(H2O2)3" are formulas.
Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

The test cases are generated so that all the values in the output fit in a 32-bit integer.

Example 1:
Input: formula = "H2O"
Output: "H2O"
Explanation: The count of elements are {'H': 2, 'O': 1}.

Example 2:
Input: formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

Example 3:
Input: formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.

Constraints:

1 <= formula.length <= 1000
formula consists of English letters, digits, '(', and ')'.
formula is always valid.
 */

/*
Approach: Stack-Based Parsing with HashMap Aggregation

Goal:
- Compute the count of each atom in a chemical formula
  and return the result in sorted lexicographical order.

Core Idea:
- Use a stack of HashMaps to represent nested scopes
  created by parentheses.
- Each map stores atom → frequency.
- When encountering ')', multiply the current scope
  by its multiplier and merge it with the previous scope.

Algorithm Steps:
1. Initialize a stack with one empty HashMap.
2. Traverse the formula string:
   - '(' → push a new empty map.
   - ')' → pop current map, read multiplier,
           multiply counts, merge with previous map.
   - Element name → parse element and its count,
                    update top map.
3. After traversal, retrieve the final map.
4. Sort atom names lexicographically.
5. Build the output string with atom counts.

Time Complexity:
- O(n log k)  
  n = length of formula, k = number of unique atoms (due to sorting).

Space Complexity:
- O(n)

Result:
- Returns a string representing the atom counts
  in sorted order.
*/

package StacksAndQueues.Hard;

// Solution Class

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

// Solution Class
class Solution {
    // Method to find out the count of each atom
    public String countOfAtoms(String formula) {
        // Initiailize the length of the formula
        int length = formula.length();

        // Initialize the stack of Hashmap
        Stack<HashMap<String, Integer>> stack = new Stack<>();

        // Push a empty hash map to it
        stack.push(new HashMap<>());

        // Initialize the index
        int index = 0;

        // Iterate over the while loop
        while (index < length) {
            if (formula.charAt(index) == '(') {
                // Push a empty hash map to the stack
                stack.push(new HashMap<>());
                index++;
            } else if (formula.charAt(index) == ')') {
                // Get the current map
                HashMap<String, Integer> currentMap = stack.pop();

                // Initialize the count variable
                int count = 0;
                index++;

                // Extract the digit from the formula
                while (index < length && Character.isDigit(formula.charAt(index))) {
                    // Build the number
                    count = count * 10 + (formula.charAt(index) - '0');
                    index++;
                }

                // Update the count
                count = count == 0 ? 1 : count;

                // Get the previous map
                HashMap<String, Integer> previousMap = stack.peek();

                // Iterate over the hash map to update the count
                for (String key : currentMap.keySet()) {
                    previousMap.put(key, previousMap.getOrDefault(key, 0) + currentMap.get(key) * count);
                }

            } else {
                // Initialize the stringbuilder for the element
                StringBuilder element = new StringBuilder();

                // Add the current character to the element
                element.append(formula.charAt(index));
                index++;

                // Check if next element is part of element or not
                while (index < length && Character.isLowerCase(formula.charAt(index))) {
                    // Add the character to the element
                    element.append(formula.charAt(index));

                    // Increment the index by one
                    index++;
                }

                // Initialize the count variable
                int count = 0;

                // Extract the digit from the formula
                while (index < length && Character.isDigit(formula.charAt(index))) {
                    // Build the number
                    count = count * 10 + (formula.charAt(index) - '0');
                    index++;
                }

                // Get the top hash map of the stack
                HashMap<String, Integer> map = stack.peek();

                // Convert the element stringbuilder to the string
                String el = element.toString();

                // Update the count
                count = count == 0 ? 1 : count;

                // Update the hash map
                map.put(el, map.getOrDefault(el, 0) + count);

            }
        }

        // Initialize the stringbuilder for the countOfAtoms
        StringBuilder countOfAtoms = new StringBuilder();

        // Get the top hash map of the stack
        HashMap<String, Integer> map = stack.peek();

        // Get the keys of the hash map
        ArrayList<String> keys = new ArrayList<>(map.keySet());

        // Sort the keys of the hash map
        Collections.sort(keys);

        for (String elem : keys) {
            // Append the elem to the string builder
            countOfAtoms.append(elem);

            // Get the counts of the atom
            int count = map.get(elem);

            if (count != 1) {
                // Append the elem count to the string builder
                countOfAtoms.append(count);
            }
        }

        // Return the countOfAtoms by converting it to string
        return countOfAtoms.toString();
    }
}

public class _726_Number_of_Atoms {
    // Main method to test countOfAtoms
    public static void main(String[] args) {
        String formula = "K4(ON(SO3)2)2";

        String result = new Solution().countOfAtoms(formula);

        System.out.println("The count of each atom is : " + result);
    }
}
