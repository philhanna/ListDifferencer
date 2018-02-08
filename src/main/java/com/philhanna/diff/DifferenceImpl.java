package com.philhanna.diff;

/**
 * Implementation of <code>Difference</code> interface
 */
public class DifferenceImpl implements Difference {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   // ====================================================================
   // Instance variables
   // ====================================================================

   private final DifferenceType differenceType;
   private final int lowIndex1;
   private final int highIndex1;
   private final int lowIndex2;
   private final int highIndex2;

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates an implementation of the <code>Difference</code> interface
    */
   public DifferenceImpl(
         DifferenceType differenceType,
         int lowIndex1,
         int highIndex1,
         int lowIndex2,
         int highIndex2) {
      this.differenceType = differenceType;
      this.lowIndex1 = lowIndex1;
      this.highIndex1 = highIndex1;
      this.lowIndex2 = lowIndex2;
      this.highIndex2 = highIndex2;
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   @Override
   public DifferenceType getDifferenceType() {
      return differenceType;
   }

   @Override
   public int getLowIndex1() {
      return lowIndex1;
   }

   @Override
   public int getHighIndex1() {
      return highIndex1;
   }

   @Override
   public int getLowIndex2() {
      return lowIndex2;
   }

   @Override
   public int getHighIndex2() {
      return highIndex2;
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder();
      
      final int lowLineNumber1 = lowIndex1 + 1;
      final int highLineNumber1 = highIndex1 + 1;
      final int lowLineNumber2 = lowIndex2 + 1;
      final int highLineNumber2 = highIndex2 + 1;
      
      sb.append(lowLineNumber1);
      if (lowLineNumber1 != highLineNumber1)
         sb.append(",").append(highLineNumber1);
      
      switch (differenceType) {
         case ADD:
            sb.append("a");
            break;
         case CHANGE:
            sb.append("c");
            break;
         case DELETE:
            sb.append("d");
            break;
      }
      sb.append(lowLineNumber2);
      if (lowLineNumber2 != highLineNumber2)
         sb.append(",").append(highLineNumber2);

      final String output = sb.toString();
      return output;
   }
}