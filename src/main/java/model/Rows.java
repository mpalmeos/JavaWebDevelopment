package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rows {
   private String itemName;

   @NotNull
   @Positive
   private int quantity;

   @NotNull
   @Positive
   private int price;
}
