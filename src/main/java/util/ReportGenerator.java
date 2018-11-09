package util;

import model.Order;
import model.Report;
import model.Rows;

import java.util.List;

public class ReportGenerator {

   public Report generateReport(List<Order> orders){
      Integer turnoverVAT;
      Integer orderCount = orders.size();
      Integer avgAmount;
      Integer priceWithoutVAT = 0;
      Integer priceWithVAT;

      for (Order o : orders) {
         for (Rows r : o.getOrderRows()) {
            priceWithoutVAT += r.getPrice() * r.getQuantity();
         }
      }

      avgAmount = priceWithoutVAT / orderCount;
      turnoverVAT = priceWithoutVAT * 20/100;
      priceWithVAT = priceWithoutVAT + turnoverVAT;

      return new Report(orderCount, avgAmount, priceWithoutVAT, turnoverVAT, priceWithVAT);
   }
}
