import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class BinServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String goodsID = request.getParameter("id");
        Catalog catalog = new Catalog();
        Goods addedGoods = catalog.getGoods(Integer.parseInt(goodsID));
        user.addToBin(addedGoods);
        PrintWriter out = response.getWriter();
	out.println("<!DOCTYPE html><html><body>");
	LinkedList<Goods> cart = user.getBin().getBin();
	out.println(cart);
	out.println("</body</html>");
        out.close();
    }

}
