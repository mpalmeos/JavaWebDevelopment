package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {
   private Integer count;
   private Integer averageOrderAmount;
   private Integer turnoverWithoutVAT;
   private Integer turnoverVAT;
   private Integer turnoverWithVAT;
}
