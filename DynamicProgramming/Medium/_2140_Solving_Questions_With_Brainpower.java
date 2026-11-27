/*
LeetCode Problem: https://leetcode.com/problems/solving-questions-with-brainpower/

Question: 2140. Solving Questions With Brainpower

Problem Statement: You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].

The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.

For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
Return the maximum points you can earn for the exam.

Example 1:
Input: questions = [[3,2],[4,3],[4,4],[2,5]]
Output: 5
Explanation: The maximum points can be earned by solving questions 0 and 3.
- Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
- Unable to solve questions 1 and 2
- Solve question 3: Earn 2 points
Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.

Example 2:
Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
Output: 7
Explanation: The maximum points can be earned by solving questions 1 and 4.
- Skip question 0
- Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
- Unable to solve questions 2 and 3
- Solve question 4: Earn 5 points
Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.

Constraints:
1 <= questions.length <= 10^5
questions[i].length == 2
1 <= pointsi, brainpoweri <= 10^5
*/

/*
Approach: Top-down Memoized DP over Question Index
Goal:
- Maximize total points from an exam where questions
  are answered in order and solving a question forces
  skipping the next brainpower[i] questions.
Core Idea:
- dfs(i) = max points obtainable from questions i..n-1.
- Skip question i: dfs(i + 1).
- Solve question i: points[i] + dfs(i + brainpower[i] + 1).
- Take the max of both choices and memoize per index.
- memo == 0 acts as the "not computed" sentinel, valid
  since every computed value is at least 1.
Algorithm Steps:
1. If index >= n, return 0.
2. Return memo[index] if already computed.
3. memo[index] = max(skip, solve) and return it.
Time Complexity:
- O(n)
each index is computed once with O(1) work.
Space Complexity:
- O(n)
memo array plus recursion stack.
Result:
- Returns the maximum points achievable.
*/

package DynamicProgramming.Medium;

// Solution Class
class Solution {
  // Method to find the maximum points you can earn for the exam
  public long mostPoints(int[][] questions) {
    // Return the recursive dfs method
    return this.dfs(0, questions, new long[questions.length]);
  }

  // Helper method for the dfs
  private long dfs(int index, int[][] question, long[] memo) {
    // If index is out of bound then return zero
    if (index >= question.length) {
      return 0;
    }

    // If memo[index] is not zero then return the memo[index]
    if (memo[index] != 0) {
      return memo[index];
    }

    // Return the memo[index] after updating
    return memo[index] = Math.max(this.dfs(index + 1, question, memo),
        this.dfs(index + question[index][1] + 1, question, memo) + question[index][0]);
  }
}

public class _2140_Solving_Questions_With_Brainpower {
  // Main method to test mostPoints
  public static void main(String[] args) {
    int[][] questions = new int[][] { { 3, 2 }, { 4, 3 }, { 4, 4 }, { 2, 5 } };

    long result = new Solution().mostPoints(questions);

    System.out.println("The maximum points you can earn for the exam is : " + result);
  }
}