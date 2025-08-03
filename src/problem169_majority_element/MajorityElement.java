package problem169_majority_element;

public class MajorityElement {
    
    /**
     * Optimal Solution: Boyer-Moore Voting Algorithm
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * The Boyer-Moore voting algorithm works on the principle that if we cancel out
     * each occurrence of an element e with all the other elements that are different
     * from e, then e will still exist if it is a majority element.
     * 
     * @param nums array of integers
     * @return the majority element
     */
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        
        // Phase 1: Find potential candidate
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // Phase 2: Verify candidate (optional since problem guarantees majority exists)
        // count = 0;
        // for (int num : nums) {
        //     if (num == candidate) count++;
        // }
        // return count > nums.length / 2 ? candidate : -1;
        
        return candidate;
    }
    
    /**
     * Alternative Solution 1: Using HashMap
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int majorityElementHashMap(int[] nums) {
        java.util.Map<Integer, Integer> counts = new java.util.HashMap<>();
        int majorityCount = nums.length / 2;
        
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
            if (counts.get(num) > majorityCount) {
                return num;
            }
        }
        
        return -1; // Should never reach here given problem constraints
    }
    
    /**
     * Alternative Solution 2: Using Sorting
     * Time Complexity: O(n log n)
     * Space Complexity: O(1) or O(log n) depending on sorting algorithm
     */
    public int majorityElementSorting(int[] nums) {
        java.util.Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    
    // Test cases
    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();
        
        // Test case 1: [3,2,3] -> Expected: 3
        int[] nums1 = {3, 2, 3};
        int result1 = solution.majorityElement(nums1);
        System.out.println("Test 1: " + java.util.Arrays.toString(nums1) + " -> " + result1 + " (Expected: 3)");
        
        // Test case 2: [2,2,1,1,1,2,2] -> Expected: 2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        int result2 = solution.majorityElement(nums2);
        System.out.println("Test 2: " + java.util.Arrays.toString(nums2) + " -> " + result2 + " (Expected: 2)");
        
        // Test case 3: [1] -> Expected: 1
        int[] nums3 = {1};
        int result3 = solution.majorityElement(nums3);
        System.out.println("Test 3: " + java.util.Arrays.toString(nums3) + " -> " + result3 + " (Expected: 1)");
        
        // Test case 4: [1,1,1,2,2] -> Expected: 1
        int[] nums4 = {1, 1, 1, 2, 2};
        int result4 = solution.majorityElement(nums4);
        System.out.println("Test 4: " + java.util.Arrays.toString(nums4) + " -> " + result4 + " (Expected: 1)");
        
        // Testing alternative solutions
        System.out.println("\n--- Testing Alternative Solutions ---");
        System.out.println("HashMap solution: " + solution.majorityElementHashMap(nums2));
        System.out.println("Sorting solution: " + solution.majorityElementSorting(nums2));
    }
} 