/*
LeetCode Problem: https://leetcode.com/problems/time-based-key-value-store/

Question: 981. Time Based Key-Value Store

Problem Statement: Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".

Example 1:
Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"

Constraints:

1 <= key.length, value.length <= 100
key and value consist of lowercase English letters and digits.
1 <= timestamp <= 10^7
All the timestamps timestamp of set are strictly increasing.
At most 2 * 10^5 calls will be made to set and get.
*/

/*
Approach: HashMap + Binary Search (Time-Based Key-Value Store)

We store multiple values for each key along with their timestamps and retrieve the
latest value whose timestamp is less than or equal to the given timestamp.

Data Structure:
- A HashMap where:
  key   → String
  value → ArrayList of (timestamp, value) pairs

Set Operation:
- If the key does not exist, create a new list.
- Append the (timestamp, value) pair to the list.
- Since timestamps are added in increasing order, the list remains sorted.

Get Operation:
- If the key does not exist, return an empty string.
- Apply binary search on the list to find the largest timestamp ≤ given timestamp.
- Update the result whenever a valid timestamp is found.
- Return the most recent valid value.

Why Binary Search Works:
- Timestamps are stored in sorted order.
- We need the closest value in the past.

Time Complexity:
- set(): O(1)
- get(): O(log n)

Space Complexity:
- O(n) to store all key-timestamp-value pairs.
*/

package BinarySearchAndSorting.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _981_Time_Based_Key_Value_Store {
    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */

    // Class to make the TimeMap
    private static class TimeMap {
        // Initialzie the HashMap for storing string to the timestamps
        private final HashMap<String, ArrayList<Info>> stringMapList;

        public TimeMap() {
            // Initialize the stringMapList HashMap
            this.stringMapList = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            // If key is not present in the stringMapList HashMap then add it to the it
            if (!this.stringMapList.containsKey(key)) {
                this.stringMapList.put(key, new ArrayList<>());
            }

            // Add the info class instance in the end
            this.stringMapList.get(key).add(new Info(timestamp, value));
        }

        public String get(String key, int timestamp) {
            // If key is not present in the stringMapList HashMap then return empty string
            if (!this.stringMapList.containsKey(key)) {
                return "";
            }

            // Get the ArrayList corresponding to the key
            ArrayList<Info> list = this.stringMapList.get(key);

            // Initialize the starting and end variable for the binary search
            int start = 0, end = list.size() - 1;

            // Initialize the return string variable
            String result = "";

            // Apply binary search to the list to get the value corresponding to the
            // timestamp
            while (start <= end) {
                // Get the middle index of the list
                int middle = (start + end) / 2;

                // Get the info of the middle index
                Info info = list.get(middle);

                // If info timestamp is equal to the timestamp then return the value else update
                // the variable
                if (info.timestamp <= timestamp) {
                    result = info.value;
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }

            // Return the result
            return result;
        }

        // Helper class to make the info class
        private class Info {
            // Initialize the timestamp variable and its value
            private final int timestamp;
            private final String value;

            // Make a constructor to set the value of the timestamp variable and its value
            public Info(int time, String val) {
                // Set the timestamp variable
                this.timestamp = time;

                // Set the value of the string
                this.value = val;
            }
        }
    }

    // Main method to test TimeMap
    public static void main(String[] args) {

        String[] operations = {
                "TimeMap", "set", "get", "get", "set", "get", "get"
        };

        List<Object[]> values = new ArrayList<>();
        values.add(new Object[] {}); // TimeMap
        values.add(new Object[] { "foo", "bar", 1 }); // set
        values.add(new Object[] { "foo", 1 }); // get
        values.add(new Object[] { "foo", 3 }); // get
        values.add(new Object[] { "foo", "bar2", 4 }); // set
        values.add(new Object[] { "foo", 4 }); // get
        values.add(new Object[] { "foo", 5 }); // get

        TimeMap timeMap = new TimeMap();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("TimeMap")) {
                timeMap = new TimeMap();
                System.out.println("null");
            }

            if (operation.equals("set")) {
                timeMap.set(
                        (String) values.get(i)[0],
                        (String) values.get(i)[1],
                        (int) values.get(i)[2]);
                System.out.println("null");
            }

            if (operation.equals("get")) {
                System.out.println(
                        timeMap.get(
                                (String) values.get(i)[0],
                                (int) values.get(i)[1]));
            }
        }
    }
}
