/*
LeetCode Problem: https://leetcode.com/problems/koko-eating-bananas/

Question: 875. Koko Eating Bananas

Problem Statement: Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:

1 <= piles.length <= 10^4
piles.length <= h <= 10^9
1 <= piles[i] <= 10^9
*/

/*
Approach: Binary Search on Eating Speed

We search for the minimum eating speed K such that Koko can finish all banana piles
within h hours.

Search Space:
- Minimum speed = 1 (at least 1 banana per hour)
- Maximum speed = max value in the piles array

Step 1: Determine the upper bound
- The maximum pile size is the worst-case eating speed.

Step 2: Apply Binary Search on Speed
- Pick a middle speed.
- Check if Koko can eat all bananas within h hours at this speed.

Step 3: Validation using Helper Function
- For each pile, compute hours needed using:
  time = ceil(pile / speed)
- Subtract the time from h.
- If h becomes negative, the speed is insufficient.

Binary Search Decisions:
- If Koko can finish → try smaller speed (move left).
- If Koko cannot finish → increase speed (move right).

Final Answer:
- The smallest valid speed found during the search.

Time Complexity: O(n log m)
- n = number of piles
- m = maximum bananas in a pile

Space Complexity: O(1)
*/

package BinarySearchAndSorting.Medium;

public class _875_Koko_Eating_Bananas {
    // Method to find the minimum eating speed of Koko
    public static int minEatingSpeed(int[] piles, int h) {
        // Initialize the three variable for the lower and upper range of eating speed
        // of Koko and current eating speed of Koko
        int minimumSpeedOfEatingBananas = 1, maximumSpeedOfEatingBananas = Integer.MIN_VALUE,
                currentSpeedOfEatingBananas = Integer.MAX_VALUE;

        // Get the lower and upper range of eating speed of Koko by iterating over the
        // piles
        for (int bananas : piles) {
            // Get the upper range of eating speed of Koko
            maximumSpeedOfEatingBananas = Math.max(maximumSpeedOfEatingBananas, bananas);
        }

        // Iterate over the range to of eating speed of Koko to find the minimum eating
        // speed of Koko
        while (minimumSpeedOfEatingBananas <= maximumSpeedOfEatingBananas) {
            // Get the middelSpeedOfEatingBananas
            int middelSpeedOfEatingBananas = minimumSpeedOfEatingBananas
                    + (maximumSpeedOfEatingBananas - minimumSpeedOfEatingBananas) / 2;

            // If Koko can eat all the bananas in the middelSpeedOfEatingBananas then move
            // the maximumSpeedOfEatingBananas to the middelSpeedOfEatingBananas else move
            // minimumSpeedOfEatingBananas to the middelSpeedOfEatingBananas
            if (canEatAllTheBananas(middelSpeedOfEatingBananas, piles, h)) {
                maximumSpeedOfEatingBananas = middelSpeedOfEatingBananas - 1;

                // Update the currentSpeedOfEatingBananas of Koko
                currentSpeedOfEatingBananas = Math.min(currentSpeedOfEatingBananas, middelSpeedOfEatingBananas);
            } else {
                minimumSpeedOfEatingBananas = middelSpeedOfEatingBananas + 1;
            }
        }

        // Return the currentSpeedOfEatingBananas of Koko
        return currentSpeedOfEatingBananas;
    }

    // Helper method to determine if Koko can eat all the bananas in time or not
    private static boolean canEatAllTheBananas(int eatingSpeed, int[] piles, int h) {
        // Check if Koko can eat all the bananas by iterating over the piles array
        for (int bananas : piles) {
            // Get the time to eat the bananas in the current pile
            int time = Math.ceilDiv(bananas, eatingSpeed);

            // Reduse the time form the total hours of time to eat the bananas
            h -= time;

            // If time goes negative then retrun false
            if (0 > h) {
                return false;
            }
        }

        // Return true if Koko can eat all the bananas with the eatingSpeed
        return true;
    }

    // Main method to test minEatingSpeed
    public static void main(String[] args) {
        int[] piles = { 3, 6, 7, 11 };
        int h = 8;

        int result = minEatingSpeed(piles, h);

        System.out
                .println("The minimum eating speed of Koko in which he can eat all the bananas in time is : " + result);
    }
}
