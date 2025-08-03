# 169. Majority Element

## Problem Description

Given an array `nums` of size `n`, return the majority element.

The majority element is the element that appears more than `⌊n / 2⌋` times. You may assume that the majority element always exists in the array.

**Follow-up:** Could you solve the problem in linear time and in O(1) space?

## Examples

```
Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
```

## Constraints

- `n == nums.length`
- `1 <= n <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`

## Solutions

### 1. Boyer-Moore Voting Algorithm (Optimal) ⭐

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

This is the optimal solution that satisfies the follow-up requirement.

#### Algorithm Explanation

The Boyer-Moore Voting Algorithm is based on the key insight that if we cancel out each occurrence of an element with all different elements, the majority element will still remain.

**How it works:**

1. **Candidate Selection Phase:**
   - Maintain a `candidate` and a `count`
   - For each element:
     - If `count` is 0, set current element as `candidate` and `count` to 1
     - If current element equals `candidate`, increment `count`
     - Otherwise, decrement `count`

2. **Verification Phase (Optional):**
   - Since the problem guarantees a majority element exists, we can skip this
   - Otherwise, count occurrences of the candidate to verify it's truly the majority

#### Why it works:

- The majority element appears more than n/2 times
- Even if we pair every majority element with a different element, we'll still have majority elements left over
- The algorithm essentially "cancels out" non-majority elements with majority elements
- The remaining candidate will be the majority element

#### Example Walkthrough:

For array `[2,2,1,1,1,2,2]`:

```
i=0: nums[0]=2, candidate=2, count=1
i=1: nums[1]=2, candidate=2, count=2 (same as candidate)
i=2: nums[2]=1, candidate=2, count=1 (different, decrement)
i=3: nums[3]=1, candidate=2, count=0 (different, decrement)
i=4: nums[4]=1, candidate=1, count=1 (count was 0, new candidate)
i=5: nums[5]=2, candidate=1, count=0 (different, decrement)
i=6: nums[6]=2, candidate=2, count=1 (count was 0, new candidate)

Result: candidate=2 (which is correct!)
```

### 2. HashMap Approach

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

#### Algorithm:
1. Count frequency of each element using a HashMap
2. Return the element with count > n/2

#### Pros:
- Easy to understand and implement
- Works for finding any element with frequency > threshold

#### Cons:
- Uses extra space
- Doesn't satisfy the O(1) space requirement

### 3. Sorting Approach

**Time Complexity:** O(n log n)  
**Space Complexity:** O(1) or O(log n)

#### Algorithm:
1. Sort the array
2. Return the element at index n/2

#### Why it works:
- After sorting, the majority element will occupy the middle position
- Since it appears more than n/2 times, it must cross the midpoint

#### Pros:
- Simple to implement
- Uses constant extra space (depending on sort algorithm)

#### Cons:
- Higher time complexity
- Doesn't satisfy the O(n) time requirement

## Key Insights

1. **Boyer-Moore is optimal** for the follow-up constraints (O(n) time, O(1) space)
2. **The majority element always exists** in this problem, which simplifies the verification step
3. **Pattern recognition:** This algorithm can be extended to find elements with frequency > n/k for any k
4. **Real-world applications:** Useful in distributed systems for finding consensus, stream processing, etc.

## Test Cases

The solution handles various edge cases:
- Single element array: `[1]` → `1`
- Clear majority: `[1,1,1,2,2]` → `1`
- Majority at boundaries: `[2,2,1,1,1,2,2]` → `2`
- Alternating pattern: `[3,2,3]` → `3`

## Files Structure

- `MajorityElementMy.java`: Template for your implementation (with package statement)
- `MajorityElement.java`: Optimal solution with all approaches (with package statement)  
- `README.md`: This detailed explanation