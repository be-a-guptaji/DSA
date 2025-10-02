/*
LeetCode Problem: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

Question: 378. Kth Smallest Element in a Sorted Matrix

Problem Statement: Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).

Example 1:
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

Example 2:
Input: matrix = [[-5]], k = 1
Output: -5

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 300
-10^9 <= matrix[i][j] <= 10^9
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2

Follow up:

Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
*/

/*
Approach:
This solution uses a **Binary Search on Value Range** technique to efficiently find the k-th smallest element in a sorted 2D matrix.
The matrix is sorted in ascending order both row-wise and column-wise.

1. Search Space Initialization :
   - Define the search space between the smallest element (`matrix[0][0]`) and the largest element (`matrix[n-1][n-1]`) in the matrix.
   - These values represent the possible range for the k-th smallest element.
2. Binary Search on the Value Range :
   - At each step, compute the middle value (`middle`) of the current range.
   - Use a helper method `findSmallerElement()` to count how many elements in the matrix are less than or equal to `middle`.
3. Adjusting the Search Range :
   - If the count of elements ≤ `middle` is less than `k`, it means the k-th smallest is larger, so shift the search range right (`start = middle + 1`).
   - Otherwise, shift the search range left (`end = middle - 1`), since the k-th smallest could still be `middle`.
4. Return Result :
   - Once the loop finishes, `start` will be pointing to the k-th smallest element.
5. Helper Function (`findSmallerElement`) :
   - For each row, perform binary search to count how many elements are ≤ `middle`.
   - This works efficiently because each row is sorted.

Time Complexity: O(n * log(max - min))
- `n` is the number of rows.
- `log(max - min)` is the number of binary search steps over the value range.
Space Complexity: O(1)
- Only a few variables are used; no extra space is required apart from input.
*/

package HeapsAndPriorityQueues.Medium;

public class _378_Kth_Smallest_Element_in_a_Sorted_Matrix {
    // Method to find the kth smallest element in the matrix
    public static int kthSmallest(int[][] matrix, int k) {
        // Initialize rows and colums and number of element of matirix
        int rows = matrix.length;
        int columns = matrix[0].length;
        int start = matrix[0][0];
        int end = matrix[rows - 1][columns - 1];

        // Do binary serach for finding the kth smallest element in the matrix
        while (start <= end) {
            // Find middle of the matirx
            int middle = start + (end - start) / 2;

            // Get number of small element for the middle
            int lesserElement = findSmallerElement(matrix, middle);
            if (lesserElement <= k - 1) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        // Return the start
        return start;
    }

    // Helper function to find the smaller element than middle in a matrix
    private static int findSmallerElement(int[][] matrix, int middle) {
        // Initialize the number of smaller element to 0
        int numberOfSmallerElement = 0;

        // Traverse all row and do binary search to find number of smaller element
        for (int[] array : matrix) {
            // Initialize the start and end variable
            int start = 0;
            int end = array.length - 1;

            // Do binary serach for finding the kth smallest element in the matrix
            while (start <= end) {
                // Find middle of the matirx
                int mid = start + (end - start) / 2;

                if (array[mid] <= middle) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            // Add start to the number of smaller element
            numberOfSmallerElement += start;
        }

        // Return the number of smaller
        return numberOfSmallerElement;
    }

    // Main method to test kthSmallest
    public static void main(String[] args) {
        int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
        int k = 8;

        int result = kthSmallest(matrix, k);

        System.out.println("The kth smallest element in the matrix is : " + result);
    }
}
