package problem45_jump_game_ii;
/**
 * Problem 45: Jump Game II - Optimal Solution
 * 
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(1) - Only using constant extra space
 * 
 * Algorithm: Greedy approach using BFS-like thinking
 * - Think of it as finding minimum levels in BFS to reach the end
 * - Each "level" represents positions reachable with the same number of jumps
 * - Use two pointers to track the current level boundary and next level boundary
 */
public class JumpGameII {
    
    /**
     * Greedy solution using implicit BFS levels
     * 
     * Key insight: We can think of this as a BFS problem where each level
     * contains all positions reachable with the same number of jumps.
     * We want to find the minimum number of levels to reach the end.
     * 
     * @param nums array of integers representing maximum jump lengths
     * @return minimum number of jumps to reach the last index
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        
        int jumps = 0;          // Number of jumps taken
        int currentEnd = 0;     // The farthest index reachable with current number of jumps
        int farthest = 0;       // The farthest index reachable with one more jump
        
        // We don't need to consider the last index because we're trying to reach it
        for (int i = 0; i < nums.length - 1; i++) {
            // Update the farthest position we can reach from current position
            farthest = Math.max(farthest, i + nums[i]);
            
            // If we've reached the end of current level (currentEnd),
            // we need to make another jump to continue
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                
                // Early termination: if we can already reach the end, no need to continue
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        
        return jumps;
    }
    
    /**
     * Alternative solution using dynamic programming (less optimal)
     * Time Complexity: O(n²) in worst case
     * Space Complexity: O(n)
     */
    public int jumpDP(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        int[] dp = new int[n];
        // Initialize with max values except the first position
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < n; i++) {
            // From position i, we can jump to positions i+1 to i+nums[i]
            for (int j = i + 1; j <= Math.min(i + nums[i], n - 1); j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Test the solution with provided examples
     */
    public static void main(String[] args) {
        JumpGameII solution = new JumpGameII();
        
        // Test case 1
        int[] nums1 = {2, 3, 1, 1, 4};
        int result1 = solution.jump(nums1);
        System.out.println("Test 1 - Input: [2,3,1,1,4]");
        System.out.println("Expected: 2, Got: " + result1);
        System.out.println("Pass: " + (result1 == 2));
        System.out.println();
        
        // Test case 2
        int[] nums2 = {2, 3, 0, 1, 4};
        int result2 = solution.jump(nums2);
        System.out.println("Test 2 - Input: [2,3,0,1,4]");
        System.out.println("Expected: 2, Got: " + result2);
        System.out.println("Pass: " + (result2 == 2));
        System.out.println();
        
        // Additional test cases
        int[] nums3 = {1};
        int result3 = solution.jump(nums3);
        System.out.println("Test 3 - Input: [1]");
        System.out.println("Expected: 0, Got: " + result3);
        System.out.println("Pass: " + (result3 == 0));
        System.out.println();
        
        int[] nums4 = {1, 2, 3};
        int result4 = solution.jump(nums4);
        System.out.println("Test 4 - Input: [1,2,3]");
        System.out.println("Expected: 2, Got: " + result4);
        System.out.println("Pass: " + (result4 == 2));
        System.out.println();
        
        int[] nums5 = {2, 1};
        int result5 = solution.jump(nums5);
        System.out.println("Test 5 - Input: [2,1]");
        System.out.println("Expected: 1, Got: " + result5);
        System.out.println("Pass: " + (result5 == 1));
        System.out.println();
        
        // Performance comparison
        System.out.println("=== Performance Comparison ===");
        int[] largeBench = new int[1000];
        for (int i = 0; i < largeBench.length; i++) {
            largeBench[i] = Math.min(3, largeBench.length - i);
        }
        
        long start = System.nanoTime();
        int resultGreedy = solution.jump(largeBench);
        long greedyTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        int resultDP = solution.jumpDP(largeBench);
        long dpTime = System.nanoTime() - start;
        
        System.out.println("Large array test (1000 elements):");
        System.out.println("Greedy result: " + resultGreedy + " (Time: " + greedyTime/1000 + " μs)");
        System.out.println("DP result: " + resultDP + " (Time: " + dpTime/1000 + " μs)");
        System.out.println("Results match: " + (resultGreedy == resultDP));
    }
}