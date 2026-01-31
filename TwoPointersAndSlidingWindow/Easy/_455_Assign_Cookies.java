/*
LeetCode Problem: https://leetcode.com/problems/assign-cookies/

Question: 455. Assign Cookies

Problem Statement: Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.

Example 1:
Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.

Example 2:
Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.

Constraints:

1 <= g.length <= 3 * 10^4
0 <= s.length <= 3 * 10^4
1 <= g[i], s[j] <= 2^31 - 1

Note: This question is the same as 2410: Maximum Matching of Players With Trainers.
 */

/*
Approach: Greedy Matching After Sorting

Goal:
Maximize the number of children whose greed factor can be satisfied
using available cookies.

Key Idea:
Assign the smallest possible cookie that satisfies the least greedy child.
Sorting enables an optimal greedy match.

Algorithm:
1. Sort both arrays:
   - g[]: greed factors of children
   - s[]: sizes of cookies
   (Note: multithreading is unnecessary here; single-threaded sort is sufficient.)
2. Use a pointer `satisfied` to track how many children are satisfied.
3. Iterate over cookies:
   - If current cookie size >= greed of the current child:
       • Assign the cookie.
       • Increment `satisfied` to move to the next child.
4. Stop when either cookies are exhausted or all children are satisfied.
5. Return `satisfied`.

Why It Works:
- Giving the smallest adequate cookie preserves larger cookies
  for greedier children.
- This greedy choice leads to the maximum number of satisfied children.

Time Complexity: O(n log n + m log m)  
Space Complexity: O(1) extra (excluding sort overhead)
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

public class _455_Assign_Cookies {
    // Method to find the number of children who can have there greed satisfy
    public static int findContentChildren(int[] g, int[] s) {
        // Sort both the arrays
        Thread t1 = new Thread(() -> Arrays.sort(g));
        Thread t2 = new Thread(() -> Arrays.sort(s));

        // Start the process
        t1.start();
        t2.start();

        try {
            // Join the porcesses
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }

        // Initialize the satisfied children variable
        int satisfied = 0;

        // Iterate over the arrays to find the number of childern who can have there
        // greed satisfy
        for (int i = 0; i < s.length && satisfied < g.length; i++) {
            // If greed is satisfied for the children increment the satisfied
            if (s[i] >= g[satisfied])
                // Increment the satisfied
                satisfied++;
        }

        // Return the satisfied children
        return satisfied;
    }

    // Main method to test findContentChildren
    public static void main(String[] args) {
        int[] g = new int[] { 1, 2, 3 };
        int[] s = new int[] { 1, 1 };

        int result = findContentChildren(g, s);

        System.out.println("The number of children who can have there greed satisfy is : " + result);
    }
}
