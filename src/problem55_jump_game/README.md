# 55. Jump Game

## Problem Description

You are given an integer array `nums`. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

**Difficulty:** Medium

## Examples

### Example 1:
```
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

### Example 2:
```
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
```

## Constraints
- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 10^5`

## Algorithm Explanation

### Optimal Solution: Greedy Approach

The key insight is that we only need to track the **furthest position we can reach** at any given time. We don't need to explore all possible paths - we just need to know if it's possible to reach the end.

#### Algorithm Steps:

1. **Initialize** `maxReach = 0` to track the furthest position reachable
2. **Iterate** through each position `i` in the array:
   - **Check reachability**: If `i > maxReach`, we cannot reach position `i`, so return `false`
   - **Update max reach**: `maxReach = max(maxReach, i + nums[i])`
   - **Early termination**: If `maxReach >= nums.length - 1`, we can reach the end
3. **Return** `maxReach >= nums.length - 1`

#### Why This Works:

- **Greedy choice**: At each position, we update our maximum reachable distance
- **Optimal substructure**: If we can reach position `i`, then we can reach any position between our previous max reach and `i + nums[i]`
- **No backtracking needed**: Once we know the maximum distance we can reach, we don't need to consider specific paths

#### Example Walkthrough:

For `nums = [2,3,1,1,4]`:

| i | nums[i] | maxReach before | i + nums[i] | maxReach after | Can reach end? |
|---|---------|-----------------|-------------|----------------|----------------|
| 0 | 2       | 0               | 2           | 2              | No (need ≥4)   |
| 1 | 3       | 2               | 4           | 4              | Yes! ✓         |

Result: `true`

For `nums = [3,2,1,0,4]`:

| i | nums[i] | maxReach before | i + nums[i] | maxReach after | Notes |
|---|---------|-----------------|-------------|----------------|-------|
| 0 | 3       | 0               | 3           | 3              |       |
| 1 | 2       | 3               | 3           | 3              |       |
| 2 | 1       | 3               | 3           | 3              |       |
| 3 | 0       | 3               | 3           | 3              | Stuck at 3! |
| 4 | 4       | 3               | -           | -              | i > maxReach, unreachable |

Result: `false`

### Alternative Approach: Backwards Greedy

We can also solve this by working backwards:

1. Start with `lastGoodIndex = nums.length - 1` (the target)
2. For each position `i` from right to left:
   - If `i + nums[i] >= lastGoodIndex`, then position `i` can reach the target
   - Update `lastGoodIndex = i`
3. Return `lastGoodIndex == 0`

This approach asks: "What's the leftmost position that can reach the end?"

### Complexity Analysis

- **Time Complexity:** O(n) - Single pass through the array
- **Space Complexity:** O(1) - Only using constant extra space

### Alternative Approaches

1. **Dynamic Programming:** O(n²) time, O(n) space - Check reachability for each position
2. **Recursive with Memoization:** O(n²) time, O(n) space - DFS with memo
3. **BFS:** O(n²) time, O(n) space - Explore all reachable positions level by level

The greedy approach is optimal because:
- We only care about reachability, not the specific path
- Maximizing reach at each step gives us the best chance of success
- No need to explore all possibilities

### Key Insights

1. **Reachability is cumulative** - if we can reach position `i`, we can reach all positions from 0 to `i`
2. **Maximum reach is monotonic** - it never decreases as we move forward
3. **Early termination** - once `maxReach >= n-1`, we know we can reach the end
4. **Greedy is optimal** - there's no benefit to jumping shorter distances when we can jump farther

This problem is a classic example where the greedy approach provides an elegant and optimal solution!