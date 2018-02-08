package com.philhanna.diff.samples;

import java.io.*;
import java.util.List;

import com.philhanna.diff.*;

/**
 * Base class for command line differencers
 */
public abstract class CommandLineDiff {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   /**
    * Utility method to format a difference the way GNU diff does
    */
   static String toString(Difference difference) {
      final StringBuilder sb = new StringBuilder();

      final int lowLineNumber1 = difference.getLowIndex1() + 1;
      final int highLineNumber1 = difference.getHighIndex1() + 1;
      sb.append(lowLineNumber1);
      if (lowLineNumber1 != highLineNumber1)
         sb.append(",").append(highLineNumber1);

      switch (difference.getDifferenceType()) {
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

      final int lowLineNumber2 = difference.getLowIndex2() + 1;
      final int highLineNumber2 = difference.getHighIndex2() + 1;
      sb.append(lowLineNumber2);
      if (lowLineNumber2 != highLineNumber2)
         sb.append(",").append(highLineNumber2);

      final String output = sb.toString();
      return output;
   }

   // ====================================================================
   // Instance variables
   // ====================================================================

   // ====================================================================
   // Constructors
   // ====================================================================

   // ====================================================================
   // Instance methods
   // ====================================================================

   public void run(PrintStream out) {
      final Differencer<? extends Object> differencer = getDifferencer();
      for (final Difference difference : differencer.getDifferences()) {

         out.printf("%s\n", toString(difference));

         final int lo1 = difference.getLowIndex1();
         final int hi1 = difference.getHighIndex1();
         final int lo2 = difference.getLowIndex2();
         final int hi2 = difference.getHighIndex2();

         switch (difference.getDifferenceType()) {

            case ADD:
               for (int index = lo2; index <= hi2; index++)
                  out.printf("> %s\n", getList2().get(index));
               break;

            case CHANGE:
               for (int index = lo1; index <= hi1; index++)
                  out.printf("< %s\n", getList1().get(index));
               out.printf("---\n");
               for (int index = lo2; index <= hi2; index++)
                  out.printf("> %s\n", getList2().get(index));
               break;

            case DELETE:
               for (int index = lo1; index <= hi1; index++)
                  out.printf("< %s\n", getList1().get(index));
               break;
         }
      }
      out.flush();
      
   }
   public void run() {
      final PrintStream out = System.out;
      run(out);
   }

   protected abstract Differencer<? extends Object> getDifferencer();

   protected abstract List<? extends Object> getList1();

   protected abstract List<? extends Object> getList2();
}
