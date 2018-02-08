package com.philhanna.diff.samples.xml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.philhanna.diff.BaseTest;

/**
 * Unit tests for NodeNames
 */
public class TestNodeNames extends BaseTest {
   // ====================================================================
   // Class methods
   // ====================================================================

   @Before
   public void setUp() throws Exception {
      super.setUp();
   }

   @After
   public void tearDown() throws Exception {
      super.tearDown();
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

   @Test
   public void printTable() {
      final int expectedCount = 12;
      int actualCount = 0;
      for (final Integer index : NodeNames.getNodeTypes()) {
         final String value = NodeNames.getTypeName(index);
         assertNotNull(value);
         actualCount++;
      }
      assertEquals(expectedCount, actualCount);
   }

}
