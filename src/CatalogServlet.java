import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CatalogServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("front/src/index.html").include(request, response);
    }
}
