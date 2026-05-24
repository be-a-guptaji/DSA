/*
LeetCode Problem: https://leetcode.com/problems/text-justification/

Question: 68. Text Justification

Problem Statement: Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.

Example 1:
Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:
Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.

Example 3:
Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

Constraints:
1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
*/

/*
Approach: Greedy Line Packing + Space Distribution

Goal:
- Format text so that each line has exactly maxWidth characters
  with fully justified spacing.

Core Idea:
- Greedily pack as many words as possible into a line.
- Distribute spaces evenly between words.
- Extra spaces are assigned from left to right.
- Last line is left-justified.

Algorithm Steps:
1. Traverse words array:
   - Add words while total length fits within maxWidth.
2. Once line is full:
   - Compute:
       extraSpace = maxWidth - totalWordLength
   - Number of gaps:
       max(1, line.size() - 1)
   - Distribute spaces evenly:
       space = extraSpace / gaps
       remainder = extraSpace % gaps
3. Append extra spaces from left to right.
4. Join words and add line to result.
5. Handle last line separately:
   - Join with single spaces.
   - Pad trailing spaces to maxWidth.
6. Return result list.

Time Complexity:
- O(total characters)

Space Complexity:
- O(maxWidth)

Result:
- Returns fully justified text lines.
*/

package Arrays.Hard;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
    // Method to find the justified text
    public List<String> fullJustify(String[] words, int maxWidth) {
        // Initialize the result and line list
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> line = new ArrayList<>();

        // Initialize the length and the index variable
        int length = 0;
        int index = 0;

        // Iterate over the words
        while (index < words.length) {
            // If the current word can fit in the line update the line list
            if (length + words[index].length() + line.size() <= maxWidth) {
                // Add words[index] to the line list
                line.add(words[index]);

                // Update the length
                length += words[index].length();

                // Increment the index
                index++;
            } else {
                // Make the line
                // Initialize the extraSpace variable
                int extraSpace = maxWidth - length;

                // Get the maxLength
                int maxLength = Math.max(1, line.size() - 1);

                // Initialize the space and remainder variable
                int space = extraSpace / maxLength;
                int remainder = extraSpace % maxLength;

                // Form the line
                for (int i = 0; i < maxLength; i++) {
                    line.set(i, line.get(i) + " ".repeat(space));

                    // If we have remainder add an extra space
                    if (remainder > 0) {
                        line.set(i, line.get(i) + " ");

                        // Decrement the remainder
                        remainder--;
                    }
                }

                // Add the line to the result list
                result.add(String.join("", line));

                // Clear the line
                line.clear();

                // Reset the length
                length = 0;
            }
        }

        // Handle the last line
        String lastLine = String.join(" ", line);

        // Add the traling spaces
        int tralingSpaces = maxWidth - lastLine.length();

        // Add the line to the result list
        result.add(lastLine + " ".repeat(tralingSpaces));

        // Return the result
        return result;
    }
}

public class _68_Text_Justification {
    // Main method to test the fullJustify
    public static void main(String[] args) {
        String[] words = new String[] { "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
                "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do" };
        int maxWidth = 20;

        List<String> result = new Solution().fullJustify(words, maxWidth);

        System.out.println("The result of the valid expression in string is : " + result);
    }
}
