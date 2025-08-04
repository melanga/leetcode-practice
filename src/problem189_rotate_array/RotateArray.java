package problem189_rotate_array;

/**
 * 189. Rotate Array - Optimal Solution
 * 
 * This solution uses the "Reverse" approach which achieves:
 * - Time Complexity: O(n)
 * - Space Complexity: O(1) - meets the follow-up requirement
 * 
 * Algorithm:
 * 1. Reverse the entire array
 * 2. Reverse the first k elements
 * 3. Reverse the remaining n-k elements
 */
public class RotateArray {
    
    /**
     * Rotates the array to the right by k steps using the reverse approach.
     * This is the most space-efficient solution with O(1) extra space.
     * 
     * @param nums the input array to rotate
     * @param k the number of steps to rotate right
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int n = nums.length;
        k = k % n; // Handle cases where k > n
        
        if (k == 0) {
            return; // No rotation needed
        }
        
        // Step 1: Reverse the entire array
        reverse(nums, 0, n - 1);
        
        // Step 2: Reverse the first k elements
        reverse(nums, 0, k - 1);
        
        // Step 3: Reverse the remaining n-k elements
        reverse(nums, k, n - 1);
    }
    
    /**
     * Helper method to reverse array elements between start and end indices (inclusive).
     * 
     * @param nums the array to reverse
     * @param start starting index (inclusive)
     * @param end ending index (inclusive)
     */
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
     * Alternative solution using extra space (less optimal but easier to understand)
     * Time: O(n), Space: O(n)
     */
    public void rotateWithExtraSpace(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int n = nums.length;
        k = k % n;
        
        if (k == 0) {
            return;
        }
        
        int[] temp = new int[n];
        
        // Copy elements to their new positions
        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }
        
        // Copy back to original array
        System.arraycopy(temp, 0, nums, 0, n);
    }
    
    /**
     * Alternative solution using cyclic replacement
     * Time: O(n), Space: O(1)
     */
    public void rotateCyclic(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int n = nums.length;
        k = k % n;
        
        if (k == 0) {
            return;
        }
        
        int count = 0;
        
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];
            
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
    
    /**
     * Test all three approaches
     */
    public static void main(String[] args) {
        RotateArray solution = new RotateArray();
        
        System.out.println("=== Testing Reverse Approach (Optimal) ===");
        testApproach(solution::rotate);
        
        System.out.println("\n=== Testing Extra Space Approach ===");
        testApproach(solution::rotateWithExtraSpace);
        
        System.out.println("\n=== Testing Cyclic Replacement Approach ===");
        testApproach(solution::rotateCyclic);
    }
    
    private static void testApproach(java.util.function.BiConsumer<int[], Integer> rotateMethod) {
        // Test case 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        System.out.println("Before: " + java.util.Arrays.toString(nums1));
        rotateMethod.accept(nums1, k1);
        System.out.println("After rotating by " + k1 + ": " + java.util.Arrays.toString(nums1));
        System.out.println("Expected: [5, 6, 7, 1, 2, 3, 4]");
        System.out.println();
        
        // Test case 2
        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;
        System.out.println("Before: " + java.util.Arrays.toString(nums2));
        rotateMethod.accept(nums2, k2);
        System.out.println("After rotating by " + k2 + ": " + java.util.Arrays.toString(nums2));
        System.out.println("Expected: [3, 99, -1, -100]");
        System.out.println();
        
        // Test case 3: k > array length
        int[] nums3 = {1, 2};
        int k3 = 3;
        System.out.println("Before: " + java.util.Arrays.toString(nums3));
        rotateMethod.accept(nums3, k3);
        System.out.println("After rotating by " + k3 + ": " + java.util.Arrays.toString(nums3));
        System.out.println("Expected: [2, 1]");
        System.out.println();
    }
}