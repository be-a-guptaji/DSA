/*
LeetCode Problem: https://leetcode.com/problems/fruit-into-baskets/

Question: 904. Fruit Into Baskets

Problem Statement: You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

Example 1:
Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.

Example 2:
Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].

Example 3:
Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].

Constraints:
1 <= fruits.length <= 10^5
0 <= fruits[i] < fruits.length
*/

/*
Approach: Sliding Window with Two Fruit Types

Goal:
- Find the longest contiguous subarray containing
  at most two distinct fruit types.

Core Idea:
- Maintain a sliding window that contains at most
  two fruit types.
- Each basket stores:
    [fruitType, lastOccurrenceIndex]
- When a third fruit type appears:
   - Remove the fruit type whose most recent
     occurrence is farther left.
   - Move the window start just after that index.
- Continue expanding the window and track the
  maximum length.

Algorithm Steps:
1. Maintain two baskets:
      [fruitType, lastIndex]
2. Expand the right pointer.
3. If current fruit matches an existing basket:
      update its last occurrence.
4. If an empty basket exists:
      place the fruit there.
5. Otherwise a third fruit type is found:
      - Remove the fruit whose last occurrence
        is smaller.
      - Move left to:
            removedLastIndex + 1
      - Store the new fruit type.
6. Update:
      maxLength = max(maxLength, right - left + 1)
7. Return maxLength.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the maximum number of fruits that can
  be collected using two baskets.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the maximum number of fruits you can pick
  public int totalFruit(int[] fruits) {
    // Initialize the basket variable fruits and the last index of fruits
    int[][] basket = new int[][] { { -1, -1 }, { -1, -1 } };

    // Initialize the totalFruits varaible
    int totalFruits = 0;

    // Iterate over the loop for getting totalFruits of two kind
    for (int left = 0, right = 0; right < fruits.length; right++) {
      // Update the pointers
      if (basket[0][0] == -1) {
        basket[0][0] = fruits[right];
        basket[0][1] = right;
      } else if (basket[0][0] == fruits[right]) {
        basket[0][1] = right;
      } else if (basket[1][0] == -1) {
        basket[1][0] = fruits[right];
        basket[1][1] = right;
      } else if (basket[1][0] == fruits[right]) {
        basket[1][1] = right;
      } else {
        if (basket[0][1] < basket[1][1]) {
          left = basket[0][1] + 1;
          basket[0][0] = fruits[right];
          basket[0][1] = right;
        } else {
          left = basket[1][1] + 1;
          basket[1][0] = fruits[right];
          basket[1][1] = right;
        }
      }

      // Update the totalFruits variable
      totalFruits = Math.max(totalFruits, right - left + 1);
    }

    // Retrun the totalFruits
    return totalFruits;
  }
}

public class _904_Fruit_Into_Baskets {
  // Main method to test totalFruit
  public static void main(String[] args) {
    int[] fruits = new int[] { 1, 2, 3, 2, 2 };

    int result = new Solution().totalFruit(fruits);

    System.out.println("The maximum number of fruits you can pick is : " + result);
  }
}
