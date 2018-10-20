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
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        //https://examples.javacodegeeks.com/enterprise-java/servlet/get-request-parameter-in-servlet/
        Long inputID = Util.getLong(request.getParameter("id"));
        String output;

        if (inputID == null){
            List<Order> allOrders = new OrderDao().getOrderList();
            output = new ObjectMapper().writeValueAsString(allOrders);
        } else {
            Order orderByID = new OrderDao().getOrderByID(inputID);
            output = new ObjectMapper().writeValueAsString(orderByID);
        }

        response.setContentType("application/json");
        response.getWriter().print(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String input = Util.asString(req.getInputStream());
        Order inputOrder = new ObjectMapper().readValue(input, Order.class);
        Order processedOrder = new OrderDao().insertOrder(inputOrder);

        resp.setContentType("application/json");
        String output = new ObjectMapper().writeValueAsString(processedOrder);
        resp.getWriter().print(output);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long inputID = Util.getLong(req.getParameter("id"));
        OrderDao.deleteOrderByID(inputID);
    }
}
