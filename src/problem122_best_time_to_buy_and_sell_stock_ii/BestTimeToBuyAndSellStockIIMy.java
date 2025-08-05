package problem122_best_time_to_buy_and_sell_stock_ii;

public class BestTimeToBuyAndSellStockIIMy {
    
    /**
     * Problem: Best Time to Buy and Sell Stock II
     * 
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
     * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share 
     * of the stock at any time. However, you can buy it then immediately sell it on the same day.
     * Find and return the maximum profit you can achieve.
     * 
     * TODO: Implement your solution here
     * 
     * Hint: Think about when you would want to buy and sell to maximize profit.
     * Since you can buy and sell on the same day, consider every profitable opportunity.
     * 
     * @param prices array of stock prices
     * @return maximum profit achievable
     */
    public int maxProfit(int[] prices) {
        // TODO: Implement your solution
        // Your implementation goes here
        return 0;
    }
    
    // Test cases
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIIMy solution = new BestTimeToBuyAndSellStockIIMy();
        
        // Test case 1: [7,1,5,3,6,4] -> Expected: 7
        // Buy on day 2 (price=1), sell on day 3 (price=5), profit = 4
        // Buy on day 4 (price=3), sell on day 5 (price=6), profit = 3
        // Total profit = 7
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int result1 = solution.maxProfit(prices1);
        System.out.println("Test 1: " + java.util.Arrays.toString(prices1) + " -> " + result1 + " (Expected: 7)");
        
        // Test case 2: [1,2,3,4,5] -> Expected: 4
        // Buy on day 1 (price=1), sell on day 5 (price=5), profit = 4
        int[] prices2 = {1, 2, 3, 4, 5};
        int result2 = solution.maxProfit(prices2);
        System.out.println("Test 2: " + java.util.Arrays.toString(prices2) + " -> " + result2 + " (Expected: 4)");
        
        // Test case 3: [7,6,4,3,1] -> Expected: 0
        // Prices are decreasing, no profit possible
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
    }
}