package problem122_best_time_to_buy_and_sell_stock_ii;

public class BestTimeToBuyAndSellStockII {
    
    /**
     * Optimal Solution: Greedy Approach
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Key Insight: Since we can buy and sell on the same day, we want to capture
     * every profitable transaction. This means buying before every price increase
     * and selling before every price decrease.
     * 
     * Algorithm: Sum all positive differences between consecutive days.
     * If prices[i+1] > prices[i], we buy at prices[i] and sell at prices[i+1].
     * 
     * @param prices array of stock prices
     * @return maximum profit achievable
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int totalProfit = 0;
        
        // Add profit from every upward price movement
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        
        return totalProfit;
    }
    
    /**
     * Alternative Solution 1: Peak Valley Approach
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Find all valleys (local minima) and peaks (local maxima).
     * Buy at each valley and sell at the next peak.
     */
    public int maxProfitPeakValley(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int totalProfit = 0;
        int i = 0;
        
        while (i < prices.length - 1) {
            // Find valley (local minimum)
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }
            
            if (i == prices.length - 1) break;
            
            int valley = prices[i++]; // Buy at valley
            
            // Find peak (local maximum)
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            
            int peak = prices[i++]; // Sell at peak
            
            totalProfit += peak - valley;
        }
        
        return totalProfit;
    }
    
    /**
     * Alternative Solution 2: Dynamic Programming (State Machine)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Two states: hold (have stock) and sold (no stock).
     * Track maximum profit for each state at each day.
     */
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int hold = -prices[0]; // Max profit when holding stock
        int sold = 0;          // Max profit when not holding stock
        
        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;
            int prevSold = sold;
            
            // Either keep holding or buy today
            hold = Math.max(prevHold, prevSold - prices[i]);
            
            // Either keep not holding or sell today
            sold = Math.max(prevSold, prevHold + prices[i]);
        }
        
        return sold; // We want to end with no stock
    }
    
    /**
     * Alternative Solution 3: Two-Pass Approach (Less Efficient)
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * 
     * For educational purposes - shows brute force approach.
     * Not recommended for large inputs.
     */
    public int maxProfitBruteForce(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        return maxProfitRecursive(prices, 0, false);
    }
    
    private int maxProfitRecursive(int[] prices, int day, boolean holding) {
        if (day >= prices.length) {
            return 0;
        }
        
        int doNothing = maxProfitRecursive(prices, day + 1, holding);
        
        int doSomething = 0;
        if (holding) {
            // Sell stock
            doSomething = prices[day] + maxProfitRecursive(prices, day + 1, false);
        } else {
            // Buy stock
            doSomething = -prices[day] + maxProfitRecursive(prices, day + 1, true);
        }
        
        return Math.max(doNothing, doSomething);
    }
    
    // Test cases
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockII solution = new BestTimeToBuyAndSellStockII();
        
        // Test case 1: [7,1,5,3,6,4] -> Expected: 7
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int result1 = solution.maxProfit(prices1);
        System.out.println("Test 1: " + java.util.Arrays.toString(prices1) + " -> " + result1 + " (Expected: 7)");
        
        // Test case 2: [1,2,3,4,5] -> Expected: 4
        int[] prices2 = {1, 2, 3, 4, 5};
        int result2 = solution.maxProfit(prices2);
        System.out.println("Test 2: " + java.util.Arrays.toString(prices2) + " -> " + result2 + " (Expected: 4)");
        
        // Test case 3: [7,6,4,3,1] -> Expected: 0
        int[] prices3 = {7, 6, 4, 3, 1};
        int result3 = solution.maxProfit(prices3);
        System.out.println("Test 3: " + java.util.Arrays.toString(prices3) + " -> " + result3 + " (Expected: 0)");
        
        // Test case 4: [1,2] -> Expected: 1
        int[] prices4 = {1, 2};
        int result4 = solution.maxProfit(prices4);
        System.out.println("Test 4: " + java.util.Arrays.toString(prices4) + " -> " + result4 + " (Expected: 1)");
        
        // Test case 5: [2,1,2,0,1] -> Expected: 2
        int[] prices5 = {2, 1, 2, 0, 1};
        int result5 = solution.maxProfit(prices5);
        System.out.println("Test 5: " + java.util.Arrays.toString(prices5) + " -> " + result5 + " (Expected: 2)");
        
        // Testing alternative solutions
        System.out.println("\n--- Testing Alternative Solutions ---");
        System.out.println("Peak-Valley approach: " + solution.maxProfitPeakValley(prices1));
        System.out.println("Dynamic Programming: " + solution.maxProfitDP(prices1));
        System.out.println("Brute Force (small input): " + solution.maxProfitBruteForce(new int[]{1, 2, 3}));
        
        // Performance comparison
        System.out.println("\n--- Performance Analysis ---");
        int[] largePrices = {7, 1, 5, 3, 6, 4, 2, 8, 1, 9};
        
        long start = System.nanoTime();
        int result = solution.maxProfit(largePrices);
        long greedyTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        result = solution.maxProfitPeakValley(largePrices);
        long peakValleyTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        result = solution.maxProfitDP(largePrices);
        long dpTime = System.nanoTime() - start;
        
        System.out.println("Greedy: " + greedyTime + " ns");
        System.out.println("Peak-Valley: " + peakValleyTime + " ns");
        System.out.println("DP: " + dpTime + " ns");
        System.out.println("Result: " + result);
    }
}