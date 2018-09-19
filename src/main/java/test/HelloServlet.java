package test;

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
    private long num = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().print("Hello!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String input = Util.asString(req.getInputStream());
        //System.out.println(input);
        Order test = new Order();
        test.setOrderNumber(Util.getOrder(input));
        test.setId(num);
        num++;
        System.out.println(test);

        resp.setContentType("application/json");
        String output = "{ \"id\": " + test.getId() +", \"orderNumber\": \"" + test.getOrderNumber() +"\" }";

        resp.getWriter().print(output);
    }
}
