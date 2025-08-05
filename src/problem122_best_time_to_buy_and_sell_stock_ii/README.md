# 122. Best Time to Buy and Sell Stock II

## 📋 Problem Description

You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `ith` day.

On each day, you may decide to buy and/or sell the stock. You can only hold **at most one share** of the stock at any time. However, you can **buy it then immediately sell it on the same day**.

Find and return the **maximum profit** you can achieve.

### Examples

**Example 1:**
```
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: 
- Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4
- Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3
- Total profit is 4 + 3 = 7
```

**Example 2:**
```
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: 
- Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4
- Total profit is 4
```

**Example 3:**
```
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock
```

### Constraints
- `1 <= prices.length <= 3 * 10^4`
- `0 <= prices[i] <= 10^4`

## 🎯 Key Insights

### 🔑 Critical Observation
Since we can **buy and sell on the same day**, we can capture **every profitable opportunity**. This means:
- If tomorrow's price > today's price → Buy today, sell tomorrow
- If tomorrow's price ≤ today's price → Do nothing

### 💡 Greedy Strategy
The optimal strategy is to **sum all positive price differences** between consecutive days.

```
prices = [7, 1, 5, 3, 6, 4]
differences = [-6, +4, -2, +3, -2]
profitable = [+4, +3] = 7
```

## 🚀 Solution Approaches

### 🥇 Approach 1: Greedy Algorithm (Optimal)

**Algorithm:**
1. Iterate through the array from day 1 to day n-1
2. If `prices[i+1] > prices[i]`, add the difference to total profit
3. Return total profit

**Why it works:**
- We're essentially buying before every price increase and selling before every price decrease
- Since we can trade on the same day, we don't miss any profitable opportunities

```java
public int maxProfit(int[] prices) {
    int totalProfit = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] > prices[i - 1]) {
            totalProfit += prices[i] - prices[i - 1];
        }
    }
    return totalProfit;
}
```

**Complexity:**
- ⏰ **Time:** O(n) - Single pass through array
- 💾 **Space:** O(1) - Only using constant extra space

### 🥈 Approach 2: Peak-Valley Method

**Algorithm:**
1. Find all local valleys (buy points) and peaks (sell points)
2. For each valley-peak pair, add (peak - valley) to profit
3. Return total profit

```java
public int maxProfitPeakValley(int[] prices) {
    int totalProfit = 0;
    int i = 0;
    
    while (i < prices.length - 1) {
        // Find valley
        while (i < prices.length - 1 && prices[i + 1] <= prices[i]) i++;
        if (i == prices.length - 1) break;
        int valley = prices[i++];
        
        // Find peak
        while (i < prices.length - 1 && prices[i + 1] >= prices[i]) i++;
        int peak = prices[i++];
        
        totalProfit += peak - valley;
    }
    return totalProfit;
}
```

**Complexity:**
- ⏰ **Time:** O(n) - Single pass through array
- 💾 **Space:** O(1) - Only using constant extra space

### 🥉 Approach 3: Dynamic Programming (State Machine)

**Algorithm:**
Track two states at each day:
- `hold`: Maximum profit when holding stock
- `sold`: Maximum profit when not holding stock

```java
public int maxProfitDP(int[] prices) {
    int hold = -prices[0]; // Start by buying first stock
    int sold = 0;          // Start with no profit
    
    for (int i = 1; i < prices.length; i++) {
        int prevHold = hold;
        int prevSold = sold;
        
        hold = Math.max(prevHold, prevSold - prices[i]); // Keep holding or buy
        sold = Math.max(prevSold, prevHold + prices[i]); // Keep sold or sell
    }
    
    return sold; // End with no stock
}
```

**Complexity:**
- ⏰ **Time:** O(n) - Single pass through array
- 💾 **Space:** O(1) - Only using constant extra space

## 📊 Complexity Comparison

| Approach | Time Complexity | Space Complexity | Readability | Performance |
|----------|----------------|------------------|-------------|-------------|
| Greedy | O(n) | O(1) | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| Peak-Valley | O(n) | O(1) | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| Dynamic Programming | O(n) | O(1) | ⭐⭐⭐ | ⭐⭐⭐ |

## 🔍 Visual Example

```
prices = [7, 1, 5, 3, 6, 4]
```

```
Price Chart:
7 |*
6 |        * 
5 |    *     
4 |          *
3 |      *   
2 |
1 |  *
0 +--+--+--+--+--+--
  0  1  2  3  4  5  Days

Transactions:
Day 1→2: Buy at 1, Sell at 5  → Profit: 4
Day 3→4: Buy at 3, Sell at 6  → Profit: 3
Total Profit: 7
```

## 🎭 Edge Cases

| Case | Input | Output | Explanation |
|------|-------|--------|-------------|
| Single day | `[5]` | `0` | Cannot make any transactions |
| Increasing prices | `[1,2,3,4,5]` | `4` | Buy first day, sell last day |
| Decreasing prices | `[5,4,3,2,1]` | `0` | No profitable transactions |
| Flat prices | `[3,3,3,3]` | `0` | No price differences |
| Two days | `[1,2]` | `1` | Simple buy-sell |

## 🧠 Interview Tips

### 🤔 Questions to Ask
1. "Can I buy and sell on the same day?" (Answer: Yes)
2. "What if prices array is empty?" (Usually guaranteed non-empty)
3. "Can prices be negative?" (Usually non-negative)

### 💭 Thought Process
1. **Recognize the pattern:** This is about capturing all profitable opportunities
2. **Key insight:** Since same-day trading is allowed, we can be greedy
3. **Optimization:** Instead of tracking actual transactions, just sum profitable price differences

### 🚨 Common Mistakes
- Overthinking the problem with complex state tracking
- Trying to find the absolute minimum and maximum (that's Stock I problem)
- Forgetting that same-day trading is allowed

## 🔗 Related Problems

- **121. Best Time to Buy and Sell Stock** (Single transaction)
- **123. Best Time to Buy and Sell Stock III** (At most 2 transactions)
- **188. Best Time to Buy and Sell Stock IV** (At most k transactions)
- **309. Best Time to Buy and Sell Stock with Cooldown** (With cooldown period)

## 📚 Algorithm Pattern

This problem demonstrates the **Greedy Algorithm** pattern:
- Make locally optimal choices (capture every profitable trade)
- Local optimality leads to global optimality
- Perfect for problems where you can "undo" decisions or make multiple choices

**Template for similar problems:**
```java
// For maximizing sum of positive differences
int result = 0;
for (int i = 1; i < array.length; i++) {
    if (array[i] > array[i-1]) {
        result += array[i] - array[i-1];
    }
}
return result;
```