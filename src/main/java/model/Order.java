package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
   private Long id;

   @NotNull
   @Size(min = 2)
   private String orderNumber;

   @Valid
   private List<Rows> orderRows;
}
