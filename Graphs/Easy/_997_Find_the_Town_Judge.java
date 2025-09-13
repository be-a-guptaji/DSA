/*
LeetCode Problem: https://leetcode.com/problems/find-the-town-judge/

Question: 997. Find the Town Judge

Problem Statement: In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

Example 1:
Input: n = 2, trust = [[1,2]]
Output: 2

Example 2:
Input: n = 3, trust = [[1,3],[2,3]]
Output: 3

Example 3:
Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1

Constraints:

1 <= n <= 1000
0 <= trust.length <= 10^4
trust[i].length == 2
All the pairs of trust are unique.
ai != bi
1 <= ai, bi <= n
*/

/*
Approach:
1. Handle edge case:
   - If n == 1 and there are no trust relationships, then the only person is the judge.
2. Use an integer array score[] of size n + 1 to track trust balance for each person:
   - When person a trusts person b:
     - Decrement score[a] because a cannot be the judge (judge trusts no one).
     - Increment score[b] because b gains one more person’s trust.   
3. After processing all trust relationships:
   - The judge should be trusted by all n - 1 other people and trust nobody.
   - This means score[i] == n - 1 for the judge.   
4. Iterate through all people from 1..n and return the one with score == n - 1.
   - If no such person exists, return -1.

Time Complexity: O(m + n), where m = trust.length  
Space Complexity: O(n), for the score array
*/

package Graphs.Easy;

public class _997_Find_the_Town_Judge {
    // Method to find the town judge
    public static int findJudge(int n, int[][] trust) {
        // If only one person and no trust, they are the judge
        if (n == 1 && trust.length == 0) {
            return 1;
        }

        // Array to store net trust count for each person
        int[] score = new int[n + 1];

        // Process trust relationships
        for (int[] t : trust) {
            int a = t[0], b = t[1];
            score[a]--; 
            score[b]++;
        }

        // Judge must have score = n - 1
        for (int i = 1; i <= n; i++) {
            if (score[i] == n - 1) {
                return i;
            }
        }

        // If nothing found then return -1
        return -1;
    }

    // Main method to test findJudge
    public static void main(String[] args) {
        int n = 3;
        int[][] trust = { { 1, 3 }, { 2, 3 }, { 3, 1 } };

        int result = findJudge(n, trust);

        System.out.println("The judge of the town is : " + result);
    }
}
