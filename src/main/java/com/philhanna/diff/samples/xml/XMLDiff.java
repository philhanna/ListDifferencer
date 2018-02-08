package com.philhanna.diff.samples.xml;

import java.io.*;
import java.util.List;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.philhanna.diff.*;
import com.philhanna.diff.samples.CommandLineDiff;

/**
 * An implementation of the GNU <code>diff</code> tool for XML files.
 */
public class XMLDiff extends CommandLineDiff {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   /**
    * Parses the specified XML file with the default document builder
    * @param file and XML file
    * @return a DOM object
    * @throws SAXException if a parsing error occurs
    * @throws IOException if an I/O error occurs
    */
   static Document parse(InputStream in) throws SAXException, IOException {
      final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      try {
         final DocumentBuilder db = dbf.newDocumentBuilder();
         final Document doc = db.parse(in);
         return doc;
      }
      catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }
   }

   // ====================================================================
   // Instance variables
   // ====================================================================

   private List<DOMNode> list1;
   private List<DOMNode> list2;

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates a command-line diff object for two text files
    * @param file1 the first text file
    * @param file2 the second text file
    * @throws IOException
    * @throws SAXException
    */
   public XMLDiff(InputStream in1, InputStream in2) throws IOException {
      try {
         this.list1 = new DOMNodeCollector(parse(in1)).getNodeList();
         this.list2 = new DOMNodeCollector(parse(in2)).getNodeList();
      }
      catch (SAXException e) {
         throw new IOException(e);
      }
   }

   @Override
   protected Differencer<DOMNode> getDifferencer() {
      return new Differencer<DOMNode>(list1, list2);
   }

   @Override
   protected List<DOMNode> getList1() {
      return list1;
   }

   @Override
   protected List<DOMNode> getList2() {
      return list2;
   }

   // ====================================================================
   // Instance methods
   // ====================================================================
}
