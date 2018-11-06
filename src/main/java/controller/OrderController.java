package controller;

import dao.OrderDao;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

   @Autowired
   private OrderDao orderDao;

   @GetMapping("orders")
   public List<Order> getAllOrders(){
      return orderDao.getOrderList();
   }

   @GetMapping("orders/{orderID}")
   public Order getOrder(@PathVariable("orderID") Long orderID){
      return orderDao.getOrderByID(orderID);
   }

   @PostMapping("orders")
   public Order saveOrder(@RequestBody @Valid Order order){
      return orderDao.insertOrder(order);
   }

   @DeleteMapping("orders")
   public void deleteAllOrders(){
      orderDao.deleteAll();
   }

   @DeleteMapping("orders/{orderID}")
   public void deleteOrder(@PathVariable("orderID") Long orderID){
      orderDao.deleteOrderByID(orderID);
   }
}
