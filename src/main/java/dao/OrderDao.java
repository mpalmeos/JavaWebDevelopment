package dao;

import model.Order;
import model.Rows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import util.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class OrderDao {

   @Autowired
   private JdbcTemplate template;

   //https://codereview.stackexchange.com/questions/42185/how-to-fill-an-arraylist-of-arraylists-with-a-left-join
   public List<Order> getOrderList(){
      List<Order> orderList = new ArrayList<>();
      Map<Long, Order> orders = new HashMap<>();
      Map<Long, List<Rows>> rows = new HashMap<>();

      try(Connection conn = DataSourceProvider.getDataSource().getConnection();
          Statement stmt = conn.createStatement()){

          ResultSet rs = stmt.executeQuery("select o.id, o.orderNumber, r.itemName, r.quantity, r.price from orders o " +
                  "left join order_rows r on o.id = r.orders_id");

          while (rs.next()) {
              Long o_id = rs.getLong("id");
              Order o = orders.get(o_id);
              if (o == null){
                  o = new Order(
                          o_id,
                          rs.getString("orderNumber"),
                          null
                  );
                  orders.put(o_id, o);
                  rows.put(o_id, new ArrayList<Rows>());
              }

              if (rs.getString("itemName") != null){
                 List<Rows> rowList = rows.get(o_id);
                 Rows r = new Rows(
                         rs.getString("itemName"),
                         rs.getInt("quantity"),
                         rs.getInt("price")
                 );
                 rowList.add(r);
              }
          }

          for (Order o : orders.values()){
             o.setOrderRows(rows.get(o.getId()));
             orderList.add(o);
          }

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }

      return orderList;
   }

   public Order getOrderByID(Long id_value){

      String sql_o = "select o.id, o.orderNumber, r.itemName, r.quantity, r.price from orders o " +
              "left join order_rows r on o.id = r.orders_id where o.id = ?";
      Order newOrder = new Order();
      List<Rows> rowList = new ArrayList<>();

      try (Connection conn = DataSourceProvider.getDataSource().getConnection();
           PreparedStatement ps = conn.prepareStatement(sql_o)){

         ps.setLong(1, id_value);

         ResultSet rs = ps.executeQuery();

         while (rs.next()) {
            newOrder.setId(rs.getLong("id"));
            newOrder.setOrderNumber(rs.getString("orderNumber"));

            if (rs.getString("itemName") != null) {
               Rows r = new Rows(
                       rs.getString("itemName"),
                       rs.getInt("quantity"),
                       rs.getInt("price")
               );
               rowList.add(r);
            }
         }

         newOrder.setOrderRows(rowList);

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }

      return newOrder;
   }

   public Order insertOrder(Order order){
      String sql = "insert into orders (id, orderNumber) values (next value for seq1, ?)";
      String sql_r = "insert into order_rows (row_id, itemName, quantity, price, orders_id) " +
              "values (next value for seq2, ?, ?, ?, ?)";
      Long order_id;

      try(Connection conn = DataSourceProvider.getDataSource().getConnection()){

         try(PreparedStatement ps = conn.prepareStatement(sql, new String[] {"id"})){
            ps.setString(1, order.getOrderNumber());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            order_id = rs.getLong("id");
         }

         if (order.getOrderRows() != null) {
            try(PreparedStatement ps = conn.prepareStatement(sql_r)){
               for (Rows r : order.getOrderRows()) {
                  ps.setString(1, r.getItemName());
                  ps.setInt(2, r.getQuantity());
                  ps.setInt(3, r.getPrice());
                  ps.setLong(4, order_id);
                  ps.executeUpdate();
               }
            }
         }

         return new Order(order_id, order.getOrderNumber(), order.getOrderRows());

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   //http://www.sqlitetutorial.net/sqlite-java/delete/
   public static void deleteOrderByID(Long id){
      String sql = "delete from orders where id = ?";
      String sql_r = "delete from order_rows where orders_id = ?";

      try(Connection conn = DataSourceProvider.getDataSource().getConnection()){
         
         try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ps.executeUpdate();
         }

         try(PreparedStatement ps = conn.prepareStatement(sql_r)){
            ps.setLong(1, id);
            ps.executeUpdate();   
         }

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   public static void deleteAll(){
      String sql = "truncate schema public and commit";

      try(Connection conn = DataSourceProvider.getDataSource().getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {

         ps.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }
}
