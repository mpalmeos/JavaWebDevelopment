package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

@WebServlet("/api/orders")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String input = request.getParameter("id");
        Long valueLong = Long.valueOf(input);
        String output = new ObjectMapper().writeValueAsString(DataSafe.get(valueLong));

        response.setContentType("application/json");
        response.getWriter().print(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String input = Util.asString(req.getInputStream());
        Order test = new ObjectMapper().readValue(input, Order.class);
        DataSafe.store(test.getId(), test);

        resp.setContentType("application/json");
        String output = new ObjectMapper().writeValueAsString(test);

        resp.getWriter().print(output);
        System.out.println(output);
    }
}
