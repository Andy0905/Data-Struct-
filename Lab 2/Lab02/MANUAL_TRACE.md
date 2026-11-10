# Manual Trace - Lab 2: Linear Search in Real Applications

## Scenario
Search for **'Java'** and **'Python'** in the shelf list
`["C", "Java", "DSA", "Java", "SQL"]`. Count every comparison.

## Search 'Java' (findFirst)
| Step | Index | Inspected value | Comparison result | Action |
|---|---|---|---|---|
| 1 | 0 | "C" | "C".equals("Java") = false | continue |
| 2 | 1 | "Java" | "Java".equals("Java") = true | STOP, return 1 |

Total comparisons = 2. First match at index 1.

## Search 'Java' (countMatches)
| Step | Index | Inspected value | Match? | Running count |
|---|---|---|---|---|
| 1 | 0 | "C" | no | 0 |
| 2 | 1 | "Java" | yes | 1 |
| 3 | 2 | "DSA" | no | 1 |
| 4 | 3 | "Java" | yes | 2 |
| 5 | 4 | "SQL" | no | 2 |

Total comparisons = 5. Total matches = 2.

## Search 'Python' (findFirst)
| Step | Index | Inspected value | Comparison result | Action |
|---|---|---|---|---|
| 1 | 0 | "C" | false | continue |
| 2 | 1 | "Java" | false | continue |
| 3 | 2 | "DSA" | false | continue |
| 4 | 3 | "Java" | false | continue |
| 5 | 4 | "SQL" | false | continue |

End of list reached -> return -1. Total comparisons = 5 (worst case, target missing).

## Summary
- 'Java' first index = 1 (2 comparisons).
- 'Java' count = 2 (5 comparisons).
- 'Python' first index = -1 (5 comparisons, worst case).
