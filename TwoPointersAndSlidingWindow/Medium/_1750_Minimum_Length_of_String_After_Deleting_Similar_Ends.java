/*
LeetCode Problem: https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/

Question: 1750. Minimum Length of String After Deleting Similar Ends

Problem Statement: Given a string s consisting only of characters 'a', 'b', and 'c'. You are asked to apply the following algorithm on the string any number of times:

Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
The prefix and the suffix should not intersect at any index.
The characters from the prefix and suffix must be the same.
Delete both the prefix and the suffix.
Return the minimum length of s after performing the above operation any number of times (possibly zero times).

Example 1:
Input: s = "ca"
Output: 2
Explanation: You can't remove any characters, so the string stays as is.

Example 2:
Input: s = "cabaabac"
Output: 0
Explanation: An optimal sequence of operations is:
- Take prefix = "c" and suffix = "c" and remove them, s = "abaaba".
- Take prefix = "a" and suffix = "a" and remove them, s = "baab".
- Take prefix = "b" and suffix = "b" and remove them, s = "aa".
- Take prefix = "a" and suffix = "a" and remove them, s = "".

Example 3:
Input: s = "aabccabba"
Output: 3
Explanation: An optimal sequence of operations is:
- Take prefix = "aa" and suffix = "a" and remove them, s = "bccabb".
- Take prefix = "b" and suffix = "bb" and remove them, s = "cca".

Constraints:

1 <= s.length <= 10^5
s only consists of characters 'a', 'b', and 'c'.
 */

/*
Approach: Two Pointers – Remove Matching Prefix & Suffix Blocks

Goal:
- Repeatedly remove a prefix and suffix if:
    - They consist of the same character.
    - They are non-empty.
- Return the minimum remaining length.

Key Insight:
- If s[start] == s[end], we can remove ALL consecutive occurrences
  of that character from both ends.
- Continue shrinking inward as long as the boundary characters match.

Algorithm Steps:

1. Initialize:
   - start = 0
   - end = s.length() - 1

2. While:
   - start < end
   - s[start] == s[end]

   Let character = s[start]

   Remove all consecutive `character` from the left:
       while (start <= end && s[start] == character)
           start++

   Remove all consecutive `character` from the right:
       while (start <= end && s[end] == character)
           end--

3. When characters differ or pointers cross:
   - Remaining length = end - start + 1

Why This Works:
- The operation removes full matching boundary blocks.
- Once boundaries differ, no more valid operations are possible.
- Each character is processed at most once.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the minimum length after performing the operations.
*/

package TwoPointersAndSlidingWindow.Medium;

public class _1750_Minimum_Length_of_String_After_Deleting_Similar_Ends {
    // Method to find the the minimum length of s after performing the above
    // operation any number of times (possibly zero times)
    public static int minimumLength(String s) {
        // Convert the s into the character arrays
        char[] str = s.toCharArray();

        // Initialize the start and end pointer
        int start = 0, end = str.length - 1;

        // Iterate over the str array untill start and end do not crosses each other and
        // str[start] is equal to str[end]
        while (start < end && str[start] == str[end]) {
            // Get the character to delete
            char character = str[start];

            // Increment the start pointer untill the character is same
            while (start <= end && str[start] == character) {
                start++;
            }

            // Decrement the start pointer untill the character is same
            while (start <= end && str[end] == character) {
                end--;
            }
        }

        // Return the difference of start and end
        return end - start + 1;
    }

    // Main method to test minimumLength
    public static void main(String[] args) {
        String s = "";

        int result = minimumLength(s);

        System.out.println(
                "The minimum length of s after performing the above operation any number of times (possibly zero times) is : "
                        + result);
    }
}
