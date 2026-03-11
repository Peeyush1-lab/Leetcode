# Remove K Digits - Study Notes

## Approach: Greedy with Monotonic Stack

### Key Insight

To get the **smallest number**, we want smaller digits to appear earlier. Use a **monotonic increasing stack**:
- Remove larger digits when we see a smaller one
- Keep removing until k removals done or stack is monotonic

**Greedy Strategy**: At each position, if current digit is smaller than the previous, remove the previous to make the number smaller.

## Algorithm

1. **Build monotonic stack**: For each digit, remove larger previous digits (up to k removals)
2. **Handle remaining k**: If k > 0 after processing, remove from end
3. **Remove leading zeros**: Skip zeros at the start
4. **Return result**: Empty string becomes "0"

## Visual Example

**Input**: num = "1432219", k = 3

```
Process '1': stack = "1"

Process '4': 4 > 1, append
  stack = "14"

Process '3': 3 < 4, remove '4' (k=2 left)
  stack = "13"

Process '2': 2 < 3, remove '3' (k=1 left)
  stack = "12"

Process '2': 2 = 2, append
  stack = "122"

Process '1': 1 < 2, remove '2' (k=0 left, stop removing)
  stack = "121"

Process '9': 9 > 1, append
  stack = "1219"

k = 0, no more removals needed
No leading zeros
Result: "1219"
```

## Step-by-Step Breakdown

**1. Monotonic Stack Building:**
- While current digit < stack top AND k > 0: remove stack top
- Append current digit

**2. Handle Excess k:**
```java
stack.setLength(stack.length() - k);
```
If k removals remain, remove from end

**3. Remove Leading Zeros:**
```java
while (i < stack.length() && stack.charAt(i) == '0') i++;
```

**4. Edge Case:**
Empty result → return "0"

## Complexity

- **Time**: O(n) - each digit processed once
- **Space**: O(n) - StringBuilder for stack

## Key Cases

1. **All ascending**: "123456", k=3 → "123" (remove from end)
2. **All descending**: "654321", k=3 → "321" (remove from start)
3. **Leading zeros**: "10200", k=1 → "200"
4. **Remove all**: "10", k=2 → "0"
5. **No removals needed**: k=0 → original number