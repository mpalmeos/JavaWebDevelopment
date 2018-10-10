package model;

import lombok.Data;

import java.util.List;

@Data
public class Order {
   private Long id;
   private String orderNumber;
   private List<Rows> orderRows;

   public Order() {
   }

   public Order(Long id, String orderNumber) {
      this.id = id;
      this.orderNumber = orderNumber;
   }

   public Order(Long id, String orderNumber, List<Rows> orderRows) {
      this.id = id;
      this.orderNumber = orderNumber;
      this.orderRows = orderRows;
   }
}
