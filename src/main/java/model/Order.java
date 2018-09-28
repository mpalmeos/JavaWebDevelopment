package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
public class Order {
   private Long id;

   @Getter
   private static Long id_counter = 1L;

   @Getter
   private String orderNumber;

   @Getter
   private List<Rows> orderRows;

   public Long getId() {
      if (this.id == null) this.setId(id_counter++);
      return id;
   }
}
