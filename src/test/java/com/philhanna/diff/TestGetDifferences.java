package com.philhanna.diff;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.philhanna.diff.Difference;
import com.philhanna.diff.Differencer;

/**
 * Unit tests for the <code>Differencer</code>, which compares two lists
 * and returns the list of <code>Difference</code> objects
 */
public class TestGetDifferences extends BaseTest {

   @Test
   public void testSameList() throws Exception {

      final List<String> list1 = new ArrayList<String>();
      list1.add("Larry");
      list1.add("Curly");
      list1.add("Moe");

      final List<String> list2 = new ArrayList<String>();
      list2.add("Larry");
      list2.add("Curly");
      list2.add("Moe");

      final Differencer<String> differencer = new Differencer<String>(list1, list2);

      final List<String> expected = new ArrayList<String>();
      
      final List<String> actual = new ArrayList<String>();
      for (final Difference difference : differencer.getDifferences())
         actual.add(difference.toString());
      
      assertEquals(expected, actual);
   }

   @Test
   public void testNoCommonNames() throws Exception {

      final List<String> list1 = new ArrayList<String>();
      list1.add("Larry");
      list1.add("Curly");
      list1.add("Moe");

      final List<String> list2 = new ArrayList<String>();
      list2.add("Tom");
      list2.add("Dick");
      list2.add("Harry");

      final Differencer<String> differencer = new Differencer<String>(list1, list2);

      final List<String> expected = new ArrayList<String>();
      expected.add("1,3c1,3");
      
      final List<String> actual = new ArrayList<String>();
      for (final Difference difference : differencer.getDifferences())
         actual.add(difference.toString());
      
      assertEquals(expected, actual);
   }
   
   @Test
   public void testMixture() throws Exception {

      final List<String> list1 = new ArrayList<String>();
      list1.add("Larry");
      list1.add("Curly");
      list1.add("Shemp");
      list1.add("Moe");

      final List<String> list2 = new ArrayList<String>();
      list2.add("Larry");
      list2.add("Curly");
      list2.add("Larry");
      list2.add("Curly");
      list2.add("Curly Joe");
      list2.add("Moe");
      list2.add("Larry");

      final Differencer<String> differencer = new Differencer<String>(list1, list2);
      
      final List<String> expected = new ArrayList<String>();
      expected.add("3c3,5");
      expected.add("4a7");
      
      final List<String> actual = new ArrayList<String>();
      for (final Difference difference : differencer.getDifferences())
         actual.add(difference.toString());
      
      assertEquals(expected, actual);
   }
   
   @Test
   public void testMixOfIntegers() {
      final List<Integer> list1 = new ArrayList<Integer>();
      list1.add(0);
      list1.add(2);
      list1.add(3);

      final List<Integer> list2 = new ArrayList<Integer>();
      list2.add(0);
      list2.add(1);
      list2.add(3);
      list2.add(4);

      final Differencer<Integer> differencer = new Differencer<Integer>(list1, list2);
      
      final List<String> expected = new ArrayList<String>();
      expected.add("2c2");
      expected.add("3a4");
      
      final List<String> actual = new ArrayList<String>();
      for (final Difference difference : differencer.getDifferences())
         actual.add(difference.toString());
      
      assertEquals(expected, actual);
   }

   @Test
   public void testFromFiles() throws IOException {
      final List<String> list1 = urlToList(new URL(testDirectory + "/file1.txt"));
      final List<String> list2 = urlToList(new URL(testDirectory + "/file2.txt"));
      final Differencer<String> differencer = new Differencer<String>(list1, list2);
      
      final List<String> actual = new ArrayList<String>();
      for (final Difference difference : differencer.getDifferences()) {
         final String line = difference.toString();
         actual.add(line);
      }
      
      final List<String> expected = new ArrayList<String>();
      expected.add("0a1,6");
      expected.add("8,13c14");
      expected.add("16c17");
      expected.add("23a25,28");
   
      assertEquals(expected, actual);
   }
}
