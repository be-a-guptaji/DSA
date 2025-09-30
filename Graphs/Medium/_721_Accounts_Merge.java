/*
LeetCode Problem: https://leetcode.com/problems/accounts-merge/

Question: 721. Accounts Merge

Problem Statement: Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Example 2:
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]] 

Constraints:

1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
 */

/*
Approach:
1. The problem can be modeled as finding connected components using Union-Find:
   - Each email is treated as a node in a graph.
   - If two emails appear in the same account, they are connected (union operation).
   - The goal is to group all emails that belong to the same person.
2. Initialization:
   - Create a parent map where each email is its own parent.
   - Maintain a map `emailToName` to associate each email with the corresponding account owner.
3. Union Operations:
   - For every account, take the first email as the representative.
   - Union all other emails of that account with the first email.
   - This ensures all emails belonging to the same account end up in the same connected component.
4. Grouping:
   - For each email, find its root parent using path compression.
   - Group emails by their root parent in a map.
   - Use a TreeSet to automatically keep emails in sorted order.
5. Constructing Result:
   - For each group, create a list starting with the account owner’s name.
   - Append all sorted emails from the TreeSet.
   - Add this list to the final result.

Time Complexity: O(N * α(N) + M log M), N = total number of emails, α(N) = inverse Ackermann function for Union-Find operations (almost constant), M log M = sorting cost for emails in each group  
Space Complexity: O(N), For parent map, emailToName map, and grouped emails.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class _721_Accounts_Merge {
    // Method to merge accounts with the same email
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Initialize parent map for Union-Find (email → parent email)
        Map<String, String> parent = new HashMap<>();

        // Map email → account name
        Map<String, String> emailToName = new HashMap<>();

        // Initialize parent of each email as itself
        for (List<String> account : accounts) {
            // Get name of the account owner
            String name = account.get(0);

            // Add all the emails to the map
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parent.put(email, email); // self-parent initially
                emailToName.put(email, name); // track name for later
            }
        }

        // Union emails in the same account
        for (List<String> account : accounts) {
            // Get the first email of account
            String firstEmail = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                union(parent, firstEmail, account.get(i));
            }
        }

        // Group emails by their root parent
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            unions.putIfAbsent(root, new TreeSet<>()); // TreeSet keeps emails sorted
            unions.get(root).add(email);
        }

        // Build final merged accounts list
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (String root : unions.keySet()) {
            List<String> account = new ArrayList<>();
            account.add(emailToName.get(root)); // account name
            account.addAll(unions.get(root)); // sorted emails
            mergedAccounts.add(account);
        }

        // Return merged accounts
        return mergedAccounts;
    }

    // Union-Find: Find with path compression
    private static String find(Map<String, String> parent, String email) {
        if (!parent.get(email).equals(email)) {
            parent.put(email, find(parent, parent.get(email)));
        }
        return parent.get(email);
    }

    // Union-Find: Union two emails
    private static void union(Map<String, String> parent, String email1, String email2) {
        String root1 = find(parent, email1);
        String root2 = find(parent, email2);
        if (!root1.equals(root2)) {
            parent.put(root1, root2); // merge sets
        }
    }

    // Main method to test accountsMerge
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();

        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        List<List<String>> result = accountsMerge(accounts);

        System.out.println("The merged accounts are : " + result);
    }
}