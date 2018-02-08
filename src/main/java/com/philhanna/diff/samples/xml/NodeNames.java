package com.philhanna.diff.samples.xml;

import java.lang.reflect.*;
import java.util.*;

import org.w3c.dom.Node;

/**
 * A convenience class that equates a node type (short) to the name of
 * the constant
 */
public class NodeNames {

   // ====================================================================
   // Class variables and constants
   // ====================================================================

   private static final NodeNames instance = new NodeNames();

   // ====================================================================
   // Class methods
   // ====================================================================

   /**
    * Returns the list of integer node type codes
    */
   public static final List<Integer> getNodeTypes() {
      final List<Integer> list = new ArrayList<Integer>();
      list.addAll(instance.map.keySet());
      return list;
   }

   /**
    * Given an integer node type index, return the associated name
    * @param index the integer node type index
    * @return the associated name
    */
   public static final String getTypeName(int index) {
      return instance.map.get(index);
   }

   // ====================================================================
   // Instance variables
   // ====================================================================

   private final Map<Integer, String> map = new TreeMap<Integer, String>();

   // ====================================================================
   // Constructors
   // ====================================================================

   /**
    * Creates a new NodeNames object
    */
   private NodeNames() {

      // Using reflection on the Node class, get all the DOM node type
      // names and store them in the node types map

      for (final Field field : Node.class.getFields()) {
         final int mod = field.getModifiers();
         if (Modifier.isPublic(mod)
               && Modifier.isStatic(mod)
               && Modifier.isFinal(mod)) {
            final Class<?> fieldType = field.getType();
            if (fieldType == Short.TYPE) {
               Integer index = null;
               try {
                  Short shortKey = (Short) field.get(null);
                  index = shortKey.intValue();
               }
               catch (IllegalAccessException e) {
                  throw new RuntimeException(e);
               }
               final String name = field.getName();
               if (!name.startsWith("DOCUMENT_POSITION")) {
                  map.put(index, name);
               }
            }
         }
      }
   }
}
