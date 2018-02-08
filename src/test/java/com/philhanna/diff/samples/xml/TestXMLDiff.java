package com.philhanna.diff.samples.xml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.philhanna.diff.BaseTest;
import com.philhanna.diff.samples.CommandLineDiff;
import com.philhanna.diff.samples.CommandLineDiffFactory;

public class TestXMLDiff extends BaseTest {

   @Before
   public void setUp() throws Exception {
      super.setUp();
   }

   @After
   public void tearDown() throws Exception {
      super.tearDown();
   }

   @Test
   public void test() throws Exception {

      final URL url1 = new URL(testDirectory + "/good.workbook.xml");
      final InputStream in1 = url1.openStream();

      final URL url2 = new URL(testDirectory + "/bad.workbook.xml");
      final InputStream in2 = url2.openStream();

      final CommandLineDiffFactory factory = CommandLineDiffFactory
            .newInstance();
      factory.setXMLMode(true);
      final CommandLineDiff ldiff = factory.newCommandLineDiff(in1, in2);

      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      final PrintStream out = new PrintStream(baos);
      ldiff.run(out);
      out.flush();
      out.close();
      final byte[] outputBytes = baos.toByteArray();
      final String output = new String(outputBytes);
      System.out.println(output);
   }

}
