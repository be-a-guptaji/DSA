/*
LeetCode Problem: https://leetcode.com/problems/crawler-log-folder/

Question: 1598. Crawler Log Folder

Problem Statement: The Leetcode file system keeps a log each time some user performs a change folder operation.

The operations are described below:

"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
"./" : Remain in the same folder.
"x/" : Move to the child folder named x (This folder is guaranteed to always exist).
You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.

The file system starts in the main folder, then the operations in logs are performed.

Return the minimum number of operations needed to go back to the main folder after the change folder operations.

Example 1:
Input: logs = ["d1/","d2/","../","d21/","./"]
Output: 2
Explanation: Use this change folder operation "../" 2 times and go back to the main folder.

Example 2:
Input: logs = ["d1/","d2/","./","d3/","../","d31/"]
Output: 3

Example 3:
Input: logs = ["d1/","../","../","../"]
Output: 0

Constraints:

1 <= logs.length <= 10^3
2 <= logs[i].length <= 10
logs[i] contains lowercase English letters, digits, '.', and '/'.
logs[i] follows the format described in the statement.
Folder names consist of lowercase English letters and digits.
 */

/*
Approach: Simulated Directory Depth Tracking

Goal:
- Compute the minimum operations needed to return to the main folder
  after executing folder change logs.

Operations Meaning:
- "../" → Move up one folder (if possible).
- "./"  → Stay in the same folder.
- "x/"  → Move into a child folder.

Key Idea:
- Track current folder depth using a counter.
- Never allow depth to go below 0 (main folder).

Algorithm:

1. Initialize:
   depth = 0

2. For each log in logs:
   - If log == "../"
       → depth = max(0, depth - 1)
   - Else if log == "./"
       → do nothing
   - Else (moving into a folder)
       → depth++

3. After processing all logs:
   - depth represents how many steps are needed to return to main folder.

Why This Works:
- Each subfolder increases depth.
- Each "../" decreases depth (but not below 0).
- "./" has no effect.
- Returning to main folder requires exactly `depth` upward moves.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the minimum number of operations to reach the main folder.
*/

package StacksAndQueues.Easy;

public class _1598_Crawler_Log_Folder {
    // Method to find the minimum number of operations needed to go back to the main
    // folder after the change folder operations
    public static int minOperations(String[] logs) {
        // Initialize the integer to get the depth of the folder structure
        int depth = 0;

        // Iterate over the logs for the operations
        for (String log : logs) {
            // Update depth accordingly
            if (log.equals("../")) {
                depth = (depth == 0) ? 0 : depth - 1;
            } else if (!log.equals("./")) {
                depth++;
            }
        }

        // Return depth as the result
        return depth;
    }

    // Main method to test minOperations
    public static void main(String[] args) {
        String[] logs = new String[] { "d1/", "d2/", "./", "d3/", "../", "d31/" };

        int result = minOperations(logs);

        System.out.println(
                "The minimum number of operations needed to go back to the main folder after the change folder operations is : "
                        + result);
    }
}
