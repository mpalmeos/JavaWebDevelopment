package dao;

import model.Order;
import model.Rows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class OrderDao {

   @Autowired
   private JdbcTemplate template;

   public List<Order> getOrderList(){
      String sql = "select id, orderNumber from orders";

      List<Order> orders = template.query(sql, new BeanPropertyRowMapper<>(Order.class));
      for (Order o : orders) {
         Long order_id = o.getId();
         List<Rows> rowsList = getRows(order_id);
         o.setOrderRows(rowsList);
      }
      return orders;
   }

   public List<Rows> getRows(Long orderID){
      String sql = "select itemName, quantity, price from order_rows where orders_id = ?";
      return template.query(sql, new Object[] {orderID}, new BeanPropertyRowMapper<>(Rows.class));
   }

   public Order getOrderByID(Long id){

      String sql = "select id, orderNumber from orders where id = ?";

      Order order = template.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<>(Order.class));
      List<Rows> rows = getRows(id);
      order.setOrderRows(rows);
      return order;
   }

   public Order insertOrder(Order order){
      String sql = "insert into orders (id, orderNumber) values (next value for seq1, ?)";

      GeneratedKeyHolder id_holder = new GeneratedKeyHolder();
      Long order_id;

      template.update(con -> {
         PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
         ps.setString(1, order.getOrderNumber());
         return ps;
      }, id_holder);

      order_id = id_holder.getKey().longValue();
      if (order.getOrderRows() != null) insertRows(order_id, order.getOrderRows());
      order.setId(order_id);

      return order;
   }

   //https://www.mkyong.com/spring/spring-jdbctemplate-batchupdate-example/
   public void insertRows(Long orderID, List<Rows> rowsList){
      String sql = "insert into order_rows (row_id, itemName, quantity, price, orders_id) " +
              "values (next value for seq2, ?, ?, ?, ?)";

      template.batchUpdate(sql, new BatchPreparedStatementSetter() {
         @Override
         public void setValues(PreparedStatement ps, int i) throws SQLException {
            Rows r = rowsList.get(i);
            ps.setString(1, r.getItemName());
            ps.setInt(2, r.getQuantity());
            ps.setInt(3, r.getPrice());
            ps.setLong(4, orderID);
         }

         @Override
         public int getBatchSize() {
            return rowsList.size();
         }
      });
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
