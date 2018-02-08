package com.philhanna.diff;

/**
 * A data structure that describes consecutive ranges of elements in two
 * lists that do not match.
 */
public interface Difference {

   /**
    * Returns the zero-based starting index at which list 1 is different
    */
   public int getLowIndex1();

   /**
    * Returns the zero-based ending index at which list 1 is different
    */
   public int getHighIndex1();

   /**
    * Returns the type of difference (ADD, CHANGE, or DELETE)
    */
   public DifferenceType getDifferenceType();
   
   /**
    * Returns the zero-based starting index at which list 2 is different
    */
   public int getLowIndex2();

   /**
    * Returns the zero-based ending index at which list 2 is different
    */
   public int getHighIndex2();
}
