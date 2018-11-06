package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rows {
   private String itemName;

   @NotNull
   @Min(1)
   private int quantity;

   @NotNull
   @Min(1)
   private int price;
}
