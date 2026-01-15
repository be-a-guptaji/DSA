/*
LeetCode Problem: https://leetcode.com/problems/maximum-number-of-balloons/

Question: 1189. Maximum Number of Balloons

Problem Statement: Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

Example 1:
Input: text = "nlaebolko"
Output: 1

Example 2:
Input: text = "loonbalxballpoon"
Output: 2

Example 3:
Input: text = "leetcode"
Output: 0

Constraints:

1 <= text.length <= 10^4
text consists of lower case English letters only.

Note: This question is the same as 2287: Rearrange Characters to Make Target String.
*/

/*
Approach: Character Frequency Counting with Required Multiplicity

Goal:
Find the maximum number of times the word "balloon" can be formed
using characters from the given string.

Key Observation:
The word "balloon" requires the following character counts:
- b → 1
- a → 1
- l → 2
- o → 2
- n → 1

Algorithm:
1. Count the frequency of relevant characters in the string:
   - b, a, l, o, n
2. Since 'l' and 'o' are required twice:
   - The effective count for 'l' is freq(l) / 2
   - The effective count for 'o' is freq(o) / 2
3. The maximum number of "balloon" instances is the minimum among:
   - freq(b),
   - freq(a),
   - freq(l) / 2,
   - freq(o) / 2,
   - freq(n)

Why It Works:
- Each instance of "balloon" consumes a fixed number of characters.
- The limiting character (minimum effective count) determines how many
  complete words can be formed.

Time Complexity: O(n)
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1189_Maximum_Number_of_Balloons {
    // Method to find the minimum number of instances of the word "balloon" possible
    public static int maxNumberOfBalloons(String text) {
        // Initialize the array for storing the frequency of the character
        int[] frequecy = new int[7];

        // Initialize the lIsOdd and oIsOdd variable
        boolean lIsOdd = false, oIsOdd = false;

        // Convert the text to the character array
        char[] str = text.toCharArray();

        // Iterate over the str to fill the frequecy
        for (char ch : str) {
            // Increment the frequecy index according the the character value
            if (ch == 'b') {
                frequecy[0]++;
            } else if (ch == 'a') {
                frequecy[1]++;
            } else if (ch == 'l') {
                frequecy[lIsOdd ? 2 : 3]++;
                lIsOdd = !lIsOdd;
            } else if (ch == 'o') {
                frequecy[oIsOdd ? 4 : 5]++;
                oIsOdd = !oIsOdd;
            } else if (ch == 'n') {
                frequecy[6]++;
            }
        }

        // Initialize the variable to store the minimum number of instances
        int minimumNumberOfInstances = Integer.MAX_VALUE;

        // Iterate over the frequecy array for finding the minimum number of instances
        for (int freq : frequecy) {
            minimumNumberOfInstances = Math.min(minimumNumberOfInstances, freq);
        }

        // Return the minimum number of instances
        return minimumNumberOfInstances;
    }

    // Main method to test maxNumberOfBalloons
    public static void main(String[] args) {
        String text = "loonbalxballpoon";

        int result = maxNumberOfBalloons(text);

        System.out.println("The minimum number of instances of the word \"balloon\" possible is : " + result);
    }
}
