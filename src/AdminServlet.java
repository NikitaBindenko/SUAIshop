import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        if(user.isSeller()){out.println("<!DOCTYPE html><html><body>" + "You are successfully logged as ADMINISTRATOR" + "</body</html>");}	//заглушка
        else{out.println("<!DOCTYPE html><html><body>" + "You don't have enough rights to access this page..." + "</body</html>");}		//для html страниц
        
        out.close();
    }

}
