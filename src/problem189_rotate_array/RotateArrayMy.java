package problem189_rotate_array;

/**
 * 189. Rotate Array
 * Medium
 * 
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * 
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 * 
 * Follow up:
 * - Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * - Could you do it in-place with O(1) extra space?
 */
public class RotateArrayMy {
    
    /**
     * Rotates the array to the right by k steps.
     * Modify the array in-place.
     * 
     * @param nums the input array to rotate
     * @param k the number of steps to rotate right
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Test cases to verify your implementation
     */
    public static void main(String[] args) {
        RotateArrayMy solution = new RotateArrayMy();
        
        // Test case 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        System.out.println("Before rotation: " + java.util.Arrays.toString(nums1));
        solution.rotate(nums1, k1);
        System.out.println("After rotation by " + k1 + ": " + java.util.Arrays.toString(nums1));
        System.out.println("Expected: [5, 6, 7, 1, 2, 3, 4]");
        System.out.println();
        
        // Test case 2
        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;
        System.out.println("Before rotation: " + java.util.Arrays.toString(nums2));
        solution.rotate(nums2, k2);
        System.out.println("After rotation by " + k2 + ": " + java.util.Arrays.toString(nums2));
        System.out.println("Expected: [3, 99, -1, -100]");
        System.out.println();
        
        // Test case 3: Edge case - k greater than array length
        int[] nums3 = {1, 2};
        int k3 = 3;
        System.out.println("Before rotation: " + java.util.Arrays.toString(nums3));
        solution.rotate(nums3, k3);
        System.out.println("After rotation by " + k3 + ": " + java.util.Arrays.toString(nums3));
        System.out.println("Expected: [2, 1]");
        System.out.println();
        
        // Test case 4: Edge case - single element
        int[] nums4 = {1};
        int k4 = 1;
        System.out.println("Before rotation: " + java.util.Arrays.toString(nums4));
        solution.rotate(nums4, k4);
        System.out.println("After rotation by " + k4 + ": " + java.util.Arrays.toString(nums4));
        System.out.println("Expected: [1]");
    }
}