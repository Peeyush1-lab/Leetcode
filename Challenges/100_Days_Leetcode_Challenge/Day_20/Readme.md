# 1122. Relative Sort Array

## Problem Description
Given two arrays `arr1` and `arr2`, the elements of `arr2` are distinct, and all elements in `arr2` are also in `arr1`.

Sort the elements of `arr1` such that the relative ordering of items in `arr1` are the same as in `arr2`. Elements that do not appear in `arr2` should be placed at the end of `arr1` in ascending order.

## Difficulty
Easy

## Solution

**Time Complexity:** O(n + m + k) - where n is length of arr1, m is length of arr2, k is 1001 (constant)
**Space Complexity:** O(1) - counting array of fixed size 1001

```java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int arr[] = new int[1001];
        for(int i : arr1)
        {
            arr[i]++;
        }
        int k = 0;
        for(int j : arr2)
        {
            while(arr[j] != 0)
            {
                arr1[k++] = j;
                arr[j]--;
            }
        }
        for(int i = 0; i < arr.length; i++)
        {
            while(arr[i] != 0)
            {
                arr1[k++] = i;
                arr[i]--;
            }
        }
        return arr1;
    }
}
```

## Examples

```
Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
Explanation: Elements from arr2 come first in their order, then remaining elements in ascending order
```

```
Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
Output: [22,28,8,6,17,44]
Explanation: Elements in arr2 order, then 17 and 44 in ascending order
```

## Key Insight
Use counting sort with a frequency array. First place elements in arr2 order using their counts, then place remaining elements in ascending order by iterating through the frequency array.