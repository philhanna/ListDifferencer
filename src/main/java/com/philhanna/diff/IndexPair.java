package com.philhanna.diff;

/**
 * Keeps track of a pair of indices (current index from lists 1 and 2)
 * and creates a <code>Difference</code> object when there is a gap of
 * more than one in either index.
 */
public class IndexPair {

   // ====================================================================
   // Instance variables
   // ====================================================================

   private final int index1;
   private final int index2;

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates a new index pair data structure
    */
   public IndexPair(int index1, int index2) {
      this.index1 = index1;
      this.index2 = index2;
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   /**
    * Compares two index pair data structures and produces a
    * <code>Difference</code> if there are gaps of more than one in
    * either index.
    */
   public Difference compare(IndexPair prev) {

      final int gap1 = this.index1 - prev.index1;
      final int gap2 = this.index2 - prev.index2;

      if (gap1 > 1 && gap2 > 1)
         /*
          * This is a change. The elements in list 1 after its previous
          * index and before its current index are deleted. The elements
          * in list 2 after its previous index and before its current
          * index are added.
          */
         return new DifferenceImpl(
               DifferenceType.CHANGE,
               prev.index1 + 1,
               this.index1 - 1,
               prev.index2 + 1,
               this.index2 - 1);

      if (gap1 > 1)
         /*
          * This is a delete. The elements in list 1 after its previous
          * index and before its current index are deleted. The change
          * happens in list 2 at its previous index.
          */
         return new DifferenceImpl(
               DifferenceType.DELETE,
               prev.index1 + 1,
               this.index1 - 1,
               prev.index2,
               prev.index2);

      if (gap2 > 1)
         /*
          * This is a add. The elements in list 2 after its previous
          * index and before its current index are added. The change
          * happens in list 1 at its previous index.
          */
         return new DifferenceImpl(
               DifferenceType.ADD,
               prev.index1,
               prev.index1,
               prev.index2 + 1,
               this.index2 - 1);

      /*
       * No difference
       */
      return null;
   }
}
