/*
LeetCode Problem: https://leetcode.com/problems/candy/

Question: 135. Candy

Problem Statement: There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.

Constraints:

n == ratings.length
1 <= n <= 2 * 10^4
0 <= ratings[i] <= 2 * 10^4
*/

/*
Approach:
We use a greedy two-pass strategy to determine the minimum number of candies to distribute such that:
1. Every child gets at least one candy.
2. Any child with a higher rating than their immediate neighbor gets more candies.

Steps:
- First, initialize an array `arr[]` of the same length as `ratings`, and set all values to 1.
- Perform a left-to-right pass:
    - If the current child's rating is greater than the previous one, they get one more candy than the previous child.
- Perform a right-to-left pass:
    - If the current child's rating is greater than the next one, and they don't already have more candies, give them one more than the next child.
- Finally, sum up the values in `arr[]` to get the total number of candies needed.

Time Complexity: O(n), where n is the number of children.
Space Complexity: O(n), due to the additional array used to store candy counts.
*/

package Greedy.Hard;

public class _135_Candy {
    // Method to find the minimum number of candies to distribute
    public static int candy(int[] ratings) {
        // Initialize the size of the array
        int size = ratings.length;
        
        // Array to store candies given to each child
        int[] arr = new int[size];
        
        // Initialize each child with 1 candy
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        
        // Traverse from left to right, if the current child has a higher rating than
        // the previous one, give one more candy than the previous child
        for (int i = 1; i < size; i++) {
            if (ratings[i] > ratings[i - 1]) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        
        // Traverse from right to left, if the current child has a higher rating than
        // the next one and doesn't already have more candies, give one more than the
        // next child
        for (int i = size - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                if (arr[i] <= arr[i + 1]) {
                    arr[i] = arr[i + 1] + 1;
                }
            }
        }
        
        // Initialize the candies with the zero
        int candies = 0;
        
        // Sum up the total candies
        for (int i = 0; i < size; i++) {
            candies += arr[i];
        }

        // Return the candies
        return candies;
    }

    // Main method to test candy
    public static void main(String[] args) {
        int[] ratings = { 1, 0, 2 };

        int result = candy(ratings);

        System.out.println("The minimum number of candies need to be distribute are : " + result);
    }
}
