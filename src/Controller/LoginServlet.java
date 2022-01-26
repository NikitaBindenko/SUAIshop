package Controller;

import Model.*;
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
        	HttpSession session = request.getSession();
        	session.setAttribute("user", loggingUser);	//associates session with object User
        	if(loggingUser.isSeller()){
        		response.sendRedirect(request.getContextPath() + "/admin");
        	}
        	else{
            		response.sendRedirect(request.getContextPath() + "/profile");
            	}
        }
        else{
        	out.println("<!DOCTYPE html><html><body>" + "Please, try again..." + "</body</html>");
        }
        out.close();
    }
}
