package model;

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
}
