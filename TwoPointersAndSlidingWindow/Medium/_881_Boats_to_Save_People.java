/*
LeetCode Problem: https://leetcode.com/problems/boats-to-save-people/

Question: 881. Boats to Save People

Problem Statement: You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.

Example 1:
Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)

Example 2:
Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)

Example 3:
Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)

Constraints:

1 <= people.length <= 5 * 10^4
1 <= people[i] <= limit <= 3 * 10^4
 */

/*
Approach: Greedy Two-Pointer After Sorting (Counting Sort Optimization)

Goal:
Find the minimum number of boats needed so that:
- Each boat can carry at most 2 people
- Total weight in a boat ≤ limit

Key Observations:
- The heaviest person must always take a boat.
- To minimize boats, try to pair the heaviest person with the lightest possible person.
- Sorting helps enable this greedy pairing.

Steps Explained:

1. Sorting via Counting Sort:
   - Find the maximum weight.
   - Use a frequency array to count occurrences of each weight.
   - Rebuild the `people` array in sorted order.
   - This avoids O(n log n) sorting and runs in O(n + maxWeight).

2. Two-Pointer Greedy Strategy:
   - `left` points to the lightest person.
   - `right` points to the heaviest person.
   - Always assign a boat to `people[right]` (heaviest).
   - Check if `people[left]` can fit with them:
       if (people[left] + people[right] <= limit)
       → pair them and move `left`.
   - Move `right` every time since the heaviest always boards.
   - Increment boat count for each iteration.

Why This Works:
- Pairing the heaviest person is mandatory.
- Pairing them with the lightest possible person maximizes utilization.
- This greedy choice is optimal and minimizes boats.

Time Complexity:
- Counting sort: O(n + maxWeight)
- Two-pointer scan: O(n)
- Overall: O(n + maxWeight)

Space Complexity:
- O(maxWeight) for frequency array

Result:
- Returns the minimum number of boats required to rescue everyone.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _881_Boats_to_Save_People {
    // Method to find the minimum number of boats required to rescue all people
    public static int numRescueBoats(int[] people, int limit) {

        // Find the maximum weight among all people
        int maxWeight = Arrays.stream(people).max().getAsInt();

        // Frequency array to count how many people have a given weight
        int[] peoplesFrequency = new int[maxWeight + 1];

        // Populate the frequency array
        for (int person : people) {
            peoplesFrequency[person]++;
        }

        // Index for rebuilding the sorted people array
        // i represents the current weight being processed
        int index = 0, i = 0;

        // Reconstruct the people array in sorted order using counting sort
        while (index < people.length) {

            // Skip weights that have zero frequency
            while (peoplesFrequency[i] == 0) {
                i++;
            }

            // Place the current weight into the array
            people[index++] = i;

            // Decrease the frequency after placement
            peoplesFrequency[i]--;
        }

        // Initialize two pointers for pairing people
        int left = 0; // Lightest person
        int right = people.length - 1; // Heaviest person

        // Variable to store the total number of boats needed
        int totalNumberOfBoats = 0;

        // Greedy pairing using two pointers
        while (left <= right) {

            // Assign a boat to the heaviest remaining person
            int remain = limit - people[right--];
            totalNumberOfBoats++;

            // If the lightest person can fit with the heaviest, pair them
            if (left <= right && remain >= people[left]) {
                left++;
            }
        }

        // Return the total number of boats required
        return totalNumberOfBoats;
    }

    // Main method to test numRescueBoats
    public static void main(String[] args) {
        int[] people = new int[] { 3, 2, 2, 1 };
        int limit = 3;

        int result = numRescueBoats(people, limit);

        System.out.println("The minimum number of boats to carry every given person is : " + result);
    }
}
