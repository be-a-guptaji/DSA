/*
LeetCode Problem: https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/

Question: 2491. Divide Players Into Teams of Equal Skill

Problem Statement: You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.

The chemistry of a team is equal to the product of the skills of the players on that team.

Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.

Example 1:
Input: skill = [3,2,5,1,3,4]
Output: 22
Explanation: 
Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.

Example 2:
Input: skill = [3,4]
Output: 12
Explanation: 
The two players form a team with a total skill of 7.
The chemistry of the team is 3 * 4 = 12.

Example 3:
Input: skill = [1,1,2,3]
Output: -1
Explanation: 
There is no way to divide the players into teams such that the total skill of each team is equal.

Constraints:

2 <= skill.length <= 10^5
skill.length is even.
1 <= skill[i] <= 1000
 */

/*
Approach: Greedy Pairing Using Frequency Counting

Goal:
Pair players so that:
- Each pair has the same total skill
- Total chemistry (skill1 * skill2) is maximized
- If pairing is impossible, return -1

Key Observations:
- There are n players → n/2 pairs.
- Let totalSum = sum of all skills.
- Each pair must sum to:
    equalSkill = (2 * totalSum) / n
  If this is not an integer → impossible.

Strategy:
1. Frequency Count:
   - Use a frequency array to count how many times each skill appears.
   - This allows O(1) lookup for required partners.

2. Validation:
   - Check if (2 * totalSum) % n != 0 → return -1.

3. Greedy Pairing:
   - For each skill:
     - If its frequency is 0, skip.
     - Compute requiredSkill = equalSkill - skill.
     - If requiredSkill is invalid or unavailable → return -1.
     - Special case:
       - If skill == requiredSkill, need at least 2 occurrences.
     - Decrement frequencies for both skills.
     - Add skill * requiredSkill to total chemistry.

Why This Works:
- Every valid pair must sum to equalSkill.
- Frequency tracking ensures each player is used exactly once.
- Greedy pairing is safe because partner choice is forced.

Time Complexity:
- O(n + maxSkill) ≈ O(n)

Space Complexity:
- O(maxSkill) for frequency array

Result:
- Returns total chemistry if valid pairing exists.
- Returns -1 otherwise.
*/

package TwoPointersAndSlidingWindow.Medium;

public class _2491_Divide_Players_Into_Teams_of_Equal_Skill {
    // Method to find the chemistry of a team of all the set
    public static long dividePlayers(int[] skills) {
        // Initialize the frequency array for the skills        
        int[] skillsFrequency = new int[100001];

        // Initialize total sum variable
        long totalSum = 0;

        // Gather the total sum and fill the frequency
        for (int skill : skills) {
            // Update frequency of the skill
            skillsFrequency[skill]++;

            // Add the skill to the total sum
            totalSum += skill;
        }

        // If total sum can not be divided into equal set of 2 then return -1
        if ((2 * totalSum) % skills.length != 0) {
            return -1;
        }

        // Get the equal skill set of the team
        int equalSkill = (int) (2 * totalSum) / skills.length;

        // Initialize the total chemistry variable for the result
        long totalChemistry = 0;

        // Iterate over the skills array for finding the total chemistry
        for (int skill : skills) {
            // If the frequency array for the skills is zero then skip the iteration
            if (skillsFrequency[skill] == 0) {
                continue;
            }

            // Initialize the required skill variable
            int requiredSkill = equalSkill - skill;

            // If required skill is out of range or not in the frequency array for the
            // skills then return -1
            if (requiredSkill < 0 || requiredSkill > 100000 || skillsFrequency[requiredSkill] == 0) {
                return -1;
            }

            // If skill and required skill are same and frequency of the skill is less then
            // 2 then return -1
            if (skill == requiredSkill && skillsFrequency[skill] < 2) {
                return -1;
            }

            // Update the frequency array for the skills
            skillsFrequency[skill]--;
            skillsFrequency[requiredSkill]--;

            // Update totalChemistry
            totalChemistry += (skill * requiredSkill);
        }

        // Return total chemistry as a result
        return totalChemistry;
    }

    // Main method to test dividePlayers
    public static void main(String[] args) {
        int[] skill = { 3, 2, 5, 1, 3, 4 };

        long result = dividePlayers(skill);

        System.out.println("The chemistry of all the teams : " + result);
    }
}
