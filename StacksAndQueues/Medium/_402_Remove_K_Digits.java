/*
LeetCode Problem: https://leetcode.com/problems/remove-k-digits/

Question: 402. Remove K Digits

Problem Statement: Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Constraints:

1 <= k <= num.length <= 10^5
num consists of only digits.
num does not have any leading zeros except for the zero itself.
 */

/*
Approach: Monotonic Increasing Stack (Greedy Digit Removal)

Goal:
- Remove exactly k digits from the number
- Return the smallest possible resulting number

Core Idea:
- To minimize the number:
    Remove digits that are larger than the digit to their right.
- Maintain a monotonic increasing sequence of digits.
- If a previous digit is larger than the current digit,
  remove it (if k > 0).

Algorithm Steps:

1. Use the input char array itself as a stack.
   - `left` acts as stack pointer.

2. Traverse digits with `right` pointer:

   While:
       k > 0
       AND stack not empty (left > 0)
       AND previous digit > current digit
   → Remove previous digit (left--)
   → Decrement k

   Then push current digit:
       number[left++] = number[right]

3. If k still > 0 after traversal:
   - Remove remaining digits from the end:
       left -= k

4. Remove leading zeros:
   - Find first non-zero index.

5. Build result from:
       number[index, left)

6. If result is empty → return "0"

Why This Works:
- Greedy removal ensures highest place values are minimized.
- Monotonic increasing structure guarantees smallest lexicographic number.
- Each digit is pushed and popped at most once.

Example:
num = "1432219", k = 3

Process:
1 < 4 (keep)
4 > 3 (remove 4)
3 > 2 (remove 3)
...
Result → "1219"

Time Complexity:
- O(n)

Space Complexity:
- O(1) extra (in-place stack usage)

Result:
- Returns smallest possible number after removing k digits.
*/

package StacksAndQueues.Medium;

public class _402_Remove_K_Digits {
    // Method to find the smallest possible integer after removing k digits from num
    public static String removeKdigits(String num, int k) {
        // Convert the num's string into character array
        char[] number = num.toCharArray();

        // Initialize the left pointer
        int left = 0;

        // Iterate over the number array
        for (int right = 0; right < number.length; right++) {
            // Remove the last number if it is less than the current number
            while (k > 0 && left > 0 && number[left - 1] > number[right]) {
                // Decrement the left and k variable
                left--;
                k--;
            }

            // Add the current character to the stack
            number[left++] = number[right];
        }

        // Decrement the k from the left index
        left -= k;

        // Initialize the index variable for removing leading zero's
        int index = 0;

        // Remove the leading zero's
        while (index < left && number[index] == '0') {
            // Increment the index vairable
            index++;
        }

        // Initialize the result variable to get the samllest number after removing k
        // digits
        String result = new String(number, index, left - index);

        // If result is empty then return '0' else the result
        return result.isEmpty() ? "0" : result;
    }

    // Main method to test removeKdigits
    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;

        String result = removeKdigits(num, k);

        System.out.println(
                "The smallest possible integer after removing " + k + " digits from " + num + " is : " + result);
    }
}
