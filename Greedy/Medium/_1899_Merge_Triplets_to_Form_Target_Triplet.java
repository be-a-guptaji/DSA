/*
LeetCode Problem: https://leetcode.com/problems/merge-triplets-to-form-target-triplet/

Question: 1899. Merge Triplets to Form Target Triplet

Problem Statement: A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith triplet. You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.

To obtain target, you may apply the following operation on triplets any number of times (possibly zero):

Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.

Example 1:
Input: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
Output: true
Explanation: Perform the following operations:
- Choose the first and last triplets [[2,5,3],[1,8,4],[1,7,5]]. Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],[2,7,5]]
The target triplet [2,7,5] is now an element of triplets.

Example 2:
Input: triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
Output: false
Explanation: It is impossible to have [3,2,5] as an element because there is no 2 in any of the triplets.

Example 3:
Input: triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
Output: true
Explanation: Perform the following operations:
- Choose the first and third triplets [[2,5,3],[2,3,4],[1,2,5],[5,2,3]]. Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
- Choose the third and fourth triplets [[2,5,3],[2,3,4],[2,5,5],[5,2,3]]. Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,5,5]].
The target triplet [5,5,5] is now an element of triplets.

Constraints:

1 <= triplets.length <= 10^5
triplets[i].length == target.length == 3
1 <= ai, bi, ci, x, y, z <= 1000
*/

/*
Approach: Greedy Component-wise Accumulation

Goal:
Determine if we can select some triplets and merge them (taking max per coordinate)
to match the target triplet.

Key Insight:
We only consider triplets that are component-wise ≤ target.  
Any triplet exceeding the target in a coordinate cannot be used.

Steps:

1. Initialize variables first, second, third = 0.
   - These store the running maximums for each coordinate.

2. Iterate through all triplets:
   - Skip the triplet if any element exceeds the target element index-wise.
     Such triplets can never contribute to forming the target.

   - Otherwise, update:
       first  = max(first,  triplet[0])
       second = max(second, triplet[1])
       third  = max(third,  triplet[2])

   - If at any time (first, second, third) equals target:
       return true  (early success)

3. After processing all triplets:
   - If accumulated values equal target → true
   - Otherwise → false

Why It Works:
- The merge operation takes coordinate-wise maximums.
- Only valid triplets help contribute.
- Greedily accumulating maximums ensures required component satisfaction.

Time Complexity: O(n)
Space Complexity: O(1)
*/

package Greedy.Medium;

public class _1899_Merge_Triplets_to_Form_Target_Triplet {
    // Method to find if two triplets can be converted into target or not
    public static boolean mergeTriplets(int[][] triplets, int[] target) {
        // Intialize the three variable for holding the valid value to the triplets
        int first = 0, second = 0, third = 0;

        // Add the valid triplets to the array list
        for (int[] triplet : triplets) {
            // If all the value of the triplet is smaller than the target then add the value
            // of the triplet to the validTriplets
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            }

            // Update the variable values
            first = Math.max(first, triplet[0]);
            second = Math.max(second, triplet[1]);
            third = Math.max(third, triplet[2]);

            // Early break out if all the values are found equal to the target
            if (first == target[0] && second == target[1] && third == target[2]) {
                return true;
            }
        }

        // Return false if values are not equal to the target
        return false;
    }

    // Main method to test leastInterval
    public static void main(String[] args) {
        int[][] triplets = new int[][] { { 2, 5, 3 }, { 2, 3, 4 }, { 1, 2, 5 }, { 5, 2, 3 } };
        int[] target = new int[] { 5, 5, 5 };

        boolean result = mergeTriplets(triplets, target);

        System.out.println("The two triplets can" + (result ? "" : " not") + " be converted into target.");
    }
}
