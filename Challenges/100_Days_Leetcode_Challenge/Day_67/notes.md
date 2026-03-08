# Implement Queue using Stacks - Study Notes

## Approach: Push-Heavy (Your Solution)

### Key Insight

A **stack** is LIFO (Last In First Out), but a **queue** is FIFO (First In First Out). To implement a queue using stacks, we need to reverse the order.

Your solution maintains the queue order in the `in` stack by reversing elements on every push.

## Algorithm

**Push Operation:**
1. Transfer all elements from `in` to `out` (reverses order)
2. Push new element to `in` (now at bottom)
3. Transfer all elements back from `out` to `in` (reverses back)
4. Result: New element is at the bottom of `in` stack

**Pop/Peek/Empty Operations:**
- Direct stack operations on `in` (O(1))

## Visual Example

```
Initial: in=[], out=[]

push(1):
  in=[1]

push(2):
  Transfer: in=[] → out=[1]
  Push 2: in=[2]
  Transfer back: in=[2,1], out=[]

push(3):
  Transfer: in=[] → out=[1,2]
  Push 3: in=[3]
  Transfer back: in=[3,2,1], out=[]

pop():
  in.pop() → returns 1
  in=[3,2]

peek():
  in.peek() → returns 2
```

## Complexity

- **Push**: O(n) - need to transfer all elements twice
- **Pop**: O(1) - direct stack pop
- **Peek**: O(1) - direct stack peek
- **Empty**: O(1) - check if stack empty
- **Space**: O(n) - two stacks

## Trade-offs

**Your Approach (Push-Heavy):**
- ✅ Simple pop/peek (O(1))
- ❌ Expensive push (O(n))
- Good when: More pops than pushes

**Lazy Approach (Pop-Heavy):**
- ✅ Simple push (O(1))
- ❌ Expensive pop first time (amortized O(1))
- Good when: More pushes than pops

## Edge Cases

1. Empty queue: `empty()` → true
2. Single element: push(1), pop() → 1, empty() → true
3. Multiple operations: push-push-pop-push-peek-pop
4. Pop until empty then push again