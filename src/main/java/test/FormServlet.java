package test;

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

      Order test = new Order();
      test.setOrderNumber(request.getParameter("orderNumber"));
      DataSafe.store(test.getId(), test);

      response.setContentType("application/json");
      response.getWriter().print(test.getId());
      //System.out.println("FORM: " + test);
   }

}
