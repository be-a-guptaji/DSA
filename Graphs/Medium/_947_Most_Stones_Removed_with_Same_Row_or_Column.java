/*
LeetCode Problem: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

Question: 947. Most Stones Removed with Same Row or Column

Problem Statement: On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

Example 1:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.

Example 3:
Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.

Constraints:

1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
 */

/*
Approach:
1. The problem is about identifying how many stones can be removed such that each remaining stone
   shares its row or column with at least one other stone.
2. This can be visualized as a graph where:
   - Each stone connects its row and column as nodes in the graph.
   - If stones share a row or column, they are part of the same connected component.
3. We model this using Union-Find (Disjoint Set):
   - Treat each row and column as a unique node.
   - Use offsetting for columns to avoid index collision with rows.
   - Union each stone's row node with its column node.
4. After processing all stones:
   - Count the number of unique connected components (using `find` on unique nodes).
   - The number of stones that can be removed is:
     totalStones - number of connected components
5. Key insight:
   - In each connected component, we can remove all stones except one.
   - So, the answer is: totalStones - connectedComponents.

Time Complexity: O(N * α(N)), where N is number of stones and α is inverse Ackermann (very small).
Space Complexity: O(N), for Union-Find structure and hash map.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.HashMap;

public class _947_Most_Stones_Removed_with_Same_Row_or_Column {
    // Method to determine most stone can be removed by same column or row
    public static int removeStones(int[][] stones) {
        // Initialize the number of stone in the grid, connected components, rows and
        // column of the grid
        int totalStones = stones.length, connectedComponents = 0, maxRow = 0, maxCol = 0;

        // Add all the stone position to the set
        for (int i = 0; i < totalStones; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        // Create a disjoint set with enough nodes to represent both rows and columns
        // We offset columns by (maxRow + 1) to avoid collision with row indexes
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);

        // Create a HashMap for the tracking of stones position
        HashMap<Integer, Integer> stonePosition = new HashMap<>();

        // For each stone, union its row and column
        for (int[] stone : stones) {
            int rowNode = stone[0];
            int colNode = stone[1] + maxRow + 1; // Offset column to separate from row

            ds.unionBySize(rowNode, colNode); // Union the row and column
            stonePosition.put(rowNode, 1);
            stonePosition.put(colNode, 1);
        }

        // Count the connected components from the map
        for (HashMap.Entry<Integer, Integer> en : stonePosition.entrySet()) {
            if (ds.findUnionParent(en.getKey()) == en.getKey()) {
                connectedComponents++;
            }
        }

        // Maximum number of stone can be removed from the grid is total number of
        // stones minus connected components in the graph
        return totalStones - connectedComponents;
    }

    // Helper Union Find class for finding the connected components
    private static class DisjointSet {
        ArrayList<Integer> rank = new ArrayList<>();
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        // Initialize the disjoint set with n elements
        public DisjointSet(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i); // Initially, each node is its own parent
                rank.add(0); // Rank (for union by rank) is 0
                size.add(1); // Size (for union by size) is 1
            }
        }

        // Find the ultimate parent (root) of a node with path compression
        public int findUnionParent(int node) {
            if (parent.get(node) != node) {
                parent.set(node, findUnionParent(parent.get(node))); // Path compression
            }
            return parent.get(node);
        }

        // Union two nodes based on their rank
        public void unionByRank(int u, int v) {
            int rootU = findUnionParent(u);
            int rootV = findUnionParent(v);

            if (rootU == rootV) {
                return; // already in the same set
            }

            if (rank.get(rootU) < rank.get(rootV)) {
                parent.set(rootU, rootV);
            } else if (rank.get(rootU) > rank.get(rootV)) {
                parent.set(rootV, rootU);
            } else {
                parent.set(rootV, rootU);
                rank.set(rootU, rank.get(rootU) + 1);
            }
        }

        // Union two nodes based on their size
        public void unionBySize(int u, int v) {
            int rootU = findUnionParent(u);
            int rootV = findUnionParent(v);

            if (rootU == rootV) {
                return; // already in the same set
            }

            if (size.get(rootU) < size.get(rootV)) {
                parent.set(rootU, rootV);
                size.set(rootV, size.get(rootV) + size.get(rootU));
            } else {
                parent.set(rootV, rootU);
                size.set(rootU, size.get(rootU) + size.get(rootV));
            }
        }
    }

    // Main method to test removeStones
    public static void main(String[] args) {
        int[][] stones = {
                { 0, 0 },
                { 0, 1 },
                { 1, 0 },
                { 1, 2 },
                { 2, 1 },
                { 2, 2 }
        };

        int result = removeStones(stones);

        System.out.println("Total number of stone removed by same rows and column are : " + result);
    }
}