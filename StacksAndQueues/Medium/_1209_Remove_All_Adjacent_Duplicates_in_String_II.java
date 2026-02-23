/*
LeetCode Problem: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

Question: 1209. Remove All Adjacent Duplicates in String II

Problem Statement: You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lowercase English letters.
 */

/*
Approach: Stack Simulation with Frequency Compression

Goal:
- Remove every group of k consecutive identical characters.
- Continue until no such group exists.
- Return the final string.

Core Idea:
- Use the input char array as a stack.
- Maintain a parallel count array:
    count[i] = frequency of consecutive characters
               ending at position i in the stack.

Algorithm Steps:

1. Convert string to char array.
2. Create:
      char[] str
      int[] count
3. Use `index` as stack pointer.

4. Iterate with `right` pointer over original string:

   a) Push current character:
        str[index] = str[right]
        count[index] = 1

   b) If previous character is same:
        count[index] += count[index - 1]

   c) If count[index] == k:
        Remove last k characters:
        index -= k

   d) Move to next position:
        index++

5. Build final string from:
      new String(str, 0, index)

Why This Works:
- Stack keeps the current valid string.
- Count array tracks consecutive duplicates efficiently.
- When k duplicates form, we instantly remove them.
- Removal may expose new duplicates — stack handles it naturally.

Example:
s = "deeedbbcccbdaa", k = 3

Process:
eee removed
ccc removed
bbb removed
Final: "aa"

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns string after removing all k consecutive duplicates.
*/

package StacksAndQueues.Medium;

public class _1209_Remove_All_Adjacent_Duplicates_in_String_II {
    // Method to find the final string after all such duplicate removals have been
    // made
    public static String removeDuplicates(String s, int k) {
        // Convert the num's string into character array
        char[] str = s.toCharArray();

        // Initialize the count array for mesuaring the frequency
        int[] count = new int[str.length];

        // Initialize the index variable
        int index = 0;

        // Iterate over the str array
        for (int right = 0; right < str.length; right++, index++) {
            // Initialzie the index with the one
            count[index] = 1;

            // Set the i'th index of str to right index
            str[index] = str[right];

            // If index is non negative and str[index - 1] is equal to str[right] then
            // update the count index
            if (index > 0 && str[index - 1] == str[right]) {
                count[index] += count[index - 1];
            }

            // If count of the current index is equal to k then subtract the k form the
            // index
            if (count[index] == k) {
                index -= k;
            }
        }

        // Return the new string
        return new String(str, 0, index);
    }

    // Main method to test removeDuplicates
    public static void main(String[] args) {
        String s = "pbbcggttciiippooaais";
        int k = 2;

        String result = removeDuplicates(s, k);

        System.out.println("The final string after all such duplicate removals have been made is : " + result);
    }
}
