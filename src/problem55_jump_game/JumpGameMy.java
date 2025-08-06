package problem55_jump_game;

/**
 * LeetCode 55. Jump Game
 * 
 * You are given an integer array nums. You are initially positioned at the array's first index, 
 * and each element in the array represents your maximum jump length at that position.
 * 
 * Return true if you can reach the last index, or false otherwise.
 * 
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, 
 * which makes it impossible to reach the last index.
 * 
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^5
 */
public class JumpGameMy {
    
    /**
     * 
     * Approach ideas:
     * 1. Greedy approach: Keep track of the furthest position you can reach
     * 2. Dynamic Programming: For each position, check if it's reachable
     * 3. Backtracking: Try all possible jumps (less efficient)
     * 
     * Think about:
     * - What does it mean to "reach" the last index?
     * - How can you track the maximum reachable position?
     * - When should you return false early?
     */
    public boolean canJump(int[] nums) {
        int maxReachFromStart = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > maxReachFromStart) {
                return false;
            }
            maxReachFromStart = Math.max(maxReachFromStart, i + nums[i]);
            if (maxReachFromStart >= nums.length -1){
                return true;
            }
        }
        return maxReachFromStart >= nums.length - 1;
    }
    
    /**
     * Test cases to verify your solution
     */
    public static void main(String[] args) {
        JumpGameMy solution = new JumpGameMy();
        
        // Test case 1
        int[] nums1 = {2, 3, 1, 1, 4};
        boolean result1 = solution.canJump(nums1);
        System.out.println("Test 1: " + java.util.Arrays.toString(nums1));
        System.out.println("Expected: true, Got: " + result1);
        System.out.println("PASS: " + (result1 == true));
        System.out.println();
        
        // Test case 2
        int[] nums2 = {3, 2, 1, 0, 4};
        boolean result2 = solution.canJump(nums2);
        System.out.println("Test 2: " + java.util.Arrays.toString(nums2));
        System.out.println("Expected: false, Got: " + result2);
        System.out.println("PASS: " + (result2 == false));
        System.out.println();
        
        // Test case 3: Single element
        int[] nums3 = {0};
        boolean result3 = solution.canJump(nums3);
        System.out.println("Test 3: " + java.util.Arrays.toString(nums3));
        System.out.println("Expected: true, Got: " + result3);
        System.out.println("PASS: " + (result3 == true));
        System.out.println();
        
        // Test case 4: All zeros except first
        int[] nums4 = {2, 0, 0};
        boolean result4 = solution.canJump(nums4);
        System.out.println("Test 4: " + java.util.Arrays.toString(nums4));
        System.out.println("Expected: true, Got: " + result4);
        System.out.println("PASS: " + (result4 == true));
        System.out.println();
        
        // Test case 5: Cannot jump
        int[] nums5 = {1, 0, 1, 0};
        boolean result5 = solution.canJump(nums5);
        System.out.println("Test 5: " + java.util.Arrays.toString(nums5));
        System.out.println("Expected: false, Got: " + result5);
        System.out.println("PASS: " + (result5 == false));
    }
}