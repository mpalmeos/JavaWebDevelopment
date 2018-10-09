package model;

import java.util.List;

public class Order {
   private Long id;
   private String orderNumber;
   private List<Rows> orderRows;

   public Order(){}

   public Order(Long id, String number){
      this.id = id;
      this.orderNumber = number;
   }

   public Order(String orderNumber){
      this.orderNumber = orderNumber;
   }

   public Long getId() {
      return id;
   }

   public String getOrderNumber() {
      return orderNumber;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setOrderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
   }

   public void setOrderRows(List<Rows> orderRows) {
      this.orderRows = orderRows;
   }

   public List<Rows> getOrderRows() {
      return orderRows;
   }

   @Override
   public String toString() {
      return "Order{" +
              "id=" + id +
              ", orderNumber='" + orderNumber + '\'' +
              '}';
   }
}
