# Problem 45: Jump Game II

## Problem Description

You are given a 0-indexed array of integers `nums` of length `n`. You are initially positioned at index 0.

Each element `nums[i]` represents the maximum length of a forward jump from index `i`. In other words, if you are at index `i`, you can jump to any index `(i + j)` where:
- `0 <= j <= nums[i]`
- `i + j < n`

Return the **minimum number of jumps** to reach index `n - 1`. The test cases are generated such that you can reach index `n - 1`.

### Examples

**Example 1:**
```
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. 
Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

**Example 2:**
```
Input: nums = [2,3,0,1,4]
Output: 2
```

### Constraints
- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 1000`
- It's guaranteed that you can reach `nums[n - 1]`

## Algorithm Explanation

### Optimal Solution: Greedy BFS-like Approach

The key insight is to think of this problem as finding the minimum number of "levels" in a BFS traversal to reach the end. Each level represents all positions reachable with the same number of jumps.

#### Core Concept

1. **Level-based thinking**: Instead of exploring every possible path, we think in terms of "jump levels"
   - Level 0: Starting position (index 0)
   - Level 1: All positions reachable with 1 jump
   - Level 2: All positions reachable with 2 jumps
   - And so on...

2. **Greedy choice**: At each level, we want to reach the farthest possible position to maximize our options for the next level.

#### Algorithm Steps

```java
public int jump(int[] nums) {
    if (nums.length <= 1) return 0;
    
    int jumps = 0;          // Number of jumps taken
    int currentEnd = 0;     // Farthest index reachable with current number of jumps
    int farthest = 0;       // Farthest index reachable with one more jump
    
    for (int i = 0; i < nums.length - 1; i++) {
        // Update farthest position reachable from current position
        farthest = Math.max(farthest, i + nums[i]);
        
        // If we've processed all positions in current level
        if (i == currentEnd) {
            jumps++;                    // Move to next level
            currentEnd = farthest;      // Update boundary of next level
            
            // Early termination if we can reach the end
            if (currentEnd >= nums.length - 1) break;
        }
    }
    
    return jumps;
}
```

#### Detailed Walkthrough

**Example: nums = [2,3,1,1,4]**

```
Initial: jumps=0, currentEnd=0, farthest=0
Index 0: nums[0]=2, farthest = max(0, 0+2) = 2
         i==currentEnd(0), so jumps=1, currentEnd=2

Index 1: nums[1]=3, farthest = max(2, 1+3) = 4
         i≠currentEnd(2), continue

Index 2: nums[2]=1, farthest = max(4, 2+1) = 4
         i==currentEnd(2), so jumps=2, currentEnd=4
         currentEnd(4) >= nums.length-1(4), break

Result: 2 jumps
```

#### Why This Works

1. **Optimal substructure**: If we can reach position `i` with `k` jumps, and from `i` we can reach position `j`, then we can reach `j` with at most `k+1` jumps.

2. **Greedy choice property**: At each level, choosing the position that allows us to reach the farthest in the next jump is always optimal. This is because:
   - We're guaranteed to reach the target (given in problem)
   - Reaching farther gives us more options without increasing jump count
   - We never need to "backtrack" - if a shorter path existed, our greedy choice would have found it

3. **Level-wise processing**: By processing all positions reachable with `k` jumps before moving to `k+1` jumps, we ensure we find the minimum number of jumps.

### Complexity Analysis

- **Time Complexity**: O(n) - Single pass through the array
- **Space Complexity**: O(1) - Only using constant extra variables

### Alternative Approaches

#### 1. Dynamic Programming (Less Optimal)
```java
public int jumpDP(int[] nums) {
    int n = nums.length;
    if (n <= 1) return 0;
    
    int[] dp = new int[n];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j <= Math.min(i + nums[i], n - 1); j++) {
            dp[j] = Math.min(dp[j], dp[i] + 1);
        }
    }
    
    return dp[n - 1];
}
```
- **Time Complexity**: O(n²) in worst case
- **Space Complexity**: O(n)

#### 2. BFS (Conceptually similar but less efficient)
- Explicitly maintain a queue of positions at each level
- More intuitive but uses extra space and has higher constant factors

### Key Insights

1. **BFS vs Greedy**: The greedy approach effectively simulates BFS without explicitly maintaining levels/queues.

2. **Why greedy works**: In this problem, there's no benefit to taking a "worse" jump (one that reaches less far) when a better jump is available at the same level.

3. **Early termination**: As soon as we can reach the target, we can stop - no point in exploring further.

4. **Boundary tracking**: `currentEnd` acts as a "fence" - when we cross it, we know we've entered the next level and need to increment our jump count.

This algorithm is a beautiful example of how greedy approaches can solve seemingly complex problems optimally when the problem has the right properties.