package com.philhanna.diff;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

/**
 * Abstract class that finds the test data directory
 */
public abstract class BaseTest {
 
   public static final String TEST_DIRECTORY = "/testdata";
   public URL testDirectory = null;
   
   @Before
   public void setUp() throws Exception {
      testDirectory = getClass().getResource(TEST_DIRECTORY);
      assertNotNull(testDirectory);
   }

   @After
   public void tearDown() throws Exception {
      testDirectory = null;
   }

   /**
    * Loads the lines of text in the specified file into a list of strings
    * @param name the name of the file in the test directory
    * @return a list of strings
    * @throws IOException if an I/O error occurs
    */
   protected List<String> fileToList(File fileName) throws IOException {
      final InputStream inputStream = new FileInputStream(fileName);
      return fileToList(inputStream);
   }

   /**
    * Loads the lines of text in the specified file into a list of strings
    * @param name the name of the file in the test directory
    * @return a list of strings
    * @throws IOException if an I/O error occurs
    */
   protected List<String> urlToList(URL url) throws IOException {
      final InputStream inputStream = url.openStream();
      return fileToList(inputStream);
   }

   /**
    * Loads the lines of text in the specified file into a list of strings
    * @param file an input file
    * @return a list of strings
    * @throws IOException if an I/O error occurs
    */
   protected List<String> fileToList(InputStream inputStream) throws IOException {
      final List<String> list = new ArrayList<String>();
      final BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
      for (;;) {
         final String line = in.readLine();
         if (line == null) break;
         list.add(line);
      }
      in.close();
      return list;
   }

}
