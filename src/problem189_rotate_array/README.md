# 189. Rotate Array

## Problem Description

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

**Examples:**
- Input: `nums = [1,2,3,4,5,6,7]`, `k = 3` → Output: `[5,6,7,1,2,3,4]`
- Input: `nums = [-1,-100,3,99]`, `k = 2` → Output: `[3,99,-1,-100]`

**Constraints:**
- `1 <= nums.length <= 10^5`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `0 <= k <= 10^5`

## Solutions

This problem has multiple approaches with different trade-offs between time and space complexity.

### Solution 1: Reverse Approach (Optimal) ⭐

**Algorithm:**
1. Reverse the entire array
2. Reverse the first `k` elements
3. Reverse the remaining `n-k` elements

**Example:** `nums = [1,2,3,4,5,6,7]`, `k = 3`
```
Original:     [1, 2, 3, 4, 5, 6, 7]
Step 1:       [7, 6, 5, 4, 3, 2, 1]  (reverse entire array)
Step 2:       [5, 6, 7, 4, 3, 2, 1]  (reverse first k=3 elements)
Step 3:       [5, 6, 7, 1, 2, 3, 4]  (reverse remaining elements)
```

**Why it works:**
- After reversing the entire array, the last `k` elements (which should be at the front) are at the beginning but in reverse order
- Reversing the first `k` elements puts them in the correct order
- Reversing the remaining elements puts the original first `n-k` elements in correct order

**Complexity:**
- Time: O(n) - We traverse the array at most 3 times
- Space: O(1) - Only using a constant amount of extra space

**Code:**
```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // Handle k > n
    
    if (k == 0) return;
    
    reverse(nums, 0, n - 1);     // Reverse entire array
    reverse(nums, 0, k - 1);     // Reverse first k elements
    reverse(nums, k, n - 1);     // Reverse remaining elements
}

private void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
```

### Solution 2: Extra Space Approach

**Algorithm:**
1. Create a new array of the same size
2. Place each element at its new position: `new_position = (i + k) % n`
3. Copy the new array back to the original

**Complexity:**
- Time: O(n)
- Space: O(n) - Requires additional array

**Code:**
```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    int[] temp = new int[n];
    
    for (int i = 0; i < n; i++) {
        temp[(i + k) % n] = nums[i];
    }
    
    System.arraycopy(temp, 0, nums, 0, n);
}
```

### Solution 3: Cyclic Replacement

**Algorithm:**
1. Start from index 0 and place each element at its final position
2. Follow the cycle until we return to the starting position
3. If we haven't moved all elements, start a new cycle from the next unmoved element

**Example:** `nums = [1,2,3,4,5,6]`, `k = 2`
```
Cycle 1: 0 → 2 → 4 → 0 (moves elements at positions 0, 2, 4)
Cycle 2: 1 → 3 → 5 → 1 (moves elements at positions 1, 3, 5)
```

**Complexity:**
- Time: O(n) - Each element is moved exactly once
- Space: O(1) - Only using a few variables

**Code:**
```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
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
```

## Key Insights

1. **Modulo Operation**: Always use `k = k % n` to handle cases where `k > n`
2. **Edge Cases**: Handle arrays with length 1 or when `k = 0`
3. **In-place Requirement**: The reverse approach is the most elegant solution that meets the O(1) space requirement
4. **Pattern Recognition**: The reverse approach works because rotation is essentially moving a suffix to become a prefix

## Time and Space Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|----------------|------------------|-------|
| Reverse | O(n) | O(1) | ⭐ Optimal for both time and space |
| Extra Space | O(n) | O(n) | Easiest to understand |
| Cyclic | O(n) | O(1) | Complex but educational |

## Practice Tips

1. Start with the extra space approach if you're new to this problem
2. The reverse approach is elegant once you understand the pattern
3. Pay attention to the modulo operation - it's crucial for correctness
4. Test with edge cases: `k = 0`, `k > n`, single element arrays