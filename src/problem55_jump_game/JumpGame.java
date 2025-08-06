package problem55_jump_game;

/**
 * LeetCode 55. Jump Game - Optimal Solution
 * 
 * Greedy Approach: O(n) time, O(1) space
 * 
 * The key insight is that we only need to track the furthest position we can reach.
 * If at any point our current position exceeds the furthest reachable position,
 * we know we cannot proceed further.
 */
public class JumpGame {
    
    /**
     * Greedy approach to solve Jump Game
     * 
     * Time Complexity: O(n) - single pass through the array
     * Space Complexity: O(1) - only using constant extra space
     * 
     * Algorithm:
     * 1. Keep track of the furthest position we can reach (maxReach)
     * 2. For each position i, check if i <= maxReach (can we reach position i?)
     * 3. If yes, update maxReach to max(maxReach, i + nums[i])
     * 4. If maxReach >= nums.length - 1, we can reach the end
     * 5. If i > maxReach, we're stuck and cannot proceed
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        
        int maxReach = 0; // Furthest position we can reach
        
        for (int i = 0; i < nums.length; i++) {
            // If current position is beyond our reach, we cannot proceed
            if (i > maxReach) {
                return false;
            }
            
            // Update the furthest position we can reach from current position
            maxReach = Math.max(maxReach, i + nums[i]);
            
            // Early termination: if we can reach or exceed the last index
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        
        return maxReach >= nums.length - 1;
    }
    
    /**
     * Alternative implementation: Backwards greedy approach
     * Start from the end and work backwards to see if we can reach position 0
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean canJumpBackwards(int[] nums) {
        int lastGoodIndex = nums.length - 1;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastGoodIndex) {
                lastGoodIndex = i;
            }
        }
        
        return lastGoodIndex == 0;
    }
    
    /**
     * Test the solution with various test cases
     */
    public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        
        // Test case 1: Example 1 from problem
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Test 1: " + java.util.Arrays.toString(nums1));
        System.out.println("Result: " + solution.canJump(nums1)); // Expected: true
        System.out.println("Backwards: " + solution.canJumpBackwards(nums1));
        System.out.println();
        
        // Test case 2: Example 2 from problem
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Test 2: " + java.util.Arrays.toString(nums2));
        System.out.println("Result: " + solution.canJump(nums2)); // Expected: false
        System.out.println("Backwards: " + solution.canJumpBackwards(nums2));
        System.out.println();
        
        // Test case 3: Single element
        int[] nums3 = {0};
        System.out.println("Test 3: " + java.util.Arrays.toString(nums3));
        System.out.println("Result: " + solution.canJump(nums3)); // Expected: true
        System.out.println();
        
        // Test case 4: Can reach exactly
        int[] nums4 = {2, 0, 0};
        System.out.println("Test 4: " + java.util.Arrays.toString(nums4));
        System.out.println("Result: " + solution.canJump(nums4)); // Expected: true
        System.out.println();
        
        // Test case 5: Stuck in the middle
        int[] nums5 = {1, 0, 1, 0};
        System.out.println("Test 5: " + java.util.Arrays.toString(nums5));
        System.out.println("Result: " + solution.canJump(nums5)); // Expected: false
        System.out.println();
        
        // Test case 6: Large jumps
        int[] nums6 = {5, 4, 3, 2, 1, 0, 0};
        System.out.println("Test 6: " + java.util.Arrays.toString(nums6));
        System.out.println("Result: " + solution.canJump(nums6)); // Expected: true
    }
}