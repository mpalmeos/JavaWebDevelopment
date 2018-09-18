package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {
   private Long id;
   private String orderNumber;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getOrderNumber() {
      return orderNumber;
   }

   public void setOrderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
   }

   @Override
   public String toString() {
      return "Order{" +
              "id=" + id +
              ", orderNumber='" + orderNumber + '\'' +
              '}';
   }

   /*public String getOrder (String s){
      *//*Order newOne = new Order();
      Long number = 1L;*//*

      String buffer = s.trim().replaceAll(Pattern.quote("{"), " ").
              replaceAll(Pattern.quote("}"), " ").trim().
              replaceAll(Pattern.quote("\""), " ").trim().
              replaceAll(Pattern.quote(" "), "");
      String[] result = buffer.split(":");

      *//*newOne.setOrderNumber(result[1]);
      newOne.setId(number);
      number++;*//*

      return result[1];
   }*/
}
