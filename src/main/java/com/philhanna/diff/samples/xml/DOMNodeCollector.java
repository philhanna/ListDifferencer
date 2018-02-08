package com.philhanna.diff.samples.xml;

import java.util.*;

import org.w3c.dom.*;

/**
 * Extracts nodes from an XML document
 */
public class DOMNodeCollector {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   // ====================================================================
   // Instance variables
   // ====================================================================

   private final List<DOMNode> nodeList = new ArrayList<DOMNode>();

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates a new DOM node collector
    * @param doc a DOM object
    */
   public DOMNodeCollector(Document doc) {
      nodeList.add(new DOMNode(doc));
      final Element elemRoot = doc.getDocumentElement();
      addNodes(elemRoot);
   }

   private void addNodes(Node node) {
      nodeList.add(new DOMNode(node));
      if (node.hasAttributes()) {
         final NamedNodeMap attrNodes = node.getAttributes();
         final int n = attrNodes.getLength();
         for (int i = 0; i < n; i++) {
            final Attr attr = (Attr) attrNodes.item(i);
            nodeList.add(new DOMNode(attr));
         }
      }
      if (node.hasChildNodes()) {
         Node child = node.getFirstChild();
         while (child != null) {
            addNodes(child);
            child = child.getNextSibling();
         }
      }
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   /**
    * Returns the node list
    * @return the node list
    */
   public List<DOMNode> getNodeList() {
      return nodeList;
   }

}
