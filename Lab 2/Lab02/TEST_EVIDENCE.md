# Test Evidence - Lab 2

| Test case | Input / action | Expected | Actual result |
|---|---|---|---|
| Manual trace findFirst 'Java' | findFirst(shelf,"Java") on [C,Java,DSA,Java,SQL] | index 1, 2 comparisons | index 1 - PASS |
| Manual trace countMatches 'Java' | countMatches(shelf,"Java") | 2 | 2 - PASS |
| Manual trace findFirst 'Python' | findFirst(shelf,"Python") | -1, 5 comparisons | -1 - PASS |
| Target at first position | findFirst(["Alpha","Beta","Gamma"],"Alpha") | 0 | 0 - PASS |
| Target at last position | findFirst(["Alpha","Beta","Gamma"],"Gamma") | 2 | 2 - PASS |
| Target missing | findFirst(["Alpha","Beta","Gamma"],"Delta") | -1 | -1 - PASS |
| Target missing count | countMatches(["Alpha","Beta","Gamma"],"Delta") | 0 | 0 - PASS |
| Repeated / adjacent duplicates (first) | findFirst(["Java","Java","DSA","Java"],"Java") | 0 | 0 - PASS |
| Repeated / adjacent duplicates (count) | countMatches(["Java","Java","DSA","Java"],"Java") | 3 | 3 - PASS |
| Empty array (findFirst) | findFirst({}, "X") | -1 | -1 - PASS |
| Empty array (count) | countMatches({}, "X") | 0 | 0 - PASS |
| Case sensitivity | findFirst(shelf, "java") | -1 (case-sensitive) | -1 - PASS |
| Numbers overload (first) | findFirst([5,3,7,3,9], 3) | 1 | 1 - PASS |
| Numbers overload (count) | countMatches([5,3,7,3,9], 3) | 2 | 2 - PASS |

## Edge cases covered
- Target at first position (best case, 1 comparison).
- Target at last position (worst-ish, n comparisons).
- Target missing (worst case, n comparisons).
- Repeated titles including adjacent duplicates.
- Empty array.
- Case-sensitive comparison (no match for wrong case).
- Null array / null target rejected.

## Console evidence
See `run_output.txt` for the full successful run log.
