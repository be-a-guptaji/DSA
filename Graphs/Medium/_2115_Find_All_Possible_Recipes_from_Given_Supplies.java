/*
LeetCode Problem: https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/

Question: 2115. Find All Possible Recipes from Given Supplies

Problem Statement: You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. A recipe can also be an ingredient for other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

Example 1:
Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".

Example 2:
Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".

Example 3:
Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".

Constraints:
n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.
 */

/*
Approach: DFS with Memoized Cookability and Cycle Detection
Goal:
- Find all recipes that can be made given an
  initial set of supplies, where recipes may
  depend on other recipes as ingredients.
Core Idea:
- Model the problem as a dependency graph: recipe
  nodes depend on ingredient nodes (either supplies
  or other recipes).
- DFS from each recipe resolves dependencies
  recursively; memoize results in canCook to avoid
  recomputation.
- Setting canCook[recipe] = false before recursing
  into its ingredients acts as a cycle detection
  guard: if a recipe is encountered again mid-DFS,
  it returns false (circular dependency, uncookable).
Algorithm Steps:
1. Mark all supplies as canCook = true.
2. Build adjList mapping each recipe to its
   ingredient list.
3. For each recipe, call dfs(recipe, ...):
   a. If canCook contains the recipe, return its
      cached value.
   b. If recipe is not in adjList (unknown
      ingredient), return false.
   c. Set canCook[recipe] = false (cycle guard).
   d. Recurse on each ingredient; if any returns
      false, leave canCook[recipe] = false and
      return false.
   e. Set canCook[recipe] = true and return true.
4. Collect all recipes where dfs returns true.
Why It Works:
- Memoization ensures each recipe is resolved at
  most once, making repeated dependency queries O(1).
- The false sentinel before recursion correctly
  handles circular dependencies: if recipe A
  depends on recipe B which depends on A, the
  second encounter of A returns false immediately.
- Supplies seeded as true terminate the recursion
  at base ingredients without further DFS.
Time Complexity:
- O(R * I) where R is the number of recipes and I
  is the average number of ingredients per recipe,
  since each recipe is fully resolved once.
Space Complexity:
- O(R + S) for canCook map (R recipes + S supplies)
  and O(R) for the DFS call stack depth.
Result:
- Returns the list of all recipes that can be made
  from the given supplies.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the recipes that can be made
  public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    // Initialize the hashmap for the adj list
    HashMap<String, Boolean> canCook = new HashMap<>();

    // Add all the items for the supplies to the hashmap
    for (int i = 0; i < supplies.length; i++) {
      canCook.put(supplies[i], true);
    }

    // Initialize the Hashmap for the ingredients
    HashMap<String, List<String>> adjList = new HashMap<>();

    // Make adjList for the ingredients
    for (int i = 0; i < recipes.length; i++) {
      adjList.put(recipes[i], ingredients.get(i));
    }

    // Initialzie the array list for the result
    ArrayList<String> result = new ArrayList<>();

    // Iterate over the reciepes
    for (int i = 0; i < recipes.length; i++) {
      // Add value to the result array if dfs return true
      if (this.dfs(recipes[i], canCook, adjList)) {
        result.add(recipes[i]);
      }
    }

    // Return the result
    return result;
  }

  // Helper method to find the what can be made
  private boolean dfs(String recipe, HashMap<String, Boolean> canCook, HashMap<String, List<String>> adjList) {
    // If we have value in can cook then return can cook
    if (canCook.containsKey(recipe)) {
      return canCook.get(recipe);
    }

    // If recipe is not in map then return false
    if (!adjList.containsKey(recipe)) {
      return false;
    }

    // Set can cook to false
    canCook.put(recipe, false);

    // Initialize the array list for the recipes
    List<String> recipes = adjList.get(recipe);

    // Iterate over the dependent values
    for (int i = 0; i < recipes.size(); i++) {
      // If we can not cook and of the dependency then return false
      if (!this.dfs(recipes.get(i), canCook, adjList)) {
        return false;
      }
    }

    // Set can cook to true
    canCook.put(recipe, true);

    // Return true in the end
    return true;
  }
}

public class _2115_Find_All_Possible_Recipes_from_Given_Supplies {
  // Main method to test findAllRecipes
  public static void main(String[] args) {
    String[] recipes = { "bread", "sandwich", "burger" };
    List<List<String>> ingredients = Arrays.asList(
        Arrays.asList("yeast", "flour"),
        Arrays.asList("bread", "meat"),
        Arrays.asList("sandwich", "meat", "bread"));
    String[] supplies = { "yeast", "flour", "meat" };

    List<String> result = new Solution().findAllRecipes(recipes, ingredients, supplies);

    System.out.println("The recipes that can be made are : " + result);
  }
}
