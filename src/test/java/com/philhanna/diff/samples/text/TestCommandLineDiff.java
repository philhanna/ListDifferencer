package com.philhanna.diff.samples.text;

import static org.junit.Assert.*;

import java.io.*;
import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.philhanna.diff.BaseTest;
import com.philhanna.diff.samples.text.TextDiff;

public class TestCommandLineDiff extends BaseTest {

   private PrintStream saveOut;
   
   @Before
   public void setUp() throws Exception {
      super.setUp();
      saveOut = System.out;
   }

   @After
   public void tearDown() throws Exception {
      System.setOut(saveOut);
      super.tearDown();
   }

   @Test
   public void runZipCodes() throws IOException {
      
      // Override stdout to use an output file
      
      final File actualFile = File.createTempFile("difftest", null);
      actualFile.deleteOnExit();
      final PrintStream out = new PrintStream(new FileOutputStream(actualFile));
      System.setOut(out);
      
      // Run the command line diff and write the output to the file
      
      final URL url1 = new URL(testDirectory + "/zip1.txt");
      final InputStream in1 = url1.openStream();
      
      final URL url2 = new URL(testDirectory + "/zip2.txt");
      final InputStream in2 = url2.openStream();
      
      final TextDiff diff = new TextDiff(in1, in2);
      
      diff.run();
      
      out.flush();
      out.close();
      
      // Load expected results
      
      final URL expectedURL = new URL(testDirectory + "/zipdiff.txt");
      final List<String> expected = urlToList(expectedURL);
      final List<String> actual = fileToList(actualFile);
      
      assertEquals(expected, actual);
   }

}
