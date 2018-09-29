package test;

import model.Order;

import java.util.HashMap;
import java.util.Map;

public class DataSafe {

   private static Map<Long, Order> map = new HashMap<>();

   public static void store(Long key, Order order){
      map.put(key, order);
   }

   public static Order get(Long id){
      return map.get(id);
   }
}
