package problem169_majority_element;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MajorityElementMy {
    
    /**
     *
     * Problem: Find the majority element in an array
     * The majority element is the element that appears more than ⌊n / 2⌋ times
     * 
     * @param nums array of integers
     * @return the majority element
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int maxrepeats = map.values().stream().max(Integer::compareTo).get();
        AtomicInteger max = new AtomicInteger();
        map.forEach((k,v) -> {
            if (map.get(k) == maxrepeats){
                max.set(k);
            }
        });
        return max.get();
    }
    
    // Test cases
    public static void main(String[] args) {
        MajorityElementMy solution = new MajorityElementMy();
        
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
    }
} 