# ListDifferencer
Java library that computes the difference between two lists.
Written as a generic class that can handle lists of anything
that implements `equals(Object other)` and `hashCode()`, so
that it can find the difference between two lists of strings,
or two XML files, or whatever can be made to implement these methods.

## Algorithm
The algorithm consists of recursively finding the longest common
subsequence (`LCS`) of two lists as follows: Let

X = (x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>n</sub>)

and

Y = (y<sub>1</sub>, y<sub>2</sub>, ..., y<sub>m</sub>).

Then

```
LCS(X,Y) = {
   if either X or Y is empty then
      return the empty list
   else if the last element of X is the same as the last element of Y then
      return LCS(X-1, Y-1) + common last element of X and Y
   else if the last elements are different then
      return longer(LCS(X, Y-1) and LCS(X-1, Y))
}
where X-1 means the sublist of X that omits the last element of X
```

Since the algorithm is recursive, it may need to compute the same
subsequences many times. The working results are cached for
performance reasons.

Note that the definition of a common subsequence means a list of
elements all of whom exist in both lists in the same order, but not
necessarily contiguously. In other words, in these two lists:

```
list 1 = {"the", "cat", "in", "the", "hat", "came", "back", "today"}
list 2 = {"but, "the" "cat", "does", "not", "have", "a", "hat", "today"}
```

the longest common subsequence is

```
common = {"the" "cat", "hat", "today"}
```

This common subsequence is found in consecutive (but not contiguous)
elements 1, 2, 5, and 8 of list 1, and elements 2, 3, 8, and 9 of
list 2. Visually, this is:

```
list 1      common      list 2
------      ------      1. but
1. the      the         2. the
2. cat      cat         3. cat
3. in       ------      4. does
4. the      ------      5. not
------      ------      6. have
------      ------      7. a
5. hat      hat         8. hat
6. came     ------      ------    
7. back     ------      ------    
8. today    today       9. today
```

Once the longest common subsequence is found, the difference list is
computed in a straightforward manner: Any element in the first list
that is not in the common list is a deletion. Any element in the
second list that is not in the common list is an addition.

## References
* [http://en.wikipedia.org/wiki/Longest_common_subsequence_problem](http://en.wikipedia.org/wiki/Longest_common_subsequence_problem)
* [http://www.algorithmist.com/index.php/Longest_Common_Subsequence](http://www.algorithmist.com/index.php/Longest_Common_Subsequence)
