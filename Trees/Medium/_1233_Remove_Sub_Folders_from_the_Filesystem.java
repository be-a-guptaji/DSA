/*
LeetCode Problem: https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/

Question: 1233. Remove Sub-Folders from the Filesystem

Problem Statement: Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".

The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

    For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.

Example 1:
Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.

Example 2:
Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".

Example 3:
Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]

Constraints:
    1 <= folder.length <= 4 * 10^4
    2 <= folder[i].length <= 100
    folder[i] contains only lowercase letters and '/'.
    folder[i] always starts with the character '/'.
    Each folder name is unique.
*/

/*
Approach: Sort and Linear Scan with Last Root Prefix Check
Goal:
- Remove all sub-folders from a list of folder
  paths and return only the top-level (root)
  folders.
Core Idea:
- Sorting folder paths lexicographically guarantees
  that any sub-folder always appears immediately
  after its parent folder in the sorted order.
- After sorting, a folder is a sub-folder of the
  previous root if and only if it starts with that
  root path followed by '/'.
- Appending '/' to the prefix prevents false
  matches where one folder path is a string prefix
  of another but not an actual parent folder
  (e.g. "/a" vs "/ab").
Algorithm Steps:
1. Sort folder lexicographically.
2. Add folder[0] to result (first path is always
   a root after sorting).
3. For each subsequent folder[i]:
   - If folder[i] does not start with
     result.getLast() + "/", it is a new root;
     add it to result.
   - Otherwise it is a sub-folder of the last
     added root; skip it.
4. Return result.
Why It Works:
- Lexicographic sorting ensures a parent folder
  always precedes all its sub-folders.
- Checking only against the last added root is
  sufficient because any nested sub-folder chain
  (e.g. /a, /a/b, /a/b/c) will be filtered
  incrementally: /a/b is filtered by /a, and
  /a/b/c is also filtered by /a since it still
  starts with /a/.
Time Complexity:
- O(n log n * m)
where n is the number of folders and m is the
average path length, dominated by the sort step.
Space Complexity:
- O(n)
for the result list in the worst case where no
folder is a sub-folder of another.
Result:
- Returns the list of root folders with all
  sub-folders removed.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the answer in any order
  public ArrayList<String> removeSubfolders(String[] folder) {
    // Sort the folders
    Arrays.sort(folder);

    // Initialize the new array list for result
    ArrayList<String> result = new ArrayList<>();

    // Add the first folder to the result
    result.add(folder[0]);

    // Iterate over the folders to check if it is a root
    for (int i = 1; i < folder.length; i++) {
      if (!folder[i].startsWith(result.get(result.size() - 1) + "/")) {
        result.add(folder[i]);
      }
    }

    // Return the result
    return result;
  }
}

// Main Class
public class _1233_Remove_Sub_Folders_from_the_Filesystem {
  // Main method to test removeSubfolders
  public static void main(String[] args) {
    String[] folder = new String[] { "/a", "/a/b", "/c/d", "/c/d/e", "/c/f" };

    ArrayList<String> result = new Solution().removeSubfolders(folder);

    System.out.println("The answer in any order is : " + result);
  }
}
