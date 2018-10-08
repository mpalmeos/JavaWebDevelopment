package test;

import dao.OrderDao;
import model.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("orders/form")
public class FormServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

      /*String input = request.getParameter("orderNumber");

      Order insertOrder = new Order(input);
      Order processedOrder = new OrderDao().insertOrder(insertOrder);

      response.setContentType("application/json");
      response.getWriter().print(processedOrder);
      //System.out.println("FORM: " + test);*/
   }

}
