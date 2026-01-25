/*
LeetCode Problem: https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/

Question: 1700. Number of Students Unable to Eat Lunch

Problem Statement: The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the i​​​​​​th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.

Example 1:
Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0 
Explanation:
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.

Example 2:
Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3

Constraints:

1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] is 0 or 1.
students[i] is 0 or 1.
 */

/*
Approach: Frequency Simulation (No Queue Needed)

Goal:
Determine how many students are unable to eat given their sandwich
preferences.

Key Idea:
Only the count of each preference (0 or 1) matters.
If at any point the next sandwich cannot be matched with any remaining
student preference, the process stops.

Algorithm:
1. Create a count array of size 2:
   - count[0] → number of students preferring sandwich 0
   - count[1] → number of students preferring sandwich 1
2. Count student preferences.
3. Initialize result = total number of sandwiches.
4. Traverse the sandwiches in order:
   - If count[sandwich] > 0:
       • A student can eat it → decrement count[sandwich]
       • Decrement result
   - Else:
       • No student wants this sandwich → stop.
5. Return result (students who couldn’t eat).

Why It Works:
- Queue rotation is unnecessary once preference counts are known.
- The process halts exactly when no matching student exists.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1700_Number_of_Students_Unable_to_Eat_Lunch {
    // Method to find the number of students that are unable to eat
    public static int countStudents(int[] students, int[] sandwiches) {
        // Initialize the frequency count array
        int[] count = new int[2];

        // Iterate over the students array to fill the count array by student frequency
        for (int student : students) {
            count[student]++;
        }

        // Initialize the result variable to the length of the sandwiches length
        int result = sandwiches.length;

        // Iterate over the sandwiches to find the number of students that are unable to
        // eat
        for (int sandwich : sandwiches) {
            // If count of that frequency is more than zero then decrement the result and
            // decrement the count of that frequency and if the count of that frequency is
            // zero then break out of the loop
            if (count[sandwich] > 0) {
                result--;
                count[sandwich]--;
            } else {
                break;
            }
        }

        // Return the result
        return result;
    }

    // Main method to test countStudents
    public static void main(String[] args) {
        int[] students = new int[] { 1, 1, 1, 0, 0, 1 };
        int[] sandwiches = new int[] { 1, 0, 0, 0, 1, 1 };

        int result = countStudents(students, sandwiches);

        System.out.println("The number of students that are unable to eat is : " + result);
    }
}
