/*
LeetCode Problem: https://leetcode.com/problems/unique-email-addresses/

Question: 929. Unique Email Addresses

Problem Statement: Every valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.

For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.

For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.

For example, "m.y+name@email.com" will be forwarded to "my@email.com".
It is possible to use both of these rules at the same time.

Given an array of strings emails where we send one email to each emails[i], return the number of different addresses that actually receive mails.

Example 1:
Input: emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails.

Example 2:
Input: emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
Output: 3 

Constraints:

1 <= emails.length <= 100
1 <= emails[i].length <= 100
emails[i] consist of lowercase English letters, '+', '.' and '@'.
Each emails[i] contains exactly one '@' character.
All local and domain names are non-empty.
Local names do not start with a '+' character.
Domain names end with the ".com" suffix.
Domain names must contain at least one character before ".com" suffix.
*/

/*
Approach: String Normalization + HashSet

Goal:
Count the number of unique email addresses after applying normalization rules:
- Ignore '.' in the local name (before '@').
- Ignore everything after '+' in the local name.
- Keep the domain part (after '@') unchanged.

Algorithm:
1. Initialize a HashSet to store normalized email addresses.
2. For each email:
   - Traverse characters one by one.
   - Before '@':
       • Skip '.' characters.
       • Once '+' is encountered, ignore all characters until '@'.
   - After '@':
       • Append all characters as-is.
3. Add the normalized email to the HashSet.
4. Return the size of the HashSet.

Why It Works:
- HashSet automatically removes duplicates.
- Character-by-character processing avoids extra string operations.

Time Complexity: O(n × m)
- n = number of emails
- m = average length of an email

Space Complexity: O(n)
*/

package Arrays.Easy;

import java.util.Arrays;
import java.util.HashSet;

public class _929_Unique_Email_Addresses {
    // Method to find the unique email addresses
    public static int numUniqueEmails(String[] emails) {
        // Make a hash set for holding the emails addresses
        HashSet<String> uniqueEmails = new HashSet<>();

        // Iterate over the emails
        for (String email : emails) {
            // Initialize the string builder for building the set
            StringBuilder sb = new StringBuilder();

            // Initialize the boolean flag for removing the string after '+' and before '@'
            // and boolean flag for after '@'
            boolean ignore = false, atEnd = false;

            // Convert the email to the character array
            char[] emailStr = email.toCharArray();

            // Iterate over the email string
            for (char ch : emailStr) {
                // If we encounter '+' set the ignore flag to true
                if (!atEnd && ch == '+') {
                    ignore = true;
                }
                // If encounter '@' then set the ignore flag to false
                else if (ch == '@') {
                    ignore = false;
                    atEnd = true;
                }
                // If encounter '.' then skip the iteration
                else if (!atEnd && ch == '.') {
                    continue;
                }

                // If ignore flag is false then add the character to the string builder
                if (!ignore) {
                    sb.append(ch);
                }
            }

            // Add the email to the hash set
            uniqueEmails.add(sb.toString());
        }

        // Retrun the size of the hash set
        return uniqueEmails.size();
    }

    // Main method to test numUniqueEmails
    public static void main(String[] args) {
        String[] emails = new String[] { "test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
                "testemail+david@lee.tcode.com" };

        int result = numUniqueEmails(emails);

        System.out.println("The unique email addresses in " + Arrays.toString(emails) + " is : " + result);
    }
}
