package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
   private Long id;
   private String orderNumber;

   @Valid
   private List<Rows> orderRows;
}
