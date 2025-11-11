package com.example.utils;

import com.example.annotations.MindMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.*;

public class TraceabilityGenerator {
  public static void main(String[] args) throws Exception {
    List<Map<String,String>> list = new ArrayList<>();
    // Add test classes explicitly or scan package (simplified: list classes)
    Class<?>[] classes = new Class<?>[]{
      com.example.tests.smoke.SmokeCheckoutTest.class,
      com.example.tests.regression.InvalidLoginTest.class,
      com.example.tests.regression.RemoveItemTest.class,
      com.example.tests.regression.SortVerificationTest.class,
      com.example.tests.exploratory.BackNavTest.class,
      com.example.tests.exploratory.AddAllProductsTest.class,
      com.example.tests.exploratory.MobileResponsivenessTest.class
    };
    for(Class<?> c : classes){
      for(Method m : c.getDeclaredMethods()){
        if(m.isAnnotationPresent(MindMap.class)){
          MindMap mm = m.getAnnotation(MindMap.class);
          Map<String,String> map = new HashMap<>();
          map.put("class", c.getName());
          map.put("method", m.getName());
          map.put("node", mm.node());
          map.put("priority", mm.priority());
          map.put("file", mm.file());
          map.put("ci", mm.ci());
          list.add(map);
        }
      }
    }
    Gson g = new GsonBuilder().setPrettyPrinting().create();
    try(FileWriter fw = new FileWriter("docs/traceability.json")){
      fw.write(g.toJson(list));
    }
    System.out.println("traceability.json generated in docs/");
  }
}
