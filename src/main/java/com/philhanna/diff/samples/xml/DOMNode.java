package com.philhanna.diff.samples.xml;

import org.w3c.dom.*;

/**
 * Represents a node in an XML document
 */
public class DOMNode {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   // ====================================================================
   // Instance variables
   // ====================================================================

   private final Node node;
   private final int type;
   private final String typeName;
   private final String name;
   private final String value;

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates a new DOMNode from the specified node
    * @param node a node in a DOM object
    */
   public DOMNode(Node node) {
      this.node = node;
      this.type = node.getNodeType();
      this.typeName = NodeNames.getTypeName(type);
      this.name = node.getNodeName();
      this.value = node.getNodeValue();
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   /**
    * Returns the type
    */
   public int getType() {
      return type;
   }

   /**
    * Returns the type name
    */
   public String getTypeName() {
      return typeName;
   }

   /**
    * Returns the name
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the value
    */
   public String getValue() {
      return value;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null)
            ? 0
            : name.hashCode());
      result = prime * result + type;
      result = prime * result + ((typeName == null)
            ? 0
            : typeName.hashCode());
      result = prime * result + ((value == null)
            ? 0
            : value.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      DOMNode other = (DOMNode) obj;
      if (name == null) {
         if (other.name != null)
            return false;
      }
      else if (!name.equals(other.name))
         return false;
      if (type != other.type)
         return false;
      if (typeName == null) {
         if (other.typeName != null)
            return false;
      }
      else if (!typeName.equals(other.typeName))
         return false;
      if (value == null) {
         if (other.value != null)
            return false;
      }
      else if (!value.equals(other.value))
         return false;
      return true;
   }

   @Override
   public String toString() {
      switch (type) {
         case Node.ATTRIBUTE_NODE: {
            final Attr attr = (Attr) node;
            return String.format(
                  "Attribute <%s %s=\"%s\">",
                  attr.getOwnerElement().getTagName(),
                  attr.getName(),
                  attr.getValue());
         }
         case Node.ELEMENT_NODE: {
            final Element element = (Element) node;
            final StringBuilder sb = new StringBuilder();
            sb.append("Element <");
            sb.append(element.getTagName());
            final NamedNodeMap attrNodes = element.getAttributes();
            for (int index = 0, n = attrNodes.getLength(); index < n; index++) {
               final Attr attr = (Attr) attrNodes.item(index);
               sb.append(" ");
               sb.append(attr.getName());
               sb.append("=");
               sb.append("\"");
               sb.append(attr.getValue());
               sb.append("\"");
            }
            sb.append(">");
            final String output = sb.toString();
            return output;
         }
         case Node.TEXT_NODE: {
            final Text text = (Text) node;
            return String.format("%s", text.getData());
         }
         default: {
            return String.format(
                  "%s: %s=%s",
                  getTypeName(),
                  getName(),
                  getValue());
         }
      }
   }
}
