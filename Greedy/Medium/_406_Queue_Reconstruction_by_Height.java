/*
LeetCode Problem: https://leetcode.com/problems/queue-reconstruction-by-height/

Question: 406. Queue Reconstruction by Height

Problem Statement: You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.

Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).

Example 1:
Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
Explanation:
Person 0 has height 5 with no other people taller or the same height in front.
Person 1 has height 7 with no other people taller or the same height in front.
Person 2 has height 5 with two persons taller or the same height in front, which is person 0 and 1.
Person 3 has height 6 with one person taller or the same height in front, which is person 1.
Person 4 has height 4 with four people taller or the same height in front, which are people 0, 1, 2, and 3.
Person 5 has height 7 with one person taller or the same height in front, which is person 1.
Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue.

Example 2:
Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]

Constraints:

1 <= people.length <= 2000
0 <= hi <= 10^6
0 <= ki < people.length
It is guaranteed that the queue can be reconstructed.
*/

/*
Approach: This solution uses a greedy strategy combined with sorting to reconstruct the queue based on height and position constraints.

- Each person is represented as [height, k], where:
  - 'height' is the person's height.
  - 'k' is the number of people in front of them who have a height greater than or equal to theirs.

- The steps are as follows:
  1. **Sort the people array**:
     - First by descending height (taller people come first).
     - If heights are equal, sort by ascending k (fewer taller people ahead).
     - This ensures that when we place each person in the queue, all taller (or equal height) people are already placed, making the k value accurate.
  2. **Insert each person into the queue**:
     - Initialize an empty list.
     - For each person in the sorted list, insert them at index k.
     - This works because inserting at index k guarantees that exactly k taller or equal-height people are ahead.

- Finally, convert the list back to a 2D array and return it.

Time Complexity: O(n²), where n = number of people. Inserting into a list at an index takes O(n) in the worst case, and we do this for each person.
Space Complexity: O(n), for the result list.
*/

package Greedy.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _406_Queue_Reconstruction_by_Height {
    // Method to reconstruct queue
    public static int[][] reconstructQueue(int[][] people) {
        // Sort by descending height, then ascending k
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });

        // Initialize the queue
        List<int[]> queue = new LinkedList<>();

        // Fill the queue
        for (int[] person : people) {
            queue.add(person[1], person);
        }

        // Return the queue
        return queue.toArray(new int[people.length][]);
    }

    // Main method to test reconstructQueue
    public static void main(String[] args) {
        int[][] nums = { { 2, 4 }, { 3, 4 }, { 9, 0 }, { 0, 6 }, { 7, 1 }, { 6, 0 }, { 7, 3 }, { 2, 5 }, { 1, 1 },
                { 8, 0 } };

        int[][] result = reconstructQueue(nums);

        System.out.println("The reconstructed queue is : " + Arrays.deepToString(result));
    }
}
