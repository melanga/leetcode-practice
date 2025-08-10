package problem45_jump_game_ii;
/**
 * Problem 45: Jump Game II
 * 
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * Return the minimum number of jumps to reach index n - 1.
 * 
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. 
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */
public class JumpGameIIMy {
    
    /**
     * @param nums array of integers representing maximum jump lengths
     * @return minimum number of jumps to reach the last index
     */
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
    
    /**
     * Test the solution with provided examples
     */
    public static void main(String[] args) {
        JumpGameIIMy solution = new JumpGameIIMy();
        
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
    }
}