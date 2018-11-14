package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Rows {

   @Column(name = "item_name")
   private String itemName;

   @NotNull
   @Positive
   private int quantity;

   @NotNull
   @Positive
   private int price;
}
