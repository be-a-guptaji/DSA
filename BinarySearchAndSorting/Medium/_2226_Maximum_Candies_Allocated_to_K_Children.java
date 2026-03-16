/*
LeetCode Problem: https://leetcode.com/problems/maximum-candies-allocated-to-k-children/

Question: 2226. Maximum Candies Allocated to K Children

Problem Statement: You are given a 0-indexed integer array candies. Each element in the array denotes a pile of candies of size candies[i]. You can divide each pile into any number of sub piles, but you cannot merge two piles together.

You are also given an integer k. You should allocate piles of candies to k children such that each child gets the same number of candies. Each child can be allocated candies from only one pile of candies and some piles of candies may go unused.

Return the maximum number of candies each child can get.

Example 1:
Input: candies = [5,8,6], k = 3
Output: 5
Explanation: We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. It can be proven that each child cannot receive more than 5 candies.

Example 2:
Input: candies = [2,5], k = 11
Output: 0
Explanation: There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at least one candy. Thus, each child gets no candy and the answer is 0.

Constraints:

1 <= candies.length <= 10^5
1 <= candies[i] <= 10^7
1 <= k <= 10^12
*/

/*
Approach: Binary Search on Answer (Feasibility Check)

Goal:
- Determine the maximum number of candies each child can receive
  such that at least k children get the same amount.

Core Idea:
- The answer lies between:
    1 → minimum candies per child
    totalCandies / k → theoretical upper bound.
- Use binary search to find the maximum feasible value.
- A helper function checks if x candies per child can be distributed
  to at least k children.

Algorithm Steps:
1. Compute the upper bound:
   - right = sum(candies) / k.
2. Initialize search range [1, right].
3. For each mid value:
   - Check if we can form at least k piles of size mid.
4. If feasible:
   - Record mid as candidate answer.
   - Move left boundary to mid + 1 to maximize.
5. Otherwise:
   - Reduce right boundary to mid - 1.
6. Continue until search space collapses.

Time Complexity:
- O(n log M)
  n = number of piles
  M = maximum possible candies per child.

Space Complexity:
- O(1)

Result:
- Returns the maximum candies each child can receive.
*/
package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
    // Method to find the maximum number of candies each child can get
    public int maximumCandies(int[] candies, long k) {
        // Initialize the left and right bound
        long left = 1, right = 0;

        // Get the right bound
        for (int candy : candies) {
            right += candy;
        }

        // Adjust the right bound
        right /= k;

        // Initialize the maximumCandiesToEachChildren variable
        long maximumCandiesToEachChildren = 0;

        // Iterate over the bound
        while (left <= right) {
            // Get the middle element
            long mid = (left + right) >> 1;

            // If we can give mid candies to each children then shift the left pointer and
            // update the maximumCandiesToEachChildren else update the right pointer
            if (this.canGiveToAllChildrens(candies, k, mid)) {
                // Update the left pointer
                left = mid + 1;

                // Update the maximumCandiesToEachChildren
                maximumCandiesToEachChildren = Math.max(maximumCandiesToEachChildren, mid);
            } else {
                // Update the right pointer
                right = mid - 1;
            }
        }

        // Return the maximumCandiesToEachChildren
        return (int) maximumCandiesToEachChildren;
    }

    // Helper method to determine if give x candies to all childrens
    private boolean canGiveToAllChildrens(int[] candies, long k, long x) {
        // Iterate over the candies to update the k
        for (int candy : candies) {
            // Update the k
            k -= candy / x;

            // If k is less than one then return true
            if (k <= 0) {
                return true;
            }
        }

        // Return false in the end
        return false;
    }
}

public class _2226_Maximum_Candies_Allocated_to_K_Children {
    // Main method to test maximumCandies
    public static void main(String[] args) {
        int[] candies = new int[] { 5, 8, 6 };
        int k = 3;

        int result = new Solution().maximumCandies(candies, k);

        System.out.println("The maximum number of candies each child can get is : " + result);
    }
}