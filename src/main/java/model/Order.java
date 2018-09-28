package model;

public class Order {
   private Long id;
   private static Long id_counter = 1L;
   private String orderNumber;

   public Long getId() {
      if (this.id == null) this.setId(id_counter++);
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
