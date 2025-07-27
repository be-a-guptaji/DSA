/*
LeetCode Problem: https://leetcode.com/problems/first-bad-version/

Question: 278. First Bad Version

Problem Statement: You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example 1:
Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.

Example 2:
Input: n = 1, bad = 1
Output: 1

Constraints:

1 <= bad <= n <= 2^31 - 1
 */

/*
Approach: We solve this problem using **binary search** to minimize the number of API calls to `isBadVersion(version)`.

- Start with a search range of [1, n].
- Repeatedly check the middle version:
  - If it's bad, the first bad version must be at or before it (move the right pointer).
  - If it's good, the first bad version must be after it (move the left pointer).
- When left == right, we've found the first bad version.

This approach works efficiently even when n is large (up to 2^31 - 1).

Time Complexity: O(log n) — Binary search cuts the range in half each time.
Space Complexity: O(1) — Constant space is used.
 */

package BinarySearchAndSorting.Easy;

public class _278_First_Bad_Version {
    // Method to find the first bad version
    public static int firstBadVersion(int n) {
        // Initialize variable for tracking
        int left = 1, right = n, mid;

        // Logic as binary search
        while (left < right) {
            mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Return the value
        return left;
    }

    // Mocking the isBadVersion API
    public static boolean isBadVersion(int n) {
        return n >= 4;
    }

    // Main method to test firstBadVersion
    public static void main(String[] args) {
        int n = 9;

        int result = firstBadVersion(n);

        System.out.println("The number in the string is : " + result);
    }
}
