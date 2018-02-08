package com.philhanna.diff.samples;

import java.io.*;

import com.philhanna.diff.Differencer;

/**
 * A simple command line differencing tool.
 */
public class Main {

   /**
    * Entry point
    */
   public static void main(String[] args) {

      // If no arguments, print help and exit

      if (args.length == 0) {
         showUsage();
         return;
      }

      // If help or version requested, print help and exit

      for (final String arg : args) {
         if (arg.equals("-h") || arg.equals("--help")) {
            showUsage();
            return;
         }
         if (arg.equals("-v") || arg.equals("--version")) {
            System.out.printf(
                  "ldiff: Library version is %s\n",
                  Differencer.getVersion());
            return;
         }
      }

      // Collect the two file names

      File file1 = null;
      File file2 = null;
      String lastArg = null;
      boolean xmlMode = false;

      for (final String arg : args) {
         lastArg = arg;
         if (arg.equals("-x") || arg.equals("--xml")) {
            xmlMode = true;
         }
         else if (file1 == null) {
            file1 = new File(arg);
            if (!file1.exists()) {
               System.err.printf("ldiff: %s: No such file or directory\n", arg);
               System.exit(-1);
            }
         }
         else if (file2 == null) {
            file2 = new File(arg);
            if (!file2.exists()) {
               System.err.printf("ldiff: %s: No such file or directory\n", arg);
               System.exit(-1);
            }
         }
         else {
            System.err.printf("ldiff: Too many file names specified\n");
            System.err
                  .printf("ldiff: Try `ldiff --help' for more information\n");
            System.exit(-1);
         }
      }

      if (file2 == null) {
         System.err.printf("ldiff: missing operand after `%s'\n", lastArg);
         System.err.printf("ldiff: Try `ldiff --help' for more information\n");
         System.exit(-1);
      }

      // Run the diff

      try {
         final CommandLineDiffFactory factory = CommandLineDiffFactory
               .newInstance();
         factory.setXMLMode(xmlMode);
         final InputStream in1 = new FileInputStream(file1);
         final InputStream in2 = new FileInputStream(file2);
         final CommandLineDiff cld = factory.newCommandLineDiff(in1, in2);
         cld.run();
      }
      catch (IOException e) {
         System.err.printf("ldiff: I/O exception: %s\n", e.getMessage());
         System.exit(-1);
      }
   }

   private static void showUsage() {
      final String[] lines = {
            "Usage: ldiff [OPTION]... FILES",
            "Compare FILES line by line",
            "",
            "where OPTION is one of the following:",
            "",
            "-h | --help         Displays this help text and exits",
            "-v | --version      Displays the library version and exits",
            "-x | --xml          Specifies that the files are XML",
            "",
            "and FILES are exactly two file names", };
      for (final String line : lines) {
         System.out.println(line);
      }
   }

}
