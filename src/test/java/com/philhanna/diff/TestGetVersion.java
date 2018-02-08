package com.philhanna.diff;

import static org.junit.Assert.*;

import org.junit.Test;

import com.philhanna.diff.Differencer;

import java.io.*;
import java.net.URL;
import java.util.*;

public class TestGetVersion extends BaseTest {

   @Test
   public void testGetVersion() throws IOException {
      
      final URL versionURL = getClass().getResource("/version.properties");      
      assertNotNull("Version file does not exist", versionURL);
      
      final Properties properties = new Properties();
      final InputStream in = versionURL.openStream();
      properties.load(in);
      in.close();
      final String majorVersion = properties.getProperty("version.major");
      assertNotNull(majorVersion);
      final String minorVersion = properties.getProperty("version.minor");
      assertNotNull(minorVersion);
      final String patchVersion = properties.getProperty("version.patch");
      assertNotNull(patchVersion);
      final String expectedVersion = majorVersion + "." + minorVersion + "." + patchVersion;
      final String actualVersion = Differencer.getVersion();
      assertEquals(expectedVersion, actualVersion);
   }

}
