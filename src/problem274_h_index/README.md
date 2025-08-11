# 274. H-Index

## Problem Description

Given an array of integers `citations` where `citations[i]` is the number of citations a researcher received for their ith paper, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.

**Difficulty:** Medium

## Examples

### Example 1:
```
Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
```

### Example 2:
```
Input: citations = [1,3,1]
Output: 1
```

## Constraints
- `n == citations.length`
- `1 <= n <= 5000`
- `0 <= citations[i] <= 1000`

## Algorithm Explanation

### Understanding H-Index

The h-index is a metric to measure both the productivity and citation impact of a researcher. An h-index of h means:
- The researcher has h papers
- Each of these h papers has been cited at least h times
- The remaining papers have no more than h citations each

### Approach 1: Sorting Method

**Time Complexity:** O(n log n)  
**Space Complexity:** O(1)

#### Algorithm:
1. Sort the citations array in descending order
2. For each position i, we have (i+1) papers with at least citations[i] citations
3. The h-index at position i is min(i+1, citations[i])
4. Return the maximum h-index found

#### Step-by-Step Example:
For citations = [3,0,6,1,5]:

1. Sort descending: [6,5,3,1,0]
2. Check each position:
   - Position 0: 1 paper with ≥6 citations → h = min(1,6) = 1
   - Position 1: 2 papers with ≥5 citations → h = min(2,5) = 2  
   - Position 2: 3 papers with ≥3 citations → h = min(3,3) = 3
   - Position 3: 4 papers with ≥1 citations → h = min(4,1) = 1
   - Position 4: 5 papers with ≥0 citations → h = min(5,0) = 0
3. Maximum h-index = 3

### Approach 2: Counting Method (Optimal)

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

#### Algorithm:
1. Create buckets to count papers by citation count (0 to n citations)
2. Papers with >n citations go in bucket[n] (since h-index can't exceed n)
3. Iterate from right to left, counting papers with at least k citations
4. Return the first k where we have at least k papers with ≥k citations

#### Step-by-Step Example:
For citations = [3,0,6,1,5] (n=5):

1. Create buckets: [0,0,0,0,0,0] (indices 0-5)
2. Count citations:
   - citations[0]=3 → buckets[3]++
   - citations[1]=0 → buckets[0]++
   - citations[2]=6 → buckets[5]++ (6≥5, so use bucket[5])
   - citations[3]=1 → buckets[1]++
   - citations[4]=5 → buckets[5]++
   - Result: buckets = [1,1,0,1,0,2]

3. Count papers with at least k citations (right to left):
   - k=5: 2 papers with ≥5 citations, 2≥5? No
   - k=4: 2 papers with ≥4 citations, 2≥4? No  
   - k=3: 2+1=3 papers with ≥3 citations, 3≥3? Yes! Return 3

### Key Insights

1. **Definition Understanding:** h-index h means exactly h papers with ≥h citations each
2. **Upper Bound:** h-index cannot exceed the number of papers (n)
3. **Optimization:** Counting method avoids sorting by using the constraint that citations ≤ 1000
4. **Edge Cases:** 
   - All papers have 0 citations → h-index = 0
   - Single paper with many citations → h-index = 1
   - All papers highly cited → h-index = n

### Why Counting Method is Optimal

- Avoids O(n log n) sorting
- Leverages the constraint that we only need to consider h-values from 0 to n
- Uses bucket sort principle for better time complexity
- Still maintains clear logic flow

## Test Cases

The implementation includes comprehensive test cases covering:
- Standard cases from examples
- Edge case: single paper
- Edge case: no citations  
- Edge case: all papers highly cited

Both approaches are tested to ensure correctness and demonstrate the performance difference.
