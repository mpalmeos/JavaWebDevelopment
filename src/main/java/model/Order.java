package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

   @Id
   @SequenceGenerator(name = "my_seq", sequenceName = "order_sequence", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
   private Long id;

   @NotNull
   @Size(min = 2)
   @Column(name = "order_number")
   private String orderNumber;

   @Valid
   @CollectionTable(
           name = "order_rows",
           joinColumns=@JoinColumn(name = "orders_id",
                   referencedColumnName = "id")
   )
   @ElementCollection(fetch = FetchType.EAGER)
   private List<Rows> orderRows = new ArrayList<>();
}
