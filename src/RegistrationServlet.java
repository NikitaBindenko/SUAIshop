import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        PrintWriter out = response.getWriter();
	request.getRequestDispatcher("front/src/profile/profile.html").include(request, response);
        out.close();
    }

}
