package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import model.Order;
import util.Util;

@WebServlet("/api/orders")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        //https://examples.javacodegeeks.com/enterprise-java/servlet/get-request-parameter-in-servlet/
        String input = request.getParameter("id");
        String output;

        if (input == null){
            List<Order> allOrders = new OrderDao().getOrderList();
            output = new ObjectMapper().writeValueAsString(allOrders);
            response.getWriter().print(output);
        } else {
            Order orderByID = new OrderDao().getOrderByID(Long.valueOf(input));
            output = new ObjectMapper().writeValueAsString(orderByID);
        }

        response.setContentType("application/json");
        response.getWriter().print(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String input = Util.asString(req.getInputStream());
        Order insertOrder = new ObjectMapper().readValue(input, Order.class);
        Order processedOrder = new OrderDao().insertOrder(insertOrder);

        resp.setContentType("application/json");
        String output = new ObjectMapper().writeValueAsString(processedOrder);
        resp.getWriter().print(output);
    }
}
