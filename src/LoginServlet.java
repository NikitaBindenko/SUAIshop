import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LoginServlet extends HttpServlet {
    
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        PrintWriter out = response.getWriter();
        
        Database userlist = new Database();
        User loggingUser = userlist.getUser(name);
        
        if(loggingUser.getPassword().equals(password)){
        	request.getRequestDispatcher("front/src/profile/profile.html").include(request, response);
        }
        else{
        	out.println("<!DOCTYPE html><html><body>" + loggingUser.getPassword() + "\n" + loggingUser.getLogin() + "</body</html>");
        }
        out.close();
    }
}
