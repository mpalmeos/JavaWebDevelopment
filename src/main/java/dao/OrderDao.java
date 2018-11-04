package dao;

import model.Order;
import model.Rows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Primary
public class OrderDao {

   @Autowired
   private JdbcTemplate template;

   //https://codereview.stackexchange.com/questions/42185/how-to-fill-an-arraylist-of-arraylists-with-a-left-join
   public List<Order> getOrderList(){
      String sql = "select o.id, o.orderNumber, r.itemName, r.quantity, r.price from orders o " +
              "left join order_rows r on o.id = r.orders_id";

      return template.query(sql, new BeanPropertyRowMapper<>(Order.class));
   }

   public Order getOrderByID(Long id){

      String sql = "select o.id, o.orderNumber, r.itemName, r.quantity, r.price from orders o " +
              "left join order_rows r on o.id = r.orders_id where o.id = ?";

      return template.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<>(Order.class));
   }

   public Order insertOrder(Order order){
      String sql = "insert into orders (id, orderNumber) values (next value for seq1, ?)";
      String sql_r = "insert into order_rows (row_id, itemName, quantity, price, orders_id) " +
              "values (next value for seq2, ?, ?, ?, ?)";

      GeneratedKeyHolder id_holder = new GeneratedKeyHolder();
      Long order_id;

      template.update(con -> {
         PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
         ps.setString(1, order.getOrderNumber());
         return ps;
      }, id_holder);

      order_id = id_holder.getKey().longValue();

      if (order.getOrderRows() != null){
         template.update(con -> {
            PreparedStatement ps2 = con.prepareStatement(sql_r);
            for (Rows r : order.getOrderRows()) {
               ps2.setString(1, r.getItemName());
               ps2.setInt(2, r.getQuantity());
               ps2.setInt(3, r.getPrice());
               ps2.setLong(4, order_id);
               ps2.executeUpdate();
            }
            return ps2;
         });
      }

      order.setId(order_id);
      return order;
   }

   public void deleteOrderByID(Long id){
      String sql = "delete from orders where id = ?";
      String sql_r = "delete from order_rows where orders_id = ?";
      template.update(sql, id);
      template.update(sql_r, id);
   }

   public void deleteAll(){
      String sql = "truncate schema public and commit";
      template.update(sql);
   }
}
