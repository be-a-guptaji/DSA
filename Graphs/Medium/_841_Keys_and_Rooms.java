/*
LeetCode Problem: https://leetcode.com/problems/keys-and-rooms/

Question: 841. Keys and Rooms

Problem Statement: There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

Example 1:
Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation: 
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.

Example 2:
Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.

Constraints:

n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
 */

/*
Approach: BFS Traversal

1. Treat each room as a node in a graph and keys as directed edges to other rooms.
2. Start from room 0 (since it’s unlocked by default) and perform BFS:
   - Use a queue to process rooms level by level.
   - Use a visited array to mark rooms once they are visited.
   - For each room, add all the keys it contains into the queue.
3. Continue BFS until no more rooms can be visited.
4. After traversal, check if all rooms are marked as visited.
   - If yes, return true (all rooms can be visited).
   - If not, return false (some rooms are unreachable).

Time Complexity: O(N + K), where N = number of rooms and K = total number of keys across all rooms.  
Space Complexity: O(N), for the visited array and queue.
*/

package Graphs.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _841_Keys_and_Rooms {
    // Method to find if all the rooms can be opened or not
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // Initialize the size of the rooms
        int size = rooms.size();

        // Initialize a array of size equal to the number of rooms
        int[] visitedRooms = new int[size];

        // Initialize a queue for the BFS Traversal of the graph
        Queue<Integer> queue = new LinkedList<>();

        // Offer the 0th node to the queue as it is already unlocked
        queue.offer(0);

        // Traverse the queue untill it is not empty
        while (!queue.isEmpty()) {
            // Get the node from the queue
            int node = queue.poll();

            // Room is already visited then continue the iteration
            if (visitedRooms[node] != 0) {
                continue;
            }

            // Mark the room as visited
            visitedRooms[node] = 1;

            // Get the neighbouring nodes of the current node
            List<Integer> list = rooms.get(node);

            // Add all the neighbouring node to the queue
            for (int neighbouringNode : list) {
                queue.offer(neighbouringNode);
            }
        }

        // Check if all the rooms has been visited
        for (int i = 0; i < size; i++) {
            // If any of the room is not visited then return false
            if (visitedRooms[i] == 0) {
                return false;
            }
        }

        // If all things passed then return true
        return true;
    }

    // Main method to test canVisitAllRooms
    public static void main(String[] args) {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(3, 0, 1),
                Arrays.asList(2),
                Arrays.asList(0));

        if (canVisitAllRooms(rooms)) {
            System.out.println("All the rooms can be opened.");
        } else {
            System.out.println("All the rooms can not be opened.");
        }
    }
}