import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        String username = user.getLogin();
        out.println("<!DOCTYPE html><html><body>" + "Salam aleikum " + username + "</body</html>");	//заглушка для html страниц
        
        out.close();
    }

}
