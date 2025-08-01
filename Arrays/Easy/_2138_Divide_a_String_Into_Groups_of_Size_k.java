/*
LeetCode Problem: https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/

Question: 2138. Divide a String Into Groups of Size k

Problem Statement: A string s can be partitioned into groups of size k using the following procedure:

The first group consists of the first k characters of the string, the second group consists of the next k characters of the string, and so on. Each element can be a part of exactly one group.
For the last group, if the string does not have k characters remaining, a character fill is used to complete the group.
Note that the partition is done so that after removing the fill character from the last group (if it exists) and concatenating all the groups in order, the resultant string should be s.

Given the string s, the size of each group k and the character fill, return a string array denoting the composition of every group s has been divided into, using the above procedure.

Example 1:
Input: s = "abcdefghi", k = 3, fill = "x"
Output: ["abc","def","ghi"]
Explanation:
The first 3 characters "abc" form the first group.
The next 3 characters "def" form the second group.
The last 3 characters "ghi" form the third group.
Since all groups can be completely filled by characters from the string, we do not need to use fill.
Thus, the groups formed are "abc", "def", and "ghi".

Example 2:
Input: s = "abcdefghij", k = 3, fill = "x"
Output: ["abc","def","ghi","jxx"]
Explanation:
Similar to the previous example, we are forming the first three groups "abc", "def", and "ghi".
For the last group, we can only use the character 'j' from the string. To complete this group, we add 'x' twice.
Thus, the 4 groups formed are "abc", "def", "ghi", and "jxx".

Constraints:

1 <= s.length <= 100
s consists of lowercase English letters only.
1 <= k <= 100
fill is a lowercase English letter.
 */

/*
Approach: The solution first calculates the total number of groups needed by performing a ceiling division of the string’s length by the group size k. It then initializes an array of strings to hold these groups. The string is processed in chunks of size k, and each full chunk is added directly to the array. If there are leftover characters after dividing into full groups, these characters form the last group, which may be shorter than k. To handle this, the solution appends the specified fill character repeatedly until the last group reaches the required length k. This approach ensures that every group in the resulting array has exactly k characters, and that concatenating all groups (with the fill characters removed from the last group) recreates the original string.

Time Complexity:  O(n), where n = length of s.
Space Complexity: O(n / k) groups each of size k.
 */

package Arrays.Easy;

public class _2138_Divide_a_String_Into_Groups_of_Size_k {
    // Method to divide string into groups of size k
    public static String[] divideString(String s, int k, char fill) {
        // Initialize indeices and size
        int index = 0, i = 0, slen = s.length(), size = ((slen + k - 1) / k);

        // Initialize the string array
        String[] str = new String[size];

        // Slice the string in the size of k
        while (index + k <= slen) {
            str[i++] = s.substring(index, index + k);
            index += k;
        }

        // Check if last characters were taken and fill it if needed
        if (index < slen) {
            StringBuilder sb = new StringBuilder(s.substring(index, slen));
            while (sb.length() < k) {
                sb.append(fill);
            }
            str[size - 1] = sb.toString(); // assign last group directly to size-1 index
        }

        // Return the string
        return str;
    }

    // Main method to test divideString
    public static void main(String[] args) {
        String s = "abcdefghij";
        int k = 3;
        char fill = 'x';

        String[] result = divideString(s, k, fill);

        System.out.println(java.util.Arrays.toString(result));
    }
}
