package com.philhanna.diff.samples.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.philhanna.diff.Differencer;
import com.philhanna.diff.samples.CommandLineDiff;

/**
 * A simple implementation of the GNU <code>diff</code> tool. No special
 * features are supported, just a diff of two text files.
 */
public class TextDiff extends CommandLineDiff {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   /**
    * Utility method to load the contents of a text input stream into a
    * list of strings.
    * @param inputStream the text input stream to be loaded
    * @return a list of strings
    * @throws IOException if an I/O error occurs reading the file
    */
   static List<String> loadFileIntoList(InputStream inputStream)
         throws IOException {
      final List<String> list = new ArrayList<String>();
      final BufferedReader in = new BufferedReader(
            new InputStreamReader(inputStream));
      for (;;) {
         final String line = in.readLine();
         if (line == null)
            break;
         list.add(line);
      }
      in.close();
      return list;
   }

   // ====================================================================
   // Instance variables
   // ====================================================================

   private List<String> list1 = new ArrayList<String>();
   private List<String> list2 = new ArrayList<String>();

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates a command-line diff object for two text files
    * @param file1 the first text file
    * @param file2 the second text file
    * @throws IOException
    */
   public TextDiff(File file1, File file2) throws IOException {
      this(new FileInputStream(file1), new FileInputStream(file2));
   }

   public TextDiff(InputStream in1, InputStream in2) throws IOException {
      this.list1 = loadFileIntoList(in1);
      this.list2 = loadFileIntoList(in2);
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   @Override
   protected Differencer<String> getDifferencer() {
      return new Differencer<String>(list1, list2);
   }

   @Override
   protected List<String> getList1() {
      return list1;
   }

   @Override
   protected List<String> getList2() {
      return list2;
   }
}
