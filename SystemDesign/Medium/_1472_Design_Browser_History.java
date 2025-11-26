/*
LeetCode Problem: https://leetcode.com/problems/design-browser-history/

Question: 1472. Design Browser History

Problem Statement: You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory class:

BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
void visit(string url) Visits url from the current page. It clears up all the forward history.
string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.

Example:
Input:
["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
Output:
[null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]

Explanation:
BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"

Constraints:

1 <= homepage.length <= 20
1 <= url.length <= 20
1 <= steps <= 100
homepage and url consist of  '.' or lower case English letters.
At most 5000 calls will be made to visit, back, and forward.
 */

/*
Approach:
1. We need a data structure that supports three operations efficiently:
       • visit(url)     → go to a new webpage and clear all forward history
       • back(steps)    → move backward in the browsing history
       • forward(steps) → move forward in the browsing history

2. To implement this behavior, we use a doubly linked list where each node stores:
       • url          → the webpage URL
       • previousNode → link to the previously visited page
       • nextNode     → link to the next visited page

3. Two dummy boundary nodes are used:
       • head ("START") → marks the beginning boundary of the history
       • tail ("END")   → marks the ending boundary of the history
   They simplify navigation and remove the need for null checks.

4. The `current` pointer always points to the active webpage.  
   During initialization, we insert the homepage between head and tail
   and mark it as the starting position.

5. visit(url):
       • Create a new node after `current`.
       • Discard all forward history by connecting this new node directly to tail.
       • Move the `current` pointer to this newly created node.

6. back(steps):
       • Move `current` toward previousNode.
       • Stop when:
             – steps become zero, OR
             – we reach the boundary node `head`.
       • Return the URL of the updated current page.

7. forward(steps):
       • Move `current` toward nextNode.
       • Stop when:
             – steps become zero, OR
             – we reach the boundary node `tail`.
       • Return the URL of the updated current page.

8. This doubly linked list solution accurately simulates real browser behavior,
   allowing efficient back and forward navigation while ensuring correct handling
   of the visit operation.

Time Complexity:
       • visit:   O(1)
       • back:    O(steps)
       • forward: O(steps)
Space Complexity: O(n), where n is the number of visited pages stored.
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.List;

public class _1472_Design_Browser_History {
    /**
     * Your BrowserHistory object will be instantiated and called as such:
     * BrowserHistory obj = new BrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */

    // Class to make the BrowserHistory
    private static class BrowserHistory {
        // Initialize the variable to hold the head, tail and current pointer of the
        // doubly linked list
        private final Node head;
        private final Node tail;
        private Node current;

        public BrowserHistory(String homepage) {
            // Initialize the head and tail of the doubly linked list
            this.head = new Node("START", null, null);
            this.tail = new Node("END", null, null);

            // Set the current pointer in between the head and tail pointer
            this.current = new Node(homepage, this.tail, this.head);

            // Join the head and tail to the current pointer
            this.head.nextNode = this.current;
            this.tail.previousNode = this.current;
        }

        public void visit(String url) {
            // Add the URL to the next node of the current and add tail after that
            Node newSite = new Node(url, this.tail, this.current);

            // Set the tail and current pointer correctly
            this.current.nextNode = newSite;
            this.tail.previousNode = newSite;

            // Move the current pointer
            this.current = newSite;
        }

        public String back(int steps) {
            // Iterate back untill the head is found or the steps is extinguished
            while (this.current.previousNode != this.head && steps != 0) {
                // Move the pointer backward
                this.current = this.current.previousNode;

                // Decrement the step
                steps--;
            }

            // Return the current node URL
            return this.current.url;
        }

        public String forward(int steps) {
            // Iterate forward untill the tail is found or the steps is extinguished
            while (this.current.nextNode != this.tail && steps != 0) {
                // Move the pointer forward
                this.current = this.current.nextNode;

                // Decrement the step
                steps--;
            }

            // Return the current node URL
            return this.current.url;
        }

        // Helper class to make a doubly linked list
        private class Node {
            // Initialize the pointer for the next and previous pointer
            private Node nextNode;
            private Node previousNode;

            // Initialize the string variable to hold the URL
            private final String url;

            // Constructor to initialize the variables
            public Node(String value, Node next, Node previous) {
                // Set the variables
                this.url = value;
                this.nextNode = next;
                this.previousNode = previous;
            }
        }
    }

    // Main method to test BrowserHistory
    public static void main(String[] args) {

        String[] operations = {
                "BrowserHistory", "visit", "visit", "visit", "back", "back", "forward", "visit", "forward", "back",
                "back"
        };

        List<String[]> values = new ArrayList<>();
        values.add(new String[] { "leetcode.com" });
        values.add(new String[] { "google.com" });
        values.add(new String[] { "facebook.com" });
        values.add(new String[] { "youtube.com" });
        values.add(new String[] { "1" });
        values.add(new String[] { "1" });
        values.add(new String[] { "1" });
        values.add(new String[] { "linkedin.com" });
        values.add(new String[] { "2" });
        values.add(new String[] { "2" });
        values.add(new String[] { "7" });

        // Create an instance of BrowserHistory
        BrowserHistory browserHistory = new BrowserHistory(values.get(0)[0]);

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("BrowserHistory")) {
                browserHistory = new BrowserHistory(values.get(i)[0]);
                System.out.println("null");
            }
            if (operation.equals("visit")) {
                browserHistory.visit(values.get(i)[0]);
                System.out.println("null");
            }
            if (operation.equals("back")) {
                System.out.println(browserHistory.back(Integer.parseInt(values.get(i)[0])));
            }
            if (operation.equals("forward")) {
                System.out.println(browserHistory.forward(Integer.parseInt(values.get(i)[0])));
            }
        }
    }
}
