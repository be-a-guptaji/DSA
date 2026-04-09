/*
LeetCode Problem: https://leetcode.com/problems/encode-and-decode-tinyurl/

Question: 535. Encode and Decode TinyURL

Problem Statement: TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.

There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Implement the Solution class:

Solution() Initializes the object of the system.
String encode(String longUrl) Returns a tiny URL for the given longUrl.
String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.

Example 1:
Input: url = "https://leetcode.com/problems/design-tinyurl"
Output: "https://leetcode.com/problems/design-tinyurl"
Explanation:
Solution obj = new Solution();
string tiny = obj.encode(url); // returns the encoded tiny url.
string ans = obj.decode(tiny); // returns the original url after decoding it.

Constraints:
1 <= url.length <= 10^4
url is guranteed to be a valid URL.
*/

/*
Approach: Direct Mapping (Identity Function)

Goal:
- Encode and decode URLs without transformation.

Core Idea:
- No actual encoding or compression is performed.
- The original URL is returned as-is for both encode and decode.

Algorithm Steps:
1. encode(longUrl):
   - Return the input URL directly.
2. decode(shortUrl):
   - Return the input URL directly.

Time Complexity:
- O(1)

Space Complexity:
- O(1)

Result:
- Acts as a placeholder implementation where
  encode and decode are identity operations.

Note:
- Not suitable for real-world URL shortening.
- Practical implementations use hashing or ID mapping.
*/

package Arrays.Medium;

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

// Codec Class
class Codec {

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        return longUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortUrl;
    }
}

public class _535_Encode_and_Decode_TinyURL {
    // Main method to test minOperations
    public static void main(String[] args) {
        String url = "https://leetcode.com/problems/design-tinyurl";

        Codec codec = new Codec();

        System.out.println("The url is : " + codec.decode(codec.encode(url)));
    }
}
