package com.philhanna.diff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Computes the difference between two lists. Written as a generic class
 * that can handle lists of anything that implements
 * <code>equals(Object other)</code> and <code>hashCode()</code>, so
 * that it can find the difference between two lists of strings, or two
 * XML files, or whatever can be made to implement these methods.
 * <p>
 * The algorithm consists of recursively finding the longest common
 * subsequence (<code>LCS</code>) of two lists as follows: Let
 * <code>X = (x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>n</sub>)</code>
 * and
 * <code>Y = (y<sub>1</sub>, y<sub>2</sub>, ..., y<sub>m</sub>)</code>.
 * Then
 * 
 * <pre>
 * LCS(X,Y) = {
 *    if either X or Y is empty then
 *       return the empty list
 *    else if the last element of X is the same as the last element of Y then
 *       return LCS(X-1, Y-1) + common last element of X and Y
 *    else if the last elements are different then
 *       return longer(LCS(X, Y-1) and LCS(X-1, Y))
 * }
 * where X-1 means the sublist of X that omits the last element of X
 * </pre>
 * 
 * Since the algorithm is recursive, it may need to compute the same
 * subsequences many times. The working results are cached for
 * performance reasons.
 * <p>
 * Note that the definition of a common subsequence means a list of
 * elements all of whom exist in both lists in the same order, but not
 * necessarily contiguously. In other words, in these two lists:
 * 
 * <pre>
 * list 1 = {"the", "cat", "in", "the", "hat", "came", "back", "today"}
 * list 2 = {"but, "the" "cat", "does", "not", "have", "a", "hat", "today"}
 * </pre>
 * 
 * the longest common subsequence is
 * 
 * <pre>
 * common = {"the" "cat", "hat", "today"}
 * </pre>
 * 
 * This common subsequence is found in consecutive (but not contiguous)
 * elements 1, 2, 5, and 8 of list 1, and elements 2, 3, 8, and 9 of
 * list 2. Visually, this is:
 * 
 * <pre>
 * list 1      common      list 2
 * ------      ------      1. but
 * 1. the      the         2. the
 * 2. cat      cat         3. cat
 * 3. in       ------      4. does
 * 4. the      ------      5. not
 * ------      ------      6. have
 * ------      ------      7. a
 * 5. hat      hat         8. hat
 * 6. came     ------      ------    
 * 7. back     ------      ------    
 * 8. today    today       9. today
 * </pre>
 * 
 * Once the longest common subsequence is found, the difference list is
 * computed in a straightforward manner: Any element in the first list
 * that is not in the common list is a deletion. Any element in the
 * second list that is not in the common list is an addition.
 * <p>
 * <b>References</b>
 * <ul>
 * <li><a href=
 * "http://en.wikipedia.org/wiki/Longest_common_subsequence_problem"
 * >http://en.wikipedia.org/wiki/Longest_common_subsequence_problem</a></li>
 * <li><a href=
 * "http://www.algorithmist.com/index.php/Longest_Common_Subsequence"
 * >http://www.algorithmist.com/index.php/Longest_Common_Subsequence</a>
 * </li>
 * </ul>
 */
public class Differencer<T> {

   // ====================================================================
   // Class methods
   // ====================================================================

   /**
    * Returns the software library version
    */
   public static final String getVersion() {
      final String VERSION_FILE = "/version.properties";
      try {
         final Properties properties = new Properties();
         properties.load(Differencer.class.getResourceAsStream(VERSION_FILE));
         final String version = String.format(
               "%s.%s.%s",
               properties.getProperty("version.major"),
               properties.getProperty("version.minor"),
               properties.getProperty("version.patch"));
         return version;
      }
      catch (IOException e) {
         final String errmsg = String.format("Unable to load %s", VERSION_FILE);
         return errmsg;
      }
   }

   /**
    * Returns the longest common subsequence of two lists
    */
   static final <T> List<T> LCS(final List<T> X, final List<T> Y) {
      return LCS(X, Y, new HashMap<String, List<T>>());
   }

   private static final <T> List<T> LCS(
         final List<T> X,
         final List<T> Y,
         final Map<String, List<T>> cache) {

      final int xlength = X.size();
      final int ylength = Y.size();
      final String key = xlength + "," + ylength;
      List<T> value = cache.get(key);
      if (value != null)
         return value;

      if (xlength == 0 || ylength == 0) {
         value = new ArrayList<T>();
         cache.put(key, value);
         return value;
      }

      final T xi = X.get(xlength - 1);
      final T yj = Y.get(ylength - 1);
      final List<T> Xm1 = X.subList(0, xlength - 1);
      final List<T> Ym1 = Y.subList(0, ylength - 1);

      value = new ArrayList<T>();
      cache.put(key, value);

      if (xi.equals(yj)) {
         value.addAll(LCS(Xm1, Ym1, cache));
         value.add(xi);
      }
      else {
         final List<T> xprime = LCS(X, Ym1, cache);
         final List<T> yprime = LCS(Xm1, Y, cache);
         if (xprime.size() > yprime.size()) {
            value.addAll(xprime);
         }
         else if (xprime.size() < yprime.size()) {
            value.addAll(yprime);
         }
         else {
            value.addAll(xprime);
         }
      }
      return value;
   }

   // ====================================================================
   // Instance variables
   // ====================================================================

   private List<Difference> differences = new ArrayList<Difference>();

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates a new <code>Differencer</code> object for the two lists
    */
   public Differencer(List<T> list1, List<T> list2) {

      // Read the common list as the driver. Then read each of the
      // two lists until they match the common element. Whatever you
      // have to skip is not in the common list, and constitutes the
      // difference list.

      final List<T> lcs = LCS(list1, list2);
      int index1 = -1;
      int index2 = -1;

      IndexPair checkpoint = new IndexPair(index1, index2);

      for (final T common : lcs) {

         // Check for differences

         checkpoint = checkForDifferences(index1, index2, checkpoint);

         // Advance list 1 index until it points to the common element

         for (;;) {
            index1++;
            final T element = list1.get(index1);
            if (element.equals(common))
               break;
         }

         // Advance list 2 index until it points to the common element

         for (;;) {
            index2++;
            final T element = list2.get(index2);
            if (element.equals(common))
               break;
         }

      }

      // Check for differences at end of the common block

      checkpoint = checkForDifferences(index1, index2, checkpoint);

      // Flush any remaining entries in both lists (they are known to
      // be not in the common list)

      while (++index1 < list1.size()) {
      }
      while (++index2 < list2.size()) {
      }

      // Check for differences after both lists are exhausted

      checkpoint = checkForDifferences(index1, index2, checkpoint);
   }

   /**
    * Checks for gaps in indices of greater than one since the last time
    * this method was called and creates a <code>Difference</code>
    * object if it finds any.
    */
   private IndexPair checkForDifferences(
         int index1,
         int index2,
         IndexPair prev) {
      final IndexPair current = new IndexPair(index1, index2);
      final Difference difference = current.compare(prev);
      if (difference != null)
         differences.add(difference);
      return current;
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   /**
    * Returns the list of differences
    */
   public List<Difference> getDifferences() {
      return differences;
   }
}
