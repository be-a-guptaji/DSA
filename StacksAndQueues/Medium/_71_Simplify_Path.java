/*
LeetCode Problem: https://leetcode.com/problems/simplify-path/

Question: 71. Simplify Path

Problem Statement: You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.

The rules of a Unix-style file system are as follows:

A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.

Example 1:
Input: path = "/home/"
Output: "/home"
Explanation:
The trailing slash should be removed.

Example 2:
Input: path = "/home//foo/"
Output: "/home/foo"
Explanation:
Multiple consecutive slashes are replaced by a single one.

Example 3:
Input: path = "/home/user/Documents/../Pictures"
Output: "/home/user/Pictures"
Explanation:
A double period ".." refers to the directory up a level (the parent directory).

Example 4:
Input: path = "/../"
Output: "/"
Explanation:
Going one level up from the root directory is not possible.

Example 5:
Input: path = "/.../a/../b/c/../d/./"
Output: "/.../b/d"
Explanation:
"..." is a valid name for a directory in this problem.

Constraints:

1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.
 */

/*
Approach: Stack-Based Directory Simulation

Goal:
- Convert a given absolute Unix-style path into its simplified canonical form.

Rules:
- "/" separates directories.
- "."  → current directory (ignore).
- ".." → move one directory up (pop stack if possible).
- Multiple "/" → treated as single "/".

Algorithm Steps:

1. Split the path by "/"
   - This gives directory components.
   - Empty strings may appear due to consecutive slashes.

2. Use a stack (array + pointer):
   - stackPointer = -1
   - For each part:
       a) If part == "" or part == "."
            → ignore
       b) If part == ".."
            → pop stack if not empty
       c) Else
            → push directory name onto stack

3. Build Result:
   - If stack is empty → return "/"
   - Otherwise:
       - Construct path by appending "/" + directory
       - Maintain original order

Why This Works:
- Stack naturally models directory navigation.
- ".." corresponds to pop.
- Normal directory corresponds to push.
- Ensures valid canonical path.

Example:
Input: "/a/./b/../../c/"
Process:
["", "a", ".", "b", "..", "..", "c", ""]
Stack:
a
a, b
a
(empty)
c
Output: "/c"

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns simplified canonical path.
*/

package StacksAndQueues.Medium;

public class _71_Simplify_Path {
    // Method to find the simplified canonical path
    public static String simplifyPath(String path) {
        // Split the path by "/" to get the string array
        String[] parts = path.split("/");

        // Initialize the stack array of string
        String[] stack = new String[parts.length];

        // Initialize the stack pointer
        int stackPointer = -1;

        // Iterate over the parts
        for (String part : parts) {
            if (!(part.equals("") || part.equals("."))) {
                if (part.equals("..")) {
                    if (stackPointer != -1) {
                        stackPointer--;
                    }
                } else {
                    stack[++stackPointer] = part;
                }
            }
        }

        // If stack is empty then retrun "/"
        if (stackPointer == -1) {
            return "/";
        }

        // Initialize the string builder for the result
        StringBuilder result = new StringBuilder();

        // Append the string to the string builder
        while (stackPointer != -1) {
            result.insert(0, '/' + stack[stackPointer--]);
        }

        // Return the result by converting it into the string
        return result.toString();
    }

    // Main method to test simplifyPath
    public static void main(String[] args) {
        String path = "/../";

        String result = simplifyPath(path);

        System.out.println("The simplified canonical path is : " + result);
    }
}
