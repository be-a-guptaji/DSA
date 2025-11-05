/*
LeetCode Problem: https://leetcode.com/problems/number-of-music-playlists/

Question: 920. Number of Music Playlists

Problem Statement: Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:

Every song is played at least once.
A song can only be played again only if k other songs have been played.
Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 10^9 + 7.

Example 1:
Input: n = 3, goal = 3, k = 1
Output: 6
Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].

Example 2:
Input: n = 2, goal = 3, k = 0
Output: 6
Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].

Example 3:
Input: n = 2, goal = 3, k = 1
Output: 2
Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].

Constraints:

0 <= k < n <= goal <= 100
 */

/*
Approach: Dynamic Programming (Top-Down with Memoization)

Problem Summary:
----------------
We have n unique songs and we want to create a playlist of length 'goal' such that:
1. Every song is played at least once.
2. A song can only be replayed after at least 'k' other different songs have been played.

We must find the number of possible playlists that satisfy these conditions.
Since the result can be large, return it modulo 1_000_000_007.

Key Idea:
----------
We define a recursive state with memoization to count all valid playlists.

Let:
  dp[currentGoal][oldSongs] = number of possible playlists of length `currentGoal`
                               formed using exactly `oldSongs` unique songs.

Recurrence:
------------
At each step, we can:
1. **Add a new song** (a song not used before):
     → We have (n - oldSongs) choices.
     → New state: dp[currentGoal - 1][oldSongs + 1]
     
2. **Replay an old song** (a song used before, but not one of the last k):
     → We have (oldSongs - k) choices (only if oldSongs > k).
     → New state: dp[currentGoal - 1][oldSongs]

So:
  dp[currentGoal][oldSongs] =
       (n - oldSongs) * dp[currentGoal - 1][oldSongs + 1]
     + (oldSongs - k) * dp[currentGoal - 1][oldSongs]

Base Cases:
------------
- If currentGoal == 0 and oldSongs == n → 1 valid playlist.
- If currentGoal == 0 or oldSongs > n → 0 (invalid).
- Memoize results to avoid recomputation.

Time Complexity:  O(n * goal)
Space Complexity: O(n * goal)
*/

package DynamicProgramming.Hard;

import java.util.Arrays;

public class _920_Number_of_Music_Playlists {
    // Method to find the number of possible playlists that you can create
    public static int numMusicPlaylists(int n, int goal, int k) {
        // Initialize the dp matrix for memoization
        int[][] dp = new int[goal + 1][n + 1];

        // Fill the dp matrix with -1
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        // Return the recursive count method
        return count(n, goal, k, 0, dp);
    }

    // Helper method to count the playlist
    private static int count(int n, int currentGoal, int k, int oldSongs, int[][] dp) {
        // Base case if current goal is zero and old song is equal to n then return 1
        if (currentGoal == 0 && oldSongs == n) {
            return 1;
        }

        // Base case if current goal is zero and old song is greater to n then return 0
        if (currentGoal == 0 || oldSongs > n) {
            return 0;
        }

        // If we have already memoized the value then reutrn that
        if (dp[currentGoal][oldSongs] != -1) {
            return dp[currentGoal][oldSongs];
        }

        // Choose a new song form the playlist
        long result = ((long) (n - oldSongs) * count(n, currentGoal - 1, k, oldSongs + 1, dp)) % 1_000_000_007;

        // Choose a old song form the playlist if k songs has been played
        if (oldSongs > k) {
            result += ((long) (oldSongs - k) * count(n, currentGoal - 1, k, oldSongs, dp)) % 1_000_000_007;
        }

        // Retrun the result
        return dp[currentGoal][oldSongs] = (int) (result % 1_000_000_007);
    }

    // Main method to test mergeStones
    public static void main(String[] args) {
        int n = 3, goal = 3, k = 1;

        int result = numMusicPlaylists(n, goal, k);

        System.out.println("The number of possible playlists that you can create is : " + result);
    }
}
