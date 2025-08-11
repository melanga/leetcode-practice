package problem274_h_index;

import java.util.Arrays;

/**
 * 274. H-Index - Optimal Solution
 * 
 * Two approaches implemented:
 * 1. Sorting approach - O(n log n) time, O(1) space
 * 2. Counting approach - O(n) time, O(n) space
 */
public class HIndex {
    
    /**
     * Approach 1: Sorting approach (optimized - no array reversal needed)
     * Time: O(n log n), Space: O(1)
     * 
     * Key insight: After sorting in ascending order, for each position i,
     * we have (n - i) papers with at least citations[i] citations.
     * The h-index is the maximum value where h papers have at least h citations.
     */
    public int hIndex(int[] citations) {
        // Sort in ascending order (natural sort)
        Arrays.sort(citations);
        int n = citations.length;
        
        int hIndex = 0;
        for (int i = 0; i < n; i++) {
            // At position i, we have (n - i) papers with at least citations[i] citations
            int papersWithAtLeastThisCitations = n - i;
            int currentCitations = citations[i];
            
            // h-index candidate is the minimum of papers count and citations
            int candidate = Math.min(papersWithAtLeastThisCitations, currentCitations);
            hIndex = Math.max(hIndex, candidate);
        }
        
        return hIndex;
    }
    
    /**
     * Approach 2: Counting approach (more optimal)
     * Time: O(n), Space: O(n)
     * 
     * Count how many papers have exactly k citations for k = 0 to n.
     * Papers with more than n citations are counted in bucket[n].
     */
    public int hIndexCounting(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        
        // Count papers by citation count
        for (int citation : citations) {
            if (citation >= n) {
                buckets[n]++;
            } else {
                buckets[citation]++;
            }
        }
        
        // Count papers with at least k citations (from right to left)
        int papersWithAtLeastKCitations = 0;
        for (int k = n; k >= 0; k--) {
            papersWithAtLeastKCitations += buckets[k];
            
            // If we have at least k papers with at least k citations, k is a valid h-index
            if (papersWithAtLeastKCitations >= k) {
                return k;
            }
        }
        
        return 0;
    }
    
    // Test both approaches
    public static void main(String[] args) {
        HIndex solution = new HIndex();
        
        // Test case 1
        int[] citations1 = {3, 0, 6, 1, 5};
        System.out.println("Test case 1:");
        System.out.println("Input: [3,0,6,1,5]");
        System.out.println("Sorting approach: " + solution.hIndex(citations1.clone()));
        System.out.println("Counting approach: " + solution.hIndexCounting(citations1.clone()));
        System.out.println("Expected: 3");
        System.out.println();
        
        // Test case 2
        int[] citations2 = {1, 3, 1};
        System.out.println("Test case 2:");
        System.out.println("Input: [1,3,1]");
        System.out.println("Sorting approach: " + solution.hIndex(citations2.clone()));
        System.out.println("Counting approach: " + solution.hIndexCounting(citations2.clone()));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test case 3 - Edge case: single paper
        int[] citations3 = {100};
        System.out.println("Test case 3:");
        System.out.println("Input: [100]");
        System.out.println("Sorting approach: " + solution.hIndex(citations3.clone()));
        System.out.println("Counting approach: " + solution.hIndexCounting(citations3.clone()));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test case 4 - Edge case: no citations
        int[] citations4 = {0, 0, 0, 0};
        System.out.println("Test case 4:");
        System.out.println("Input: [0,0,0,0]");
        System.out.println("Sorting approach: " + solution.hIndex(citations4.clone()));
        System.out.println("Counting approach: " + solution.hIndexCounting(citations4.clone()));
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test case 5 - All papers highly cited
        int[] citations5 = {10, 10, 10, 10, 10};
        System.out.println("Test case 5:");
        System.out.println("Input: [10,10,10,10,10]");
        System.out.println("Sorting approach: " + solution.hIndex(citations5.clone()));
        System.out.println("Counting approach: " + solution.hIndexCounting(citations5.clone()));
        System.out.println("Expected: 5");
    }
}
