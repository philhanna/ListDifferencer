package com.philhanna.diff.samples;

import java.io.*;

/**
 * Factory class that creates instances of a command line diff tool with
 * the appropriate options.
 */
public abstract class CommandLineDiffFactory {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   /**
    * Name of boolean attribute that indicates the files to be compared
    * are XML
    */
   public static final String XML_MODE = "XML_MODE";

   // ====================================================================
   // Class methods
   // ====================================================================

   /**
    * Returns an instance of the command line diff factory
    */
   public static CommandLineDiffFactory newInstance() {
      return new DefaultCommandLineDiffFactory();
   }

   // ====================================================================
   // Instance variables
   // ====================================================================

   private boolean xmlMode = false;

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Protected constructor to prevent instantiation
    */
   protected CommandLineDiffFactory() {
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   /**
    * Creates a new instance of a command line diff object according to
    * the factory configuration
    * @param in1 the first input stream
    * @param in2 the second input stream
    * @throws IOException if an I/O error occurs
    */
   public abstract CommandLineDiff newCommandLineDiff(InputStream in1, InputStream in2) throws IOException;
   
   /**
    * Sets the XML mode
    */
   public void setXMLMode(boolean xmlMode) {
      this.xmlMode = xmlMode;
   }
   
   /**
    * Returns the XML mode
    */
   public boolean isXMLMode() {
      return xmlMode;
   }
}
