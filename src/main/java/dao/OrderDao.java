package dao;

import model.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Primary
public class OrderDao {

   @PersistenceContext
   private EntityManager entityManager;

   public List<Order> getOrderList(){
      return entityManager.createQuery(
              "select o from Order o",
              Order.class)
              .getResultList();
   }

   public Order getOrderByID(Long id){
      TypedQuery<Order> query = entityManager.createQuery(
              "select distinct o from Order o where o.id = :id",
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

   @Transactional
   public void deleteOrderByID(Long id){
      Order order = entityManager.find(Order.class, id);
      if (order != null) entityManager.remove(order);
   }

   @Transactional
   public void deleteAll(){
      Query query = entityManager.createNativeQuery(
              "truncate schema public and commit"
      );
      query.executeUpdate();
   }
}
