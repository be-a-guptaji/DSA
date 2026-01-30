/*
LeetCode Problem: https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/

Question: 2570. Merge Two 2D Arrays by Summing Values

Problem Statement: You are given two 2D integer arrays nums1 and nums2.

nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
Each array contains unique ids and is sorted in ascending order by id.

Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:

Only ids that appear in at least one of the two arrays should be included in the resulting array.
Each id should be included only once and its value should be the sum of the values of this id in the two arrays. If the id does not exist in one of the two arrays, then assume its value in that array to be 0.
Return the resulting array. The returned array must be sorted in ascending order by id.

Example 1:
Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
Output: [[1,6],[2,3],[3,2],[4,6]]
Explanation: The resulting array contains the following:
- id = 1, the value of this id is 2 + 4 = 6.
- id = 2, the value of this id is 3.
- id = 3, the value of this id is 2.
- id = 4, the value of this id is 5 + 1 = 6.

Example 2:
Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
Explanation: There are no common ids, so we just include each id with its value in the resulting list.

Constraints:

1 <= nums1.length, nums2.length <= 200
nums1[i].length == nums2[j].length == 2
1 <= idi, vali <= 1000
Both arrays contain unique ids.
Both arrays are in strictly ascending order by id.
 */

/*
Approach: Two Pointers (Merge by ID)

Goal:
Merge two sorted 2D arrays nums1 and nums2 where:
- Each element is [id, value]
- Arrays are sorted by id
- If ids match, sum their values
- Otherwise, keep the smaller id entry

Algorithm:
1. Use two pointers:
   - nums1Index for nums1
   - nums2Index for nums2
2. Compare nums1[nums1Index][0] and nums2[nums2Index][0]:
   - If id1 < id2 → add nums1 entry, move nums1Index
   - If id1 > id2 → add nums2 entry, move nums2Index
   - If equal → add [id, value1 + value2], move both
3. Append remaining elements from the non-exhausted array.
4. Convert the result list into a 2D array.

Why it works:
- Both arrays are sorted → linear merge is optimal.
- Each element is processed once.

Time Complexity: O(n + m)  
Space Complexity: O(n + m) (for result storage)

This is the optimal and clean solution for merging sorted ID-value arrays.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.ArrayList;
import java.util.Arrays;

public class _2570_Merge_Two_2D_Arrays_by_Summing_Values {
    // Method to merge the matrix
    public static int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        // Initialize the Array list for the result
        ArrayList<int[]> resultList = new ArrayList<>();

        // Intialize two pointer for the matrices
        int nums1Index = 0, nums2Index = 0;

        // Itrate over the matrix untill one matix extinguish
        while (nums1Index < nums1.length && nums2Index < nums2.length) {
            // Get the indices and values of the matrices
            int id1 = nums1[nums1Index][0], value1 = nums1[nums1Index][1], id2 = nums2[nums2Index][0],
                    value2 = nums2[nums2Index][1];

            // Fill the result list according to the expression
            if (id1 < id2) {
                resultList.add(new int[] { id1, value1 });

                // Increment the nums1 index
                nums1Index++;
            } else if (id1 > id2) {
                resultList.add(new int[] { id2, value2 });

                // Increment the nums2 index
                nums2Index++;
            } else {
                resultList.add(new int[] { id1, value1 + value2 });

                // Increment both the nums1 and nums2 indices
                nums1Index++;
                nums2Index++;
            }
        }

        // Itrate over the matrix1 untill it extinguish
        while (nums1Index < nums1.length) {
            // Add the value to the list
            resultList.add(new int[] { nums1[nums1Index][0], nums1[nums1Index][1] });

            // Increment the nums1 index
            nums1Index++;
        }

        // Itrate over the matrix2 untill it extinguish
        while (nums2Index < nums2.length) {
            // Add the value to the list
            resultList.add(new int[] { nums2[nums2Index][0], nums2[nums2Index][1] });

            // Increment the nums2 index
            nums2Index++;
        }

        // Intialize the result matrix
        int[][] resultMatrix = new int[resultList.size()][2];

        // Fill the result matrix
        for (int i = 0; i < resultList.size(); i++) {
            resultMatrix[i] = resultList.get(i);
        }

        // Return the result matrix
        return resultMatrix;
    }

    // Main method to test mergeArrays
    public static void main(String[] args) {
        int[][] nums1 = new int[][] { { 2, 4 }, { 3, 6 }, { 5, 5 } }, nums2 = new int[][] { { 1, 3 }, { 4, 3 } };

        int[][] result = mergeArrays(nums1, nums2);

        System.out.println("The merge the matrix is : " + Arrays.deepToString(result));
    }
}
