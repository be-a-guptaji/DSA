/*
LeetCode Problem: https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/

Question: 2064. Minimized Maximum of Products Distributed to Any Store

Problem Statement: You are given an integer n indicating there are n specialty retail stores. There are m product types of varying amounts, which are given as a 0-indexed integer array quantities, where quantities[i] represents the number of products of the ith product type.

You need to distribute all products to the retail stores following these rules:

A store can only be given at most one product type but can be given any amount of it.
After distribution, each store will have been given some number of products (possibly 0). Let x represent the maximum number of products given to any store. You want x to be as small as possible, i.e., you want to minimize the maximum number of products that are given to any store.
Return the minimum possible x.

Example 1:
Input: n = 6, quantities = [11,6]
Output: 3
Explanation: One optimal way is:
- The 11 products of type 0 are distributed to the first four stores in these amounts: 2, 3, 3, 3
- The 6 products of type 1 are distributed to the other two stores in these amounts: 3, 3
The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) = 3.

Example 2:
Input: n = 7, quantities = [15,10,10]
Output: 5
Explanation: One optimal way is:
- The 15 products of type 0 are distributed to the first three stores in these amounts: 5, 5, 5
- The 10 products of type 1 are distributed to the next two stores in these amounts: 5, 5
- The 10 products of type 2 are distributed to the last two stores in these amounts: 5, 5
The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5, 5) = 5.

Example 3:
Input: n = 1, quantities = [100000]
Output: 100000
Explanation: The only optimal way is:
- The 100000 products of type 0 are distributed to the only store.
The maximum number of products given to any store is max(100000) = 100000.

Constraints:

m == quantities.length
1 <= m <= n <= 10^5
1 <= quantities[i] <= 10^5
*/

/*
Approach: Binary Search on Answer (Load Distribution Feasibility)

Goal:
- Minimize the maximum number of products assigned to any store
  such that all products are distributed across n stores.

Core Idea:
- The answer (maximum load per store) lies between:
    1 → minimum possible load
    max(quantities) → worst-case load
- Use binary search to find the smallest feasible maximum load.
- For a given x, compute how many stores are required:
    stores_needed = Σ ceil(quantity / x)
- If stores_needed ≤ n → x is feasible.

Algorithm Steps:
1. Set bounds:
   - left = 1
   - right = max(quantities)
2. Perform binary search:
   - mid = candidate maximum load per store.
3. Check feasibility:
   - For each quantity:
       requiredStores += ceil(quantity / mid)
   - If requiredStores ≤ n → valid.
4. If valid:
   - Update result and search smaller x.
5. Else:
   - Increase x.
6. Return minimum valid x.

Time Complexity:
- O(m log M)
  m = number of product types
  M = max quantity

Space Complexity:
- O(1)

Result:
- Returns the minimized maximum number of products
  assigned to any store.
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
    // Method to find the minimum possible x
    public int minimizedMaximum(int n, int[] quantities) {
        // Initialize the left and right bound
        int left = 1, right = 0;

        // Get the right bound
        for (int quantity : quantities) {
            right = Math.max(right, quantity);
        }

        // Initialize the minimumX variable
        int minimumX = right;

        // Iterate over the bound
        while (left <= right) {
            // Find the mid value
            int mid = left + ((right - left) >> 1);

            // Update the bound and minimumX accordingly
            if (this.isValid(n, quantities, mid)) {
                right = mid - 1;
                minimumX = Math.min(minimumX, mid);
            } else {
                left = mid + 1;
            }
        }

        // Return minimumX
        return minimumX;
    }

    // Helper method to find if x is valid or not
    private boolean isValid(int n, int[] quantities, int x) {
        // Iterate over the quantities for finding the minimum of x
        for (int quantity : quantities) {
            // If quantity is not multiple of x thne decrement the n
            if (quantity % x != 0) {
                n--;
            }

            // Decrement the n by the floor value of quantity / x
            n -= (quantity / x);

            // If n is negative then return false
            if (n < 0) {
                return false;
            }
        }

        // Return the true in the end
        return true;
    }
}

public class _2064_Minimized_Maximum_of_Products_Distributed_to_Any_Store {
    // Main method to test minimizedMaximum
    public static void main(String[] args) {
        int n = 7;
        int[] quantities = new int[] { 15, 10, 10 };

        int result = new Solution().minimizedMaximum(n, quantities);

        System.out.println("The minimum possible x is : " + result);
    }
}