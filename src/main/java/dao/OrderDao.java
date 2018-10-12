package dao;

import model.Order;
import model.Rows;
import util.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

   public static String URL = "jdbc:hsqldb:mem:my-database";

   public List<Order> getOrderList(){
      List<Order> orderList = new ArrayList<>();

      try(Connection conn = DataSourceProvider.getDataSource().getConnection();
         ){

         try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from orders o left join order_rows r on o.id = r.orders_id");

            while (rs.next()) {


            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return orderList;
   }

   public Order getOrderByID(Long id){

      String sql_o = "select id, orderNumber from orders where id = ?";
      String sql_r = "select itemName, quantity, price from order_rows where orders_id = ?";
      Order newOrder = null;
      List<Rows> orderRows = null;

      try (Connection conn = DataSourceProvider.getDataSource().getConnection()){

         try(PreparedStatement ps = conn.prepareStatement(sql_o)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
               newOrder.setId(rs.getLong("id"));
               newOrder.setOrderNumber(rs.getString("orderNumber"));
            } else return null;
         }

         try(PreparedStatement ps = conn.prepareStatement(sql_r)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Rows fillRow = null;

            while (rs.next()){
               fillRow.setItemName(rs.getString("itemName"));
               fillRow.setPrice(rs.getInt("price"));
               fillRow.setQuantity(rs.getInt("quantity"));
               orderRows.add(fillRow);
            }
            newOrder.setOrderRows(orderRows);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return newOrder;
   }

   public Order insertOrder(Order order){
      String sql = "insert into orders (id, orderNumber) values (next value for seq1, ?)";
      String sql_r = "insert into order_rows (id, itemName, quantity, price, orders_id) " +
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
                  ps.execute();
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
}
