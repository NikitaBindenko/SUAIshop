import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LogoutServlet extends HttpServlet {
    
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html");
         HttpSession session = request.getSession();
         session.invalidate();
        //PrintWriter out = response.getWriter();
        //request.getRequestDispatcher("front/src/index.html").include(request, response);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/catalog");
        requestDispatcher.forward(request, response);
        //out.close();
    }
}
