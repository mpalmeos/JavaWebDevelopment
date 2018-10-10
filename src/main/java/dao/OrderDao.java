package dao;

import model.Order;
import util.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

   public static String URL = "jdbc:hsqldb:mem:my-database";

   public List<Order> getOrderList(){
      List<Order> orderList = new ArrayList<>();

      try (Connection conn = DataSourceProvider.getDataSource().getConnection();
           Statement stmt = conn.createStatement()) {

         ResultSet rs = stmt.executeQuery("select * from orders");

         while (rs.next()){
            Order o = new Order(
                    rs.getLong("id"),
                    rs.getString("orderNumber")
            );

            orderList.add(o);
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
      return orderList;
   }

   public Order getOrderByID(Long id){

      String sqlbyID = "select id, orderNumber from orders where id = ?";

      try (Connection conn = DataSourceProvider.getDataSource().getConnection();
           PreparedStatement ps = conn.prepareStatement(sqlbyID)) {

         ps.setLong(1, id);

         ResultSet rs = ps.executeQuery();

         if (rs.next()){
            return new Order(
              rs.getLong("id"),
              rs.getString("orderNumber")
            );
         } else return null;

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   public Order insertOrder(Order order){
      String sql = "insert into orders (id, orderNumber) values (next value for seq1, ?)";
      Long order_id;

      try(Connection conn = DataSourceProvider.getDataSource().getConnection()){

         try(PreparedStatement ps = conn.prepareStatement(sql, new String[] {"id"})){
            ps.setString(1, order.getOrderNumber());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            order_id = rs.getLong("id");

         }

         return new Order(order_id, order.getOrderNumber(), order.getOrderRows());

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   //http://www.sqlitetutorial.net/sqlite-java/delete/
   public static void deleteOrderByID(Long id){
      String sql = "delete from orders where id = ?";

      try(Connection conn = DataSourceProvider.getDataSource().getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)){

         ps.setLong(1, id);
         ps.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }
}
