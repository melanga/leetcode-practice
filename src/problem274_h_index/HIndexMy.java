package problem274_h_index;

/**
 * 274. H-Index
 * Medium
 * 
 * Given an array of integers citations where citations[i] is the number of citations 
 * a researcher received for their ith paper, return the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia: The h-index is defined as the 
 * maximum value of h such that the given researcher has published at least h papers 
 * that have each been cited at least h times.
 * 
 * Example 1:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * 
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 * 
 * Constraints:
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class HIndexMy {
    
    public int hIndex(int[] citations) {
        for (int i = 0, len = citations.length; i < len; i++) {

        }
        return 0;
    }
    
    // Test cases
    public static void main(String[] args) {
        HIndexMy solution = new HIndexMy();
        
        // Test case 1
        int[] citations1 = {3, 0, 6, 1, 5};
        int result1 = solution.hIndex(citations1);
        System.out.println("Test case 1:");
        System.out.println("Input: [3,0,6,1,5]");
        System.out.println("Expected: 3");
        System.out.println("Got: " + result1);
        System.out.println("Pass: " + (result1 == 3));
        System.out.println();
        
        // Test case 2
        int[] citations2 = {1, 3, 1};
        int result2 = solution.hIndex(citations2);
        System.out.println("Test case 2:");
        System.out.println("Input: [1,3,1]");
        System.out.println("Expected: 1");
        System.out.println("Got: " + result2);
        System.out.println("Pass: " + (result2 == 1));
        System.out.println();
        
        // Test case 3 - Edge case: single paper
        int[] citations3 = {100};
        int result3 = solution.hIndex(citations3);
        System.out.println("Test case 3:");
        System.out.println("Input: [100]");
        System.out.println("Expected: 1");
        System.out.println("Got: " + result3);
        System.out.println("Pass: " + (result3 == 1));
        System.out.println();
        
        // Test case 4 - Edge case: no citations
        int[] citations4 = {0, 0, 0, 0};
        int result4 = solution.hIndex(citations4);
        System.out.println("Test case 4:");
        System.out.println("Input: [0,0,0,0]");
        System.out.println("Expected: 0");
        System.out.println("Got: " + result4);
        System.out.println("Pass: " + (result4 == 0));
        System.out.println();
        
        // Test case 5 - All papers highly cited
        int[] citations5 = {10, 10, 10, 10, 10};
        int result5 = solution.hIndex(citations5);
        System.out.println("Test case 5:");
        System.out.println("Input: [10,10,10,10,10]");
        System.out.println("Expected: 5");
        System.out.println("Got: " + result5);
        System.out.println("Pass: " + (result5 == 5));
    }
}
