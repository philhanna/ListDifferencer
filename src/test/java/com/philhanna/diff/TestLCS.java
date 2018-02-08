package com.philhanna.diff;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.philhanna.diff.Differencer;

/**
 * Unit tests for longest common subsequence
 */
public class TestLCS extends BaseTest {

   // ==================================================================
   // Fixtures
   // ==================================================================

   @Before
   public void setUp() throws Exception {
      super.setUp();
   }

   @After
   public void tearDown() throws Exception {
      super.tearDown();
   }

   // ==================================================================
   // Unit tests
   // ==================================================================

   @Test
   public void testSameList() throws Exception {

      final List<Comparable<?>> list1 = new ArrayList<Comparable<?>>();
      list1.add("Larry");
      list1.add("Curly");
      list1.add("Moe");

      final List<Comparable<?>> list2 = new ArrayList<Comparable<?>>();
      list2.add("Larry");
      list2.add("Curly");
      list2.add("Moe");

      final List<Comparable<?>> actual = Differencer.LCS(list1, list2);
      final List<Comparable<?>> expected = new ArrayList<Comparable<?>>();
      expected.add("Larry");
      expected.add("Curly");
      expected.add("Moe");
      assertEquals(expected, actual);
   }

   @Test
   public void testNoCommonNames() throws Exception {

      final List<Comparable<?>> list1 = new ArrayList<Comparable<?>>();
      list1.add("Larry");
      list1.add("Curly");
      list1.add("Moe");

      final List<Comparable<?>> list2 = new ArrayList<Comparable<?>>();
      list2.add("Tom");
      list2.add("Dick");
      list2.add("Harry");

      final List<Comparable<?>> actual = Differencer.LCS(list1, list2);
      final List<Comparable<?>> expected = new ArrayList<Comparable<?>>();
      assertEquals(expected, actual);
   }

   @Test
   public void testMixture() throws Exception {

      final List<String> list1 = new ArrayList<String>();
      list1.add("Larry");
      list1.add("Curly Joe");
      list1.add("Moe");

      final List<String> list2 = new ArrayList<String>();
      list2.add("Larry");
      list2.add("Curly");
      list2.add("Moe");
      list2.add("Shemp");

      final List<String> actual = Differencer.LCS(list1, list2);
      final List<String> expected = new ArrayList<String>();
      expected.add("Larry");
      expected.add("Moe");
      assertEquals(expected, actual);
   }

   @Test
   public void testMixtureIntegers() throws Exception {

      // 0 Larry
      // 1 Curly
      // 2 Curly Joe
      // 3 Moe
      // 4 Shemp

      final List<Integer> list1 = new ArrayList<Integer>();
      list1.add(0);
      list1.add(2);
      list1.add(3);

      final List<Integer> list2 = new ArrayList<Integer>();
      list2.add(0);
      list2.add(1);
      list2.add(3);
      list2.add(4);

      final List<Integer> actual = Differencer.LCS(list1, list2);
      final List<Integer> expected = new ArrayList<Integer>();
      expected.add(0);
      expected.add(3);
      assertEquals(expected, actual);
   }

   @Test
   public void testDuplicates() throws Exception {

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

      final List<String> actual = Differencer.LCS(list1, list2);
      final List<String> expected = new ArrayList<String>();
      expected.add("Larry");
      expected.add("Curly");
      expected.add("Moe");
      assertEquals(expected, actual);
   }

   @Test
   public void testThroughConstructor() throws Exception {

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

      final List<String> actual = Differencer.LCS(list1, list2);
      final List<String> expected = new ArrayList<String>();
      expected.add("Larry");
      expected.add("Curly");
      expected.add("Moe");
      assertEquals(expected, actual);
   }

   @Test
   public void testShortFail() throws Exception {
      final List<String> list1 = new ArrayList<String>();
      list1.add("This part of the");
      list1.add("compress the size of the");
      list1.add("text that is outdated.");
      list1.add("It is important to spell");
      list1.add("check this dokument. On");
      list1.add("the other hand, a");
      list1.add("misspelled word isn't");
      list1.add("the end of the world.");
      list1.add("Nothing in the rest of");
      list1.add("this paragraph needs to");
      list1.add("be changed. Things can");
      list1.add("be added after it.");

      final List<String> list2 = new ArrayList<String>();
      list2.add("This is an important");
      list2.add("notice! Is should");
      list2.add("therefore be located at");
      list2.add("the beginning of the this");
      list2.add("document!");
      list2.add("This part of the");
      list2.add("compress anything.");
      list2.add("It is important to spell");
      list2.add("check this document. On");
      list2.add("the other hand, a");
      list2.add("misspelled word isn't");
      list2.add("the end of the world.");
      list2.add("Nothing in the rest of");
      list2.add("this paragraph needs to");
      list2.add("be changed. Things can");
      list2.add("be added after it.");
      list2.add("");
      list2.add("This paragraph contains");
      list2.add("important new additions");
      list2.add("to this document.");

      new Differencer<String>(list1, list2);
   }

   @Test
   public void testFromWikipedia() throws Exception {
      final List<String> list1 = new ArrayList<String>();
      list1.add("This part of the");
      list1.add("document has stayed the");
      list1.add("same from version to");
      list1.add("version.  It shouldn't");
      list1.add("be shown if it doesn't");
      list1.add("change.  Otherwise, that");
      list1.add("would not be helping to");
      list1.add("compress the size of the");
      list1.add("changes.");
      list1.add("");
      list1.add("This paragraph contains");
      list1.add("text that is outdated.");
      list1.add("It will be deleted in the");
      list1.add("near future.");
      list1.add("");
      list1.add("It is important to spell");
      list1.add("check this dokument. On");
      list1.add("the other hand, a");
      list1.add("misspelled word isn't");
      list1.add("the end of the world.");
      list1.add("Nothing in the rest of");
      list1.add("this paragraph needs to");
      list1.add("be changed. Things can");
      list1.add("be added after it.");

      final List<String> list2 = new ArrayList<String>();
      list2.add("This is an important");
      list2.add("notice! It should");
      list2.add("therefore be located at");
      list2.add("the beginning of this");
      list2.add("document!");
      list2.add("");
      list2.add("This part of the");
      list2.add("document has stayed the");
      list2.add("same from version to");
      list2.add("version.  It shouldn't");
      list2.add("be shown if it doesn't");
      list2.add("change.  Otherwise, that");
      list2.add("would not be helping to");
      list2.add("compress anything.");
      list2.add("");
      list2.add("It is important to spell");
      list2.add("check this document. On");
      list2.add("the other hand, a");
      list2.add("misspelled word isn't");
      list2.add("the end of the world.");
      list2.add("Nothing in the rest of");
      list2.add("this paragraph needs to");
      list2.add("be changed. Things can");
      list2.add("be added after it.");
      list2.add("");
      list2.add("This paragraph contains");
      list2.add("important new additions");
      list2.add("to this document.");

      final List<String> expected = new ArrayList<String>();
      for (int i = 0; i < 7; i++)
         expected.add(list1.get(i));
      for (int i = 14; i < 16; i++)
         expected.add(list1.get(i));
      for (int i = 17; i < 24; i++)
         expected.add(list1.get(i));

      final List<String> actual = Differencer.LCS(list1, list2);

      assertEquals(expected, actual);
   }
}
