package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.OrderDao;
import model.Order;
import model.OrderReport;
import util.ReportGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("api/orders/report")
public class ReportServlet extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

      List<Order> allOrders = new OrderDao().getOrderList();

      OrderReport raport = new ReportGenerator().generateReport(allOrders);
      String output = new ObjectMapper().writeValueAsString(raport);

      response.setContentType("application/json");
      response.getWriter().print(output);
   }
}
