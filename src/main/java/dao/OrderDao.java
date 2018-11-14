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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

@Repository
@Primary
public class OrderDao {

   @PersistenceContext
   private EntityManager entityManager;

   public List<Order> getOrderList(){
      return entityManager.createQuery("select o from Order o",
              Order.class).getResultList();
   }

   public Order getOrderByID(Long id){
      TypedQuery<Order> query = entityManager.createQuery(
              "select o from Order o where o.id = :id",
              Order.class);
      query.setParameter("id", id);
      return query.getSingleResult();
   }

   @Transactional
   public Order insertOrder(Order order){
      if (order.getId() == null){
         entityManager.persist(order);
      } else entityManager.merge(order);
      return order;
   }

   public void deleteOrderByID(Long id){

   }

   public void deleteAll(){

   }
}
