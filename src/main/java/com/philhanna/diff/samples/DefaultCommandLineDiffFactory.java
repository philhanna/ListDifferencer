package com.philhanna.diff.samples;

import java.io.*;

import com.philhanna.diff.samples.text.TextDiff;
import com.philhanna.diff.samples.xml.XMLDiff;

/**
 * Default implementation of command line diff factory
 */
public class DefaultCommandLineDiffFactory extends CommandLineDiffFactory {

   @Override
   public CommandLineDiff newCommandLineDiff(InputStream in1, InputStream in2) throws IOException {
      CommandLineDiff diff = null;
      if (isXMLMode())
         diff = new XMLDiff(in1, in2);
      else
         diff = new TextDiff(in1, in2);
      return diff;
   }

}
