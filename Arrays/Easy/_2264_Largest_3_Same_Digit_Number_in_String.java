/*
LeetCode Problem: https://leetcode.com/problems/largest-3-same-digit-number-in-string/

Question: 2264. Largest 3-Same-Digit Number in String

Problem Statement: You are given a string num representing a large integer. An integer is good if it meets the following conditions:

It is a substring of num with length 3.
It consists of only one unique digit.
Return the maximum good integer as a string or an empty string "" if no such integer exists.

Note:
A substring is a contiguous sequence of characters within a string.
There may be leading zeroes in num or a good integer. 


Example 1:
Input: num = "6777133339"
Output: "777"
Explanation: There are two distinct good integers: "777" and "333".
"777" is the largest, so we return "777".

Example 2:
Input: num = "2300019"
Output: "000"
Explanation: "000" is the only good integer.

Example 3:
Input: num = "42352338"
Output: ""
Explanation: No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.

Constraints:

3 <= num.length <= 1000
num only consists of digits.
 */

/*
Approach: Scan for Consecutive Triplets

Goal:
Find the largest “good” integer in the string.
A good integer is a substring of length 3 consisting of the same digit
(e.g., "777", "000").

Key Idea:
Scan the string and detect any three consecutive identical digits.
Track the maximum digit among such triplets.

Algorithm:
1. Convert the input string to a character array.
2. Initialize a variable to store the largest digit found in a valid triplet.
3. Traverse the array from index 1 to n − 2:
   - If nums[i − 1] == nums[i] == nums[i + 1]:
       • Update largest digit if this digit is greater.
4. If no valid triplet was found, return an empty string.
5. Otherwise, return the string formed by repeating the largest digit three times.

Why It Works:
- Each valid good integer corresponds to three equal adjacent characters.
- Keeping only the maximum digit ensures the largest good integer is returned.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _2264_Largest_3_Same_Digit_Number_in_String {
    // Method to find the maximum good integer as a string
    public static String largestGoodInteger(String num) {
        // Initialize the String array for all the cases
        String[] numbers = new String[] { "000", "111", "222", "333", "444", "555", "666", "777", "888", "999" };

        // Convert the string int to the character array
        char[] nums = num.toCharArray();

        // Character for the largestNumber
        char largestNumber = 0;

        // Iterate over the nums array if three consecutive number is same then update
        // the largestNumber
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] == nums[i] && nums[i] == nums[i + 1]) {
                largestNumber = (char) Math.max(largestNumber, nums[i]);
            }
        }

        // Retrun the string of three largestNumber
        return largestNumber == 0 ? "" : numbers[largestNumber - '0'];
    }

    // Main method to test largestGoodInteger
    public static void main(String[] args) {
        String num = "hello";

        String result = largestGoodInteger(num);

        System.out.println("The maximum good integer as a string is : " + result);
    }
}
