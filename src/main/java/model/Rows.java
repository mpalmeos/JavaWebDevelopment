package model;

import lombok.Data;

@Data
public class Rows {
   private String itemName;
   private int quantity;
   private int price;

   public Rows() {
   }

   public Rows(String itemName, int quantity, int price) {
      this.itemName = itemName;
      this.quantity = quantity;
      this.price = price;
   }
}
