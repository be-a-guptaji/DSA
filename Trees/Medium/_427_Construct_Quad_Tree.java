/*
LeetCode Problem: https://leetcode.com/problems/construct-quad-tree/

Question: 427. Construct Quad Tree

Problem Statement: Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.

Return the root of the Quad-Tree representing grid.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

    val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
    isLeaf: True if the node is a leaf node on the tree or False if the node has four children.

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}

We can construct a Quad-Tree from a two-dimensional area using the following steps:

    If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
    If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
    Recurse for each of the children with the proper sub-grid.

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:

You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.

It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].

If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.

Example 1:
Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.

Example 2:
Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
The topLeft, bottomLeft and bottomRight each has the same value.
The topRight have different values so we divide it into 4 sub-grids where each has the same value.
Explanation is shown in the photo below:

Constraints:
    n == grid.length == grid[i].length
    n == 2^x where 0 <= x <= 6
*/

/*
Approach: Recursive Quadrant Decomposition with Merge-on-Uniformity
Goal:
- Build a Quad-Tree representation of an n x n
  binary grid, where each leaf node represents a
  region of uniform value.
- Return the root of the constructed Quad-Tree.
Core Idea:
- Recursively divide the current square region into
  four equal quadrants: top-left, top-right,
  bottom-left, bottom-right.
- A region can be represented as a single leaf node
  only if every cell within it holds the same value;
  otherwise it must be split further.
- Base case: a 1x1 region is trivially uniform and
  becomes a leaf directly.
- After recursively building all four child
  quadrants, check if they can be merged back into
  a single leaf (all four are leaves with identical
  values); if so, collapse them into one node,
  since that yields a more compact tree.
Algorithm Steps:
1. Call dfs(grid, n, 0, 0) with n = grid.length to
   process the full grid starting at the top-left
   corner.
2. In dfs(grid, n, row, col):
   a. If n == 1, the region is a single cell;
      return a leaf node with val =
      (grid[row][col] == 1) and isLeaf = true.
   b. Otherwise, compute mid = n / 2, splitting the
      current n x n region into four (n/2) x (n/2)
      quadrants.
   c. Recursively compute:
      - topLeft = dfs(grid, mid, row, col)
      - topRight = dfs(grid, mid, row, col + mid)
      - bottomLeft = dfs(grid, mid, row + mid, col)
      - bottomRight = dfs(grid, mid, row + mid,
        col + mid)
   d. If all four children are leaves and share the
      same val, merge them: return a single new leaf
      node with that shared val.
   e. Otherwise, return an internal (non-leaf) node
      with isLeaf = false and all four children
      attached.
3. The initial call's returned node is the root of
   the Quad-Tree.
Why It Works:
- Every region is either fully uniform (mergeable
  into one leaf) or not; checking this condition
  bottom-up after full recursion guarantees the
  merge decision is made with complete information
  about all four sub-quadrants.
- Recursing on halved dimensions at each step
  guarantees termination at n == 1, since n is
  constrained to be a power of 2 for valid
  Quad-Tree construction.
- Merging only when strictly all four children are
  uniform leaves of equal value preserves
  correctness: any non-uniform quadrant forces its
  parent to remain an internal node too.
Time Complexity:
- O(n^2 log n)
where n is the grid dimension. At each of the
O(log n) recursive depths, the total work across all
calls at that depth is O(n^2) (each cell is compared
a constant number of times per depth level in the
worst case of a checkerboard-like grid).
Space Complexity:
- O(log n)
for the recursive call stack depth in the best/
average case, though the constructed tree itself can
require O(n^2) nodes in the worst case (fully
non-uniform grid, e.g., checkerboard pattern).
*/

package Trees.Medium;

// Solution Class
class Solution {
  // Method to find the root of the Quad-Tree representing grid
  public Node construct(int[][] grid) {
    // Call the recursive dfs method
    return this.dfs(grid, grid.length, 0, 0);
  }

  // Helper method to find the leaf node
  private Node dfs(int[][] grid, int n, int row, int col) {
    // If n is 1 return the node value and set isLeaf to true
    if (n == 1) {
      return new Node(grid[row][col] == 1, true);
    }

    // Get the mid value
    int mid = n >> 1;

    // Call the recursive dfs method to all four division
    Node topLeft = this.dfs(grid, mid, row, col);
    Node topRight = this.dfs(grid, mid, row, col + mid);
    Node bottomLeft = this.dfs(grid, mid, row + mid, col);
    Node bottomRight = this.dfs(grid, mid, row + mid, col + mid);

    // If all the nodes are the same and leaf node then return the single node
    if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf && topLeft.val == topRight.val
        && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
      return new Node(topLeft.val, true);
    }

    // Return the all four child
    return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
  }
}

/*
 * // Definition for a QuadTree node.
 * class Node {
 * public boolean val;
 * public boolean isLeaf;
 * public Node topLeft;
 * public Node topRight;
 * public Node bottomLeft;
 * public Node bottomRight;
 * 
 * 
 * public Node() {
 * this.val = false;
 * this.isLeaf = false;
 * this.topLeft = null;
 * this.topRight = null;
 * this.bottomLeft = null;
 * this.bottomRight = null;
 * }
 * 
 * public Node(boolean val, boolean isLeaf) {
 * this.val = val;
 * this.isLeaf = isLeaf;
 * this.topLeft = null;
 * this.topRight = null;
 * this.bottomLeft = null;
 * this.bottomRight = null;
 * }
 * 
 * public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node
 * bottomLeft, Node bottomRight) {
 * this.val = val;
 * this.isLeaf = isLeaf;
 * this.topLeft = topLeft;
 * this.topRight = topRight;
 * this.bottomLeft = bottomLeft;
 * this.bottomRight = bottomRight;
 * }
 * }
 */

// Node Class
class Node {
  public boolean val;
  public boolean isLeaf;
  public Node topLeft;
  public Node topRight;
  public Node bottomLeft;
  public Node bottomRight;

  public Node() {
    this.val = false;
    this.isLeaf = false;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public Node(boolean val, boolean isLeaf) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomLeft = bottomLeft;
    this.bottomRight = bottomRight;
  }
}

// Main Class
public class _427_Construct_Quad_Tree {
  // Main method to test construct
  public static void main(String[] args) {
    int[][] grid = new int[][] {
        { 1, 1, 1, 1, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 0, 0, 0, 0 }
    };

    Node result = new Solution().construct(grid);

    System.out.println("The root of the Quad-Tree representing grid is : " + result);
  }
}
