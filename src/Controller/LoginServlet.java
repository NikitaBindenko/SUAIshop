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
        
        if(name == "" || password == ""){out.println("<!DOCTYPE html><html><head><link rel=\"stylesheet\" href=\"front/src/service_style.css\"></head><body>" + "<div id=\"words\" >Please, fill the fields!</div>" + "</body</html>");}	//check if login and password were typed
        
        Database userlist = new Database();
        User loggingUser = userlist.getUser(name);
        if(!(loggingUser.getLogin().equals("") || loggingUser.getPassword().equals(""))){
        
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
        }
        else{
        	out.println("<!DOCTYPE html><html><head><link rel=\"stylesheet\" href=\"front/src/service_style.css\"></head><body>" + "<div id=\"words\" >Please, try again...</div>" + "</body</html>");
        }
        out.close();
    }
}
