# Technical Reflection - Lab 3

1. Which invariant or edge case caused the most difficult failure?
   The single-node deletion case: when the only node is removed, both head and
   tail must become null and size 0. The failing assertion was that tail was not
   cleared, fixed by setting tail = cur.prev (null) in deleteByValue.

2. How did measured operations compare with your complexity prediction?
   insertAtHead/insertAtTail stayed ~constant across n=100/1000/10000, while
   deleteByValue(missing), checkInvariants and toArray grew roughly linearly,
   matching O(1) vs O(n) predictions.

3. Which agent recommendation did you verify independently, and what evidence
   determined your decision?
   The deletion pointer-rewiring order; I traced it by hand (MANUAL_TRACE.md)
   and confirmed the resulting forward/backward snapshots and invariants.

4. What implementation choice would you change for a much larger dataset?
   Add a hash index for value->node lookup to make deleteByValue O(1) average,
   and consider a sentinel/dummy head to simplify boundary cases.

5. Identify one limitation that remains in your submitted solution.
   deleteByValue removes only the first matching value; deleting all duplicates
   requires repeated calls. No generic type support (ints only).
