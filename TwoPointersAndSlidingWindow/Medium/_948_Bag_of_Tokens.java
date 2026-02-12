/*
LeetCode Problem: https://leetcode.com/problems/bag-of-tokens/

Question: 948. Bag of Tokens

Problem Statement: You start with an initial power of power, an initial score of 0, and a bag of tokens given as an integer array tokens, where each tokens[i] denotes the value of tokeni.

Your goal is to maximize the total score by strategically playing these tokens. In one move, you can play an unplayed token in one of the two ways (but not both for the same token):

Face-up: If your current power is at least tokens[i], you may play tokeni, losing tokens[i] power and gaining 1 score.
Face-down: If your current score is at least 1, you may play tokeni, gaining tokens[i] power and losing 1 score.
Return the maximum possible score you can achieve after playing any number of tokens.

Example 1:
Input: tokens = [100], power = 50
Output: 0
Explanation: Since your score is 0 initially, you cannot play the token face-down. You also cannot play it face-up since your power (50) is less than tokens[0] (100).

Example 2:
Input: tokens = [200,100], power = 150
Output: 1
Explanation: Play token1 (100) face-up, reducing your power to 50 and increasing your score to 1.
There is no need to play token0, since you cannot play it face-up to add to your score. The maximum score achievable is 1.

Example 3:
Input: tokens = [100,200,300,400], power = 200
Output: 2
Explanation: Play the tokens in this order to get a score of 2:
Play token0 (100) face-up, reducing power to 100 and increasing score to 1.
Play token3 (400) face-down, increasing power to 500 and reducing score to 0.
Play token1 (200) face-up, reducing power to 300 and increasing score to 1.
Play token2 (300) face-up, reducing power to 0 and increasing score to 2.
The maximum score achievable is 2.

Constraints:

0 <= tokens.length <= 1000
0 <= tokens[i], power < 10^4
 */

/*
Approach: Greedy + Two Pointers

Goal:
- Maximize score.
- You may:
    1) Play token face up  → lose power, gain 1 score.
    2) Play token face down → gain power, lose 1 score.
- Each token can be used once.

Key Observations:
- To gain score efficiently, use the smallest token (costs least power).
- If stuck (not enough power), trade score for power using the largest token.
- Always try to increase score first.
- Only sacrifice score when necessary.

Algorithm Steps:

1. Sort tokens.
   - Enables smallest-first and largest-last access.

2. Initialize:
   - faceUp = 0                → smallest token pointer
   - faceDown = tokens.length  → one past last index
   - currentScore = 0
   - maximumScore = 0

3. While faceUp < faceDown:

   Case 1: Enough power to play smallest token
       → power -= tokens[faceUp]
       → currentScore++
       → faceUp++

   Case 2: Not enough power but have score
       → currentScore--
       → power += tokens[faceDown - 1]
       → faceDown--

   Case 3: No power and no score
       → break

4. Track maximumScore during process.

Why This Works:
- Smallest tokens give cheapest score gain.
- Largest tokens give maximum power recovery.
- Greedy choice is optimal because:
    - Always maximize score growth.
    - Only trade score when absolutely necessary.

Time Complexity:
- O(n log n) for sorting
- O(n) two-pointer scan
- Overall: O(n log n)

Space Complexity:
- O(1) extra space (ignoring sort stack)

Result:
- Returns the maximum score achievable.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _948_Bag_of_Tokens {
    // Method to find the maximum possible score you can achieve after playing any
    // number of tokens
    public static int bagOfTokensScore(int[] tokens, int power) {
        // Sort the array
        Arrays.sort(tokens);

        // Initialize the maximumScore to keep track of the maximum score
        int maximumScore = 0;

        // Initialize the currentScore to keep track of the current score
        int currentScore = 0;

        // Initialize the faceUp and faceDown variable
        int faceUp = 0, faceDown = tokens.length;

        // Iterate over the tokens untill both faceUp and faceDown variable do not cross
        // each other
        while (faceUp < faceDown) {
            // Update the power and current score accordingly
            if (tokens[faceUp] <= power) {
                currentScore++;
                power -= tokens[faceUp++];
            } else if (currentScore > 0) {
                currentScore--;
                power += tokens[--faceDown];
            } else {
                break;
            }

            // Update the maximumScore
            maximumScore = Math.max(maximumScore, currentScore);
        }

        // Return maximumScore
        return maximumScore;
    }

    // Main method to test bagOfTokensScore
    public static void main(String[] args) {
        int[] tokens = new int[] { 100, 200, 300, 400 };
        int power = 200;

        int result = bagOfTokensScore(tokens, power);

        System.out.println(
                "The maximum possible score you can achieve after playing any number of tokens is : " + result);
    }
}
